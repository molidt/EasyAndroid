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

import com.qinxiandiqi.easyandroid.container.interfaces.IPresenter;
import com.qinxiandiqi.easyandroid.container.interfaces.IPresenterManager;
import com.qinxiandiqi.easyandroid.container.interfaces.IViewAdapter;

/**
 * Created by Jianan on 2015/12/1.
 */
public class EasyPresenter implements IPresenter {

   private IPresenterManager mPresenterManager;
   private EasyPresenter mParent;
   private IViewAdapter mViewAAdapter;

   @Override
   public void bind(IViewAdapter viewAdapter) {
      mViewAAdapter = viewAdapter;
   }

   @Override
   public IPresenterManager getPresenterManager() {
      return mPresenterManager;
   }

   @Override
   public void setPresenterManager(IPresenterManager presenterManager) {
      mPresenterManager = presenterManager;
   }
}
