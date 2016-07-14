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
package com.molidt.easyandroid.container.interfaces;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import java.util.List;

/**
 * Created by Jianan on 2015/12/2.
 */
public interface IPresenterManager {

   /**
    * Start to run the presenter.
    * @param presenter the target presenter.
    */
   void startPresenter(IPresenter presenter);

   /**
    * Obtain a presenter by the target fragment and its args.
    * @param fragment the target fragment
    * @param args the fragment's args
    * @return
    */
   IPresenter obtainPresenter(Fragment fragment, Bundle args);

   /**
    * Obtain a presenter by the target fragment class and its args.
    * @param fragmentClass the target fragment's class
    * @param args the target fragment's args
    * @return
    */
   IPresenter obtainPresenter(Class<Fragment> fragmentClass, Bundle args);

   /**
    * find a running presenter by its id
    * @param id the id of the running presenter
    * @return
    */
   IPresenter findPresenterByID(int id);

   /**
    * find a running presenter list group by the presenter's target fragment class.
    * @param fragmentClass
    * @return
    */
   List<IPresenter> findPresenterByClass(Class<Fragment> fragmentClass);

   /**
    * A EasyPresenterManager should attach to an activity, this method set the attaching activity.
    * @param activity
    */
   void setAttachActivity(FragmentActivity activity);

   void setModelManagerService(IModelManagerService modelManagerService);
   IModelManagerService getModelManagerService();
}
