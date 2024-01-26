package com.teemor.core.annotation.role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 接口权限校验标识
 *
 * @author : lujing
 * @since :  2019/11/5 12:02
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {

    /**
     * 权限的名称
     *
     * @return 权限名称
     */
    String value() default "";

}
