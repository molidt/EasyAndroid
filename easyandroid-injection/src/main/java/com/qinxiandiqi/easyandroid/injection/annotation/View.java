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
package com.qinxiandiqi.easyandroid.injection.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Jianan on 2015/10/5.
 */
@Retention(RetentionPolicy.CLASS)
@Documented
@Target(ElementType.FIELD)
@BaseResource(BaseResource.Type.VIEW)
public @interface View {

    /**
     * the resource id of view.
     * @return
     */
    int value();

    /**
     * the outside view id, maybe is the parent view or ancestral view
     * @return
     */
    int outside() default 0;
}
