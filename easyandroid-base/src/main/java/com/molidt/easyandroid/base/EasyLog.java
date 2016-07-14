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
package com.molidt.easyandroid.base;

import android.util.Log;

import com.molidt.easyandroid.base.interfaces.LogInterface;

/**
 * Created by Jianan on 2015/9/30.
 */
public final class EasyLog {

   private static LogInterface mReal;

   public static synchronized void init(){
      mReal = new DefaultLog();
   }

   public static synchronized  void init(LogInterface realLog){
      mReal = realLog;
   }

   private EasyLog(){}

   public static void setTag(String tag){
      mReal.setTag(tag);
   }

   public static void setDebug(boolean isDebug){
      mReal.setDebug(isDebug);
   }

   public static void i(String msg){
      mReal.i(msg);
   }

   public static void i(String msg, Throwable throwable){
      mReal.i(msg, throwable);
   }

   public static void e(String msg){
      mReal.e(msg, null);
   }

   public static void e(String msg, Throwable throwable){
      mReal.e(msg, throwable);
   }

   public static void d(String msg){
      mReal.d(msg, null);
   }

   public static void d(String msg, Throwable throwable){
      mReal.d(msg, throwable);
   }

   public static void w(String msg){
      mReal.w(msg, null);
   }

   public static void w(String msg, Throwable throwable){
      mReal.w(msg, throwable);
   }

   public static void v(String msg){
      mReal.v(msg, null);
   }

   public static void v(String msg, Throwable throwable){
      mReal.v(msg, throwable);
   }

   private static class DefaultLog implements LogInterface{

      private String DEFAULT_LOG_TAG = "easyandroid";
      private boolean isDebug = true;

      public void setTag(String tag){
         DEFAULT_LOG_TAG = tag;
      }

      public void setDebug(boolean isDebug){
         this.isDebug = isDebug;
      }

      public void i(String msg){
         log(Log.INFO, msg, null);
      }

      public void i(String msg, Throwable throwable){
         log(Log.INFO, msg, throwable);
      }

      public void e(String msg){
         log(Log.ERROR, msg, null);
      }

      public void e(String msg, Throwable throwable){
         log(Log.ERROR, msg, throwable);
      }

      public void d(String msg){
         log(Log.DEBUG, msg, null);
      }

      public void d(String msg, Throwable throwable){
         log(Log.DEBUG, msg, throwable);
      }

      public void w(String msg){
         log(Log.WARN, msg, null);
      }

      public void w(String msg, Throwable throwable){
         log(Log.WARN, msg, throwable);
      }

      public void v(String msg){
         log(Log.VERBOSE, msg, null);
      }

      public void v(String msg, Throwable throwable){
         log(Log.VERBOSE, msg, throwable);
      }
      private int log(int type, String msg, Throwable throwable){
         if(isDebug){
            if(throwable != null){
               return Log.println(type, DEFAULT_LOG_TAG, msg + "\n" + Log.getStackTraceString(throwable));
            }else{
               return Log.println(type, DEFAULT_LOG_TAG, msg);
            }
         }else{
            return 0;
         }
      }
   }
}
