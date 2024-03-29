package com.teemor.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Locale;

/**
 * spring util
 *
 * @author hydl
 * @date 2018/2/3
 */
public class SpringContextHolder implements ApplicationContextAware {

    /**
     * Spring应用上下文环境
     */
    private static ApplicationContext applicationContext;

    /**
     * 获取对象
     *
     * @param name bean name
     * @return Object 一个以所给名字注册的bean的实例
     * @throws BeansException
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

    /**
     * 获取类型为requiredType的对象
     *
     * @param clz bean class
     * @return
     * @throws BeansException
     */
    public static <T> T getBean(Class<T> clz) {
        @SuppressWarnings("unchecked")
        T result = (T) applicationContext.getBean(clz);
        return result;
    }

    /**
     * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
     *
     * @param name bean name
     * @return boolean
     */
    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    /**
     * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。
     * 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
     *
     * @param name bean name
     * @return boolean
     * @throws NoSuchBeanDefinitionException
     */
    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.isSingleton(name);
    }

    /**
     * @param name bean name
     * @return Class 注册对象的类型
     * @throws NoSuchBeanDefinitionException
     */
    public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.getType(name);
    }

    /**
     * 如果给定的bean名字在bean定义中有别名，则返回这些别名
     *
     * @param name bean name
     * @return
     * @throws NoSuchBeanDefinitionException
     */
    public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.getAliases(name);
    }

    public static ApplicationContext getContext() {
        return applicationContext;
    }


    /**
     * @return 当前环境 spring.profile.active
     */
    public static String getActiveProfile() {
        return applicationContext.getEnvironment().getActiveProfiles()[0];
    }

    /**
     * @return applicationName spring.application.name
     */
    public static String getApplicationName() {
        return applicationContext.getId();
    }

    /**
     * 国际化使用
     *
     * @param key 消息key
     * @return 消息
     */
    public static String getMessage(String key) {
        return applicationContext.getMessage(key, null, Locale.getDefault());
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }
}
