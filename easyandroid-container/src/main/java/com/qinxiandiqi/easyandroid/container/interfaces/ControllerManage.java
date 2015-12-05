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
package com.qinxiandiqi.easyandroid.container.interfaces;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import java.util.List;

/**
 * Created by Jianan on 12/2/15.
 */
public interface ControllerManage {

   /**
    * Start to run the controller.
    * @param controller the target controller.
    */
   void startController(Control controller);

   /**
    * Obtain a controller by the target fragment and its args.
    * @param fragment the target fragment
    * @param args the fragment's args
    * @return
    */
   Control obtainController(Fragment fragment, Bundle args);

   /**
    * Obtain a controller by the target fragment class and its args.
    * @param fragmentClass the target fragment's class
    * @param args the target fragment's args
    * @return
    */
   Control obtainController(Class<Fragment> fragmentClass, Bundle args);

   /**
    * find a running controller by its id
    * @param id the id of the running controller
    * @return
    */
   Control findControllerByID(int id);

   /**
    * find a running controller list group by the controller's target fragment class.
    * @param fragmentClass
    * @return
    */
   List<Control> findControllerByClass(Class<Fragment> fragmentClass);

   /**
    * A ControllerManager should attach to an activity, this method set the attaching activity.
    * @param activity
    */
   void setAttachActivity(FragmentActivity activity);
}
