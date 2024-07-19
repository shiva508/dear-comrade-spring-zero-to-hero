package com.comrade.proxy;

import com.comrade.annotations.DcTransaction;
import com.comrade.proxy.util.ProxyUtil;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;

import java.lang.reflect.Method;

@Slf4j
public class DcTransactionalBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (ProxyUtil.transactional(bean)) {
            var proxy = getObject(bean, bean.getClass());
            return proxy.getProxy(getClass().getClassLoader());
        }
        return bean;
    }

    private ProxyFactory getObject(Object bean, Class<?> clazz) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setInterfaces(clazz.getInterfaces());
        proxyFactory.setTarget(bean);
        proxyFactory.addAdvice((MethodInterceptor) invocation -> {
            Method method = invocation.getMethod();
            Object[] args1 = invocation.getArguments();
            try {
                System.out.println("Calling method name = " + method.getName().toUpperCase() + " with arguments [" + args1 + "]");
                if (method.getAnnotation(DcTransaction.class) != null) {
                    System.out.println("starting transaction using proxy for  " + method.getName());
                }
                return method.invoke(bean, args1);
            } finally {
                if (method.getAnnotation(DcTransaction.class) != null) {
                    System.out.println("stopping transaction using proxy for  " + method.getName());
                }
            }
        });
        var proxy = proxyFactory.getProxy(getClass().getClassLoader());
        for (var i: proxy.getClass().getInterfaces()){
            log.info("Proxy class {}",i);
        }
        return proxy;
    }
}
