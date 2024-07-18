package com.comrade;

import com.comrade.annotations.DcTransaction;
import com.comrade.service.GkTopicService;
import com.comrade.service.TopicService;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;

@SpringBootApplication
public class DearComradeSpringZeroToHeroApplication {


    public static void main(String[] args) {
        SpringApplication.run(DearComradeSpringZeroToHeroApplication.class, args);
    }


	/*
	With custom proxy
	 */
	/*@Bean
	public ApplicationRunner applicationRunner(){
		return args -> {
			var gkTopicService = new GkTopicService();
			var startingForDcTransaction = (TopicService) Proxy.newProxyInstance(
					gkTopicService.getClass().getClassLoader(),
					gkTopicService.getClass().getInterfaces(),
                    (proxy, method, args1) -> {
                        try {
                            System.out.println("Calling method name = " + method.getName() + " with arguments [" + args1 + "]");
                            if (method.getAnnotation(DcTransaction.class) != null ) {
                                System.out.println("starting for  "+ method.getName());
                            }
                            return method.invoke(gkTopicService, args1);
                        }finally {
                            if (method.getAnnotation(DcTransaction.class) != null) {
                                System.out.println("stopping for  "+ method.getName());
                            }
                        }
                    });
			ProxyFactory proxyFactory = new ProxyFactory();
			proxyFactory.setInterfaces(gkTopicService.getClass().getInterfaces());
			proxyFactory.setTarget(gkTopicService);
			proxyFactory.addAdvice((MethodInterceptor) invocation -> {
                Method method = invocation.getMethod();
                Object[] args1 = invocation.getArguments();

                try {
                    System.out.println("Calling method name = " + method.getName() + " with arguments [" + args1 + "]");
                    if (method.getAnnotation(DcTransaction.class) != null ) {
                        System.out.println("starting using proxy for  "+ method.getName());
                    }
                    return method.invoke(gkTopicService, args1);
                }finally {
                    if (method.getAnnotation(DcTransaction.class) != null) {
                        System.out.println("stopping using proxy for  "+ method.getName());
                    }
                }
            });
			var proxyInstance = (TopicService) proxyFactory.getProxy(getClass().getClassLoader());
			startingForDcTransaction.createTopic();
			proxyInstance.createTopic();
		};
	}

	 */

    static boolean transactional(Object o) {
        var hasTransactional = new AtomicBoolean(false);
        var classes = new ArrayList<Class<?>>();
        classes.add(o.getClass());
        Collections.addAll(classes, o.getClass().getInterfaces());
        classes.forEach(clazz -> {
            ReflectionUtils.doWithMethods(clazz, method -> {
                if (method.getAnnotation(DcTransaction.class) != null) {
                    hasTransactional.set(true);
                }
            });
        });
        return hasTransactional.get();
    }

    @Bean
    public GkTopicService gkTopicService() {
        return new GkTopicService();
    }

    @Bean
    public DcTransactionalBeanPostProcessor dcTransactionalBeanPostProcessor() {
        return new DcTransactionalBeanPostProcessor();
    }

    static class DcTransactionalBeanPostProcessor implements BeanPostProcessor {

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            if (transactional(bean)) {
                ProxyFactory proxyFactory = new ProxyFactory();
                proxyFactory.setInterfaces(bean.getClass().getInterfaces());
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
					System.out.println(i);
				}
				return proxy;
            }
            return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
        }
    }

	@Bean
	public ApplicationRunner applicationRunner(TopicService gkTopicService) {
		return args -> {
			gkTopicService.createTopic();
			gkTopicService.view();
		};
	}
}
