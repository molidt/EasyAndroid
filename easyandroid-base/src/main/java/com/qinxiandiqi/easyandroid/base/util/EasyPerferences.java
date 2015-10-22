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
package com.qinxiandiqi.easyandroid.base.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import java.util.Set;

/**
 * Created by Jianan on 10/22/15.
 */
public class EasyPerferences {

    private SharedPreferences mInstance;
    private SharedPreferences.Editor mEditor;
    private boolean isTransaction = false;

    private EasyPerferences(Context ctx, String name){
        this(ctx, name, Context.MODE_PRIVATE);
    }

    private EasyPerferences(Context ctx, String name, int mode){
        mInstance = ctx.getApplicationContext().getSharedPreferences(name, mode);
        mEditor = mInstance.edit();
        isTransaction = false;
    }

    public EasyPerferences startTransaction(){
        isTransaction = true;
        return this;
    }

    private EasyPerferences tryCommit(){
        if(!isTransaction){
            mEditor.commit();
        }
        return this;
    }

    public EasyPerferences commit(){
        isTransaction = false;
        return tryCommit();
    }

    public EasyPerferences putInt(String key, int value){
        mEditor.putInt(key, value);
        return tryCommit();
    }

    public EasyPerferences putString(String key, String value){
        mEditor.putString(key, value);
        return tryCommit();
    }

    public EasyPerferences putBoolean(String key, boolean value){
        mEditor.putBoolean(key, value);
        return tryCommit();
    }

    public EasyPerferences putLong(String key, long value){
        mEditor.putLong(key, value);
        return tryCommit();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public EasyPerferences putStringSet(String key, Set<String> value){
        mEditor.putStringSet(key, value);
        return tryCommit();
    }

    public EasyPerferences putFloat(String key, float value){
        mEditor.putFloat(key, value);
        return tryCommit();
    }

    public EasyPerferences remove(String key){
        mEditor.remove(key);
        return tryCommit();
    }

    public EasyPerferences clear(){
        mEditor.clear();
        return tryCommit();
    }

    public int getInt(String key, int defValue){
        return mInstance.getInt(key, defValue);
    }

    public String getString(String key, String defValue){
        return mInstance.getString(key, defValue);
    }

    public boolean getBoolean(String key, boolean defValue){
        return mInstance.getBoolean(key, defValue);
    }

    public long getLong(String key, long defValue){
        return mInstance.getLong(key, defValue);
    }

    public float getFloat(String key, float defValue){
        return mInstance.getFloat(key, defValue);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public Set<String> getStringSet(String key, Set<String> defValue){
        return mInstance.getStringSet(key, defValue);
    }

}
