package com.comrade.proxy.util;

import com.comrade.annotations.DcTransaction;
import org.springframework.util.ReflectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;

public class ProxyUtil {

   public static boolean transactional(Object o) {
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
}
