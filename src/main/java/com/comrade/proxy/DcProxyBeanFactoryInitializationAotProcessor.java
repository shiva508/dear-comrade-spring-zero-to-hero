package com.comrade.proxy;

import org.springframework.beans.factory.aot.BeanFactoryInitializationAotContribution;
import org.springframework.beans.factory.aot.BeanFactoryInitializationAotProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class DcProxyBeanFactoryInitializationAotProcessor implements BeanFactoryInitializationAotProcessor {
    @Override
    public BeanFactoryInitializationAotContribution processAheadOfTime(ConfigurableListableBeanFactory beanFactory) {
        return (generationContext, beanFactoryInitializationCode) -> generationContext.getRuntimeHints()
                .proxies()
                .registerJdkProxy(
                com.comrade.service.GkTopicService.class,
                org.springframework.aop.SpringProxy.class,
                org.springframework.aop.framework.Advised.class,
                org.springframework.core.DecoratingProxy.class
                );
    }
}
