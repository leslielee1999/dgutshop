package org.dgutstu.dgutshop.core.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Author: leesk
 * @Description:
 * @Date: Create in 21:54 2020/12/19
 */
public class BeanUtil implements ApplicationContextAware {
    protected static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static Object getBean(String name) {
        return context.getBean(name);
    }

    public static <T> T getBean(Class<T> c){
        return context.getBean(c);
    }
}
