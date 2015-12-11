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

import com.qinxiandiqi.easyandroid.container.interfaces.Presenter;
import com.qinxiandiqi.easyandroid.container.interfaces.PresenterManager;
import com.qinxiandiqi.easyandroid.container.interfaces.ViewAdapter;

/**
 * Created by Jianan on 12/1/15.
 */
public class EasyPresenter implements Presenter {

   private PresenterManager mPresenterManager;
   private EasyPresenter mParent;
   private ViewAdapter mViewAAdapter;

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
      return null;
   }

   @Override
   public <T> T saveGlobalValue(int key, T value) {
      return null;
   }

   @Override
   public void bind(ViewAdapter viewAdapter) {
      mViewAAdapter = viewAdapter;
   }

   @Override
   public PresenterManager getPresenterManager() {
      return mPresenterManager;
   }

   @Override
   public void setPresenterManager(PresenterManager presenterManager) {
      mPresenterManager = presenterManager;
   }
}