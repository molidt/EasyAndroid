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

/**
 * This interface defend the data transporting rule.
 * Created by Jianan on 2015/11/30.
 */
public interface IDataTransport {

   /**
    * Get the local value by key.
    * @param key the key of the target value
    * @param type the class of the target value
    * @param <T> the type of the target value
    * @return
    */
   <T> T getValue(int key, Class<T> type);

   /**
    * Save the local value using a key.
    * @param key the key of the saving value.
    * @param value the saving value
    * @param <T> the type fo the saving value.
    * @return
    */
   <T> T saveValue(int key, T value);

   /**
    * Get the global value by key.
    * @param key the key of the target value.
    * @param type the class of the target value.
    * @param <T> the type of the target value.
    * @return
    */
   <T> T getGlobalValue(int key, Class<T> type);

   /**
    * Saving the global value using a key.
    * @param key the key of the saving value
    * @param value the saving value.
    * @param <T> the type of the saving value.
    * @return
    */
   <T> T saveGlobalValue(int key, T value);
}
