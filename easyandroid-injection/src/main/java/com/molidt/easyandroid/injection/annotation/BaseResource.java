/*
   Copyright 2015 Jianan - qinxiandiqi@foxmail.com

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package com.molidt.easyandroid.injection.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.String;

/**
 * Created by Jianan on 2015/10/7.
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.CLASS)
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
        DRAWABLE,

        /**
         * dimen resource
         */
        DIMEN,

        /**
         * animation resource
         */
        ANIM
    }
}
