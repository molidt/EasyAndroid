package com.qinxiandiqi.easyandroid.ui.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Jianan on 2015/10/7.
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BaseResource {

    Type value();

    String test() default "";

    enum Type{
        /**
         * layout resource
         */
        LAYOUT,

        /**
         * view resource
         */
        VIEW,

        /**
         * string resource
         */
        STRING,

        /**
         * color resource
         */
        COLOR,

        /**
         * drawable resource
         */
        DRAWABLE
    }
}
