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

    private View rootView;

    public static final EasyInjection bind(Activity act, int layoutID){
        act.setContentView(layoutID);
        return bind(act.getWindow().getDecorView());
    }

    public static final EasyInjection bind(View rootView){
        EasyInjection instance = new EasyInjection();
        instance.rootView = rootView;
        return instance;
    }

    public final <T extends View> EasyInjection view(T view, int resID){
        view = (T) rootView.findViewById(resID);
        return this;
    }

    public final EasyInjection string(String target, int resID){
        target = rootView.getResources().getString(resID);
        return this;
    }

    public final EasyInjection color(Integer target, int resID){
        target = rootView.getResources().getColor(resID);
        return this;
    }
}
