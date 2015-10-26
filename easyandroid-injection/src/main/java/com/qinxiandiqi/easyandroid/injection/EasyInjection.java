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
package com.qinxiandiqi.easyandroid.injection;

import android.app.Activity;
import android.view.View;

import com.qinxiandiqi.easyandroid.injection.annotation.Layout;

import java.lang.reflect.Field;

/**
 * Created by Jianan on 10/5/15.
 */
public class EasyInjection {

    public static void init(Activity act){
        Layout layout = act.getClass().getAnnotation(Layout.class);
        if(layout != null){
            act.setContentView(layout.value());
        }
        init(act.getWindow().getDecorView(), act);
    }

    public static void init(View rootView, Object handler){
        Class handleClass = handler.getClass();
        Field[] fields = handleClass.getDeclaredFields();
        if(fields != null && fields.length > 0){
            for(Field field : fields){
            }
        }
    }

}
