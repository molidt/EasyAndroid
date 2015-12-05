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

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.qinxiandiqi.easyandroid.container.interfaces.Control;
import com.qinxiandiqi.easyandroid.container.interfaces.ControllerManage;
import com.qinxiandiqi.easyandroid.container.interfaces.DataTransport;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Jianan on 12/4/15.
 */
public class ControllerManager implements ControllerManage, DataTransport {

   private volatile HashMap<Integer, Object> dataMap = new HashMap<Integer, Object>();

   @Override
   public <T> T getValue(int key, Class<T> type) {
      return null;
   }

   @Override
   public <T> T saveValue(int key, T value) {
      return null;
   }

   @Override
   public <T> T getGlobalValue(int key, Class<T> type) {
      Object value = dataMap.get(key);
      if(value.getClass() == type){
         return (T) value;
      }else {
         return null;
      }
   }

   @Override
   public <T> T saveGlobalValue(int key, T value) {
      return (T) dataMap.put(key, value);
   }

   @Override
   public void startController(Control controller) {

   }

   @Override
   public Control obtainController(Fragment fragment, Bundle args) {
      return null;
   }

   @Override
   public Control obtainController(Class<Fragment> fragmentClass, Bundle args) {
      return null;
   }

   @Override
   public Control findControllerByID(int id) {
      return null;
   }

   @Override
   public List<Control> findControllerByClass(Class<Fragment> fragmentClass) {
      return null;
   }

   @Override
   public void setAttachActivity(FragmentActivity activity) {

   }
}
