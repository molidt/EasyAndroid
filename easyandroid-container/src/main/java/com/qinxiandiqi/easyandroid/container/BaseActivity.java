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
package com.qinxiandiqi.easyandroid.container;

import android.support.v4.app.FragmentActivity;

import com.qinxiandiqi.easyandroid.container.interfaces.DataTransport;

import java.util.HashMap;

/**
 * Created by Jianan on 11/28/15.
 */
public class BaseActivity extends FragmentActivity implements DataTransport{

   private HashMap<Integer, Object> dataMap = new HashMap<Integer, Object>();

   @Override
   public <T> T getValue(int key, Class<T> type) {
      Object value = dataMap.get(key);
      if(value.getClass() == type){
         return (T) value;
      }else {
         return null;
      }
   }

   @Override
   public <T> T saveValue(int key, T value) {
      return (T) dataMap.put(key, value);
   }
}
