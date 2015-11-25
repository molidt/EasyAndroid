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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;

import com.qinxiandiqi.easyandroid.injection.annotation.Layout;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Jianan on 10/5/15.
 */
public class EasyInjection {

    private View rootView;
    private Object holder;
    private Map<Integer, View> cacheViews = new HashMap<Integer, View>();

    public static final EasyInjection bind(Activity act, int layoutID){
        act.setContentView(layoutID);
        return bind(act.getWindow().getDecorView(), act);
    }

    public static final EasyInjection bind(View rootView, Object holder){
        EasyInjection instance = new EasyInjection();
        instance.rootView = rootView;
        instance.holder = holder;
        return instance;
    }

    public final <T extends View> EasyInjection view(T view, int resID){
        if(cacheViews.containsKey(resID)){
            view = (T) cacheViews.get(resID);
        }else{
            view = (T) rootView.findViewById(resID);
            cacheViews.put(resID, view);
        }
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

    public final EasyInjection dimen(Float target, int resID){
        target = rootView.getResources().getDimension(resID);
        return this;
    }

    public final EasyInjection anim(Animation target, int resID){
        target = AnimationUtils.loadAnimation(rootView.getContext(), resID);
        return this;
    }

    public final EasyInjection onClick(final Method target, int... resIDs){
        View.OnClickListener listener = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                try {
                    target.invoke(holder, v);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        };
        View tempView = null;
        for(int id : resIDs){
            view(tempView, id);
            tempView.setOnClickListener(listener);
        }
        return this;
    }

    public final EasyInjection onClick(final String methodName, int... resIDs){
        try {
            Method method = holder.getClass().getDeclaredMethod(methodName, View.class);
            onClick(method, resIDs);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Can't find out " + methodName + " method.", e);
        }finally {
            return this;
        }
    }

    public final EasyInjection onItemClick(final Method target, int... resIDs){
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    target.invoke(holder, parent, view, position, id);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        };
        View tempView = null;
        for(int id : resIDs){
            view(tempView, id);
            if(tempView instanceof AdapterView){
                ((AdapterView)tempView).setOnItemClickListener(listener);
            }else{
                throw new RuntimeException("The view with id(" + id + ") is not a AdapterView.");
            }
        }
        return this;
    }

    public final EasyInjection onItemClick(String methodName, int... resIDs){
        try {
            Method method = holder.getClass().getMethod(methodName, AdapterView.class, View.class, Integer.class, Long.class);
            onItemClick(method, resIDs);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Can't find out " + methodName + " method.", e);
        }finally {
            return this;
        }
    }
}
