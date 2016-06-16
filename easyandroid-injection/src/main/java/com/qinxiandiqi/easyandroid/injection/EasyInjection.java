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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jianan on 2015/10/5.
 */
public class EasyInjection {

    private View rootView;
    private Object holder;
    private Map<String, View> cacheViews = new HashMap<String, View>();
    private StringBuilder cacheStrBuilder = new StringBuilder();

    public static EasyInjection bind(Activity act, int layoutID){
        act.setContentView(layoutID);
        return bind(act.getWindow().getDecorView(), act);
    }

    public static EasyInjection bind(View rootView, Object holder){
        return new EasyInjection(rootView, holder);
    }

    private EasyInjection(View rootView, Object holder){
        this.rootView = rootView;
        this.holder = holder;
    }

    public final <T extends View> T view(int... resID){
        return findView(resID);
    }

    public final String string(int resID){
        return rootView.getResources().getString(resID);
    }

    public final int color(int resID){
        return rootView.getResources().getColor(resID);
    }

    public final float dimen(int resID){
        return rootView.getResources().getDimension(resID);
    }

    public final Animation anim(int resID){
        return AnimationUtils.loadAnimation(rootView.getContext(), resID);
    }

    public final EasyInjection onClick(View.OnClickListener listener, int... resIDs){
        View tempView = view(resIDs);
        tempView.setOnClickListener(listener);
        return this;
    }

    public final EasyInjection onClick(final Method target, int... resIDs){
        View.OnClickListener listener = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                try {
                    target.setAccessible(true);
                    target.invoke(holder, v);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        };
        return onClick(listener, resIDs);
    }

    public final EasyInjection onClick(String methodName, int... resIDs){
        try {
            Method method = holder.getClass().getDeclaredMethod(methodName, View.class);
            return onClick(method, resIDs);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Can't find out " + methodName + " method.", e);
        }
    }

    public final EasyInjection onItemClick(final Method target, int resID){
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    target.setAccessible(true);
                    target.invoke(holder, parent, view, position, id);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        };
        View tempView = view(resID);
        if(tempView instanceof AdapterView){
            ((AdapterView)tempView).setOnItemClickListener(listener);
        }else{
            throw new RuntimeException("The view with id(" + resID + ") is not a AdapterView.");
        }
        return this;
    }

    public final EasyInjection onItemClick(String methodName, int resID){
        try {
            Method method = holder.getClass().getMethod(methodName, AdapterView.class, View.class, Integer.class, Long.class);
            return onItemClick(method, resID);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Can't find out " + methodName + " method.", e);
        }
    }

    public final EasyInjection onLongClick(final Method target, int... resIDs){
        View.OnLongClickListener listener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                try {
                    target.setAccessible(true);
                    return (boolean) target.invoke(holder, v);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                return false;
            }
        };
        View tempView = view(resIDs);
        tempView.setOnLongClickListener(listener);
        return this;
    }

    public final EasyInjection onLongClick(String methodName, int... resIDs){
        try {
            Method method = holder.getClass().getDeclaredMethod(methodName, View.class);
            onLongClick(method, resIDs);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Can't find out method name - " + methodName, e);
        }

        return this;
    }

    public final EasyInjection onItemLongClick(final Method target, int resID){
        AdapterView.OnItemLongClickListener listener = new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    return (boolean) target.invoke(holder, parent, view, position, id);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                return false;
            }
        };
        View tempView = view(resID);
        if(tempView instanceof AdapterView){
            ((AdapterView)tempView).setOnItemLongClickListener(listener);
        }else{
            throw new RuntimeException("The view with id(" + resID + ") is not a AdapterView.");
        }
        return this;
    }

    public final EasyInjection onItemLongClick(String methodName, int resID){
        try {
            Method method = holder.getClass().getDeclaredMethod(methodName, AdapterView.class, View.class, Integer.class, Long.class);
            return onItemLongClick(method, resID);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("can't find out method of this name:" + methodName, e);
        }
    }

    private <T extends View> T findView(int... resIDs){
        if(resIDs.length <= 0){
            throw new RuntimeException("EasyInjection don't know which view you want, please give me the view's id.");
        }
        String strKey = getStrViewID(resIDs);
        View view = cacheViews.get(strKey);
        if(view == null){
            view = rootView;
            for (int resID : resIDs) {
                view = view.findViewById(resID);
                if (view == null) {
                    throw new RuntimeException("EasyInjection can't find out the view which id is " + strKey);
                }
            }
            cacheViews.put(strKey, view);
        }
        return (T)view;
    }

    private String getStrViewID(int... resIDs){
        cacheStrBuilder.delete(0, cacheStrBuilder.length());
        for(int i = 0; i < resIDs.length; i ++){
            if(cacheStrBuilder.length() > 0){
                cacheStrBuilder.append("#");
            }
            cacheStrBuilder.append(resIDs[i]);
        }
        return cacheStrBuilder.toString();
    }
}
