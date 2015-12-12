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
package com.qinxiandiqi.easyandroid.base.interfaces;

/**
 * Created by Jianan on 2015/10/24.
 */
public interface LogInterface {


    void setTag(String tag);

    void setDebug(boolean isDebug);

    void i(String msg);

    void i(String msg, Throwable throwable);

    void e(String msg);

    void e(String msg, Throwable throwable);

    void d(String msg);

    void d(String msg, Throwable throwable);

    void w(String msg);

    void w(String msg, Throwable throwable);

    void v(String msg);

    void v(String msg, Throwable throwable);
}
