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

import com.qinxiandiqi.easyandroid.container.interfaces.Control;
import com.qinxiandiqi.easyandroid.container.interfaces.ControllerManager;
import com.qinxiandiqi.easyandroid.container.interfaces.DataTransport;

/**
 * Created by Jianan on 11/29/15.
 */
public class BaseFragment extends Fragment implements DataTransport, ControllerManager {
   @Override
   public <T> T getValue(int key, Class<T> type) {
      if(getActivity() != null && getActivity() instanceof DataTransport){
         return ((DataTransport) getActivity()).getValue(key, type);
      }else {
         return null;
      }
   }

   @Override
   public <T> T saveValue(int key, T value) {
      if(getActivity() != null && getActivity() instanceof DataTransport){
         return ((DataTransport) getActivity()).saveValue(key, value);
      }else{
         return null;
      }
   }

   @Override
   public void startController(Control controller) {
      if(getActivity() != null && getActivity() instanceof ControllerManager){
         ((ControllerManager) getActivity()).startController(controller);
      }
   }
}
