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

import android.support.v4.app.Fragment;

import com.qinxiandiqi.easyandroid.container.interfaces.Controller;
import com.qinxiandiqi.easyandroid.container.interfaces.ViewAdapter;

/**
 *
 * Created by Jianan on 12/8/15.
 */
public class BaseViewAdapter implements ViewAdapter{

   private Fragment mFragment;
   private Controller mController;

   @Override
   public void bindFragment(Fragment fragment) {
      mFragment = fragment;
   }

   @Override
   public void bindController(Controller controller) {
      mController = controller;
   }

   @Override
   public <T> T getValue(int key, Class<T> type) {
      return mController.getValue(key, type);
   }

   @Override
   public <T> T saveValue(int key, T value) {
      return mController.saveValue(key, value);
   }

   @Override
   public <T> T getGlobalValue(int key, Class<T> type) {
      return mController.getGlobalValue(key, type);
   }

   @Override
   public <T> T saveGlobalValue(int key, T value) {
      return mController.saveGlobalValue(key, value);
   }
}
