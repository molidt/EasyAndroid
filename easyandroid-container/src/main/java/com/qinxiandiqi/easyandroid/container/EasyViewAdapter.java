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

import com.qinxiandiqi.easyandroid.container.interfaces.Presenter;
import com.qinxiandiqi.easyandroid.container.interfaces.ViewAdapter;

/**
 *
 * Created by Jianan on 12/8/15.
 */
public class EasyViewAdapter implements ViewAdapter{

   private Fragment mFragment;
   private Presenter mPresenter;

   @Override
   public void bindFragment(Fragment fragment) {
      mFragment = fragment;
   }

   @Override
   public void bindPresenter(Presenter presenter) {
      mPresenter = presenter;
   }

   @Override
   public <T> T getValue(int key, Class<T> type) {
      return mPresenter.getValue(key, type);
   }

   @Override
   public <T> T saveValue(int key, T value) {
      return mPresenter.saveValue(key, value);
   }

   @Override
   public <T> T getGlobalValue(int key, Class<T> type) {
      return mPresenter.getGlobalValue(key, type);
   }

   @Override
   public <T> T saveGlobalValue(int key, T value) {
      return mPresenter.saveGlobalValue(key, value);
   }
}
