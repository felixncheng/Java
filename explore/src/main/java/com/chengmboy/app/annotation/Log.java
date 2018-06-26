package com.chengmboy.app.annotation;

import java.lang.annotation.*;

/**
 * @author cheng_mboy
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    String value() default "";
}
