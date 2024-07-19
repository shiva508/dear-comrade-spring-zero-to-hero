package com.comrade.proxy;

import com.comrade.service.DcGkTopicService;
import com.comrade.service.GkTopicService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class DcNonProxyTransactionalBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof GkTopicService){
            return new DcGkTopicService();
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
