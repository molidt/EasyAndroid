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

import com.qinxiandiqi.easyandroid.container.interfaces.IDataTransport;
import com.qinxiandiqi.easyandroid.container.interfaces.IModelManagerService;
import com.qinxiandiqi.easyandroid.container.interfaces.IPresenter;
import com.qinxiandiqi.easyandroid.container.interfaces.IPresenterManager;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jianan on 2015/12/4.
 */
public class EasyPresenterManager implements IPresenterManager, IDataTransport {

   private static volatile EasyPresenterManager sEasyPresenterManager;
   private volatile HashMap<Integer, Object> mDataMap = new HashMap<Integer, Object>();
   private volatile WeakReference<FragmentActivity> mCacheActivity;
   private volatile PresenterStack mPresenterStack = new PresenterStack();
   private volatile IModelManagerService mModelManagerService;

   private EasyPresenterManager(){}

   public static final EasyPresenterManager init(FragmentActivity act){
      if(sEasyPresenterManager == null){
         sEasyPresenterManager = new EasyPresenterManager();
         sEasyPresenterManager.setAttachActivity(act);
      }
      return sEasyPresenterManager;
   }

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
      Object value = mDataMap.get(key);
      if(value.getClass() == type){
         return (T) value;
      }else {
         return null;
      }
   }

   @Override
   public <T> T saveGlobalValue(int key, T value) {
      return (T) mDataMap.put(key, value);
   }

   @Override
   public void startPresenter(IPresenter presenter) {
      mPresenterStack.push(presenter);
   }

   @Override
   public IPresenter obtainPresenter(Fragment fragment, Bundle args) {
      return null;
   }

   @Override
   public IPresenter obtainPresenter(Class<Fragment> fragmentClass, Bundle args) {
      return null;
   }

   @Override
   public IPresenter findPresenterByID(int id) {
      return null;
   }

   @Override
   public List<IPresenter> findPresenterByClass(Class<Fragment> fragmentClass) {
      return null;
   }

   @Override
   public void setAttachActivity(FragmentActivity activity) {
      if(mCacheActivity == null || mCacheActivity.get() == null || mCacheActivity.get().isFinishing()){
         mCacheActivity = new WeakReference<FragmentActivity>(activity);
      }else{
         throw new RuntimeException("EasyPresenterManager shouldn't be attached to more then one activity.");
      }
   }

   @Override
   public void setModelManagerService(IModelManagerService modelManagerService) {
      mModelManagerService = modelManagerService;
   }

   @Override
   public IModelManagerService getModelManagerService() {
      return mModelManagerService;
   }

}
