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
package com.qinxiandiqi.easyandroid.base;

/**
 * Created by Jianan on 2015/10/25.
 */
public class EasyParser {

    public static long parseLongFromStr(String value, long defValue){
        try {
            return Long.parseLong(value);
        }catch (NumberFormatException ex){
            return defValue;
        }
    }

    public static int parseIntFromStr(String value, int defValue){
        try {
            return Integer.parseInt(value);
        }catch (NumberFormatException ex){
            return defValue;
        }
    }

    public static float parseFloatFromStr(String value, float defValue){
        try {
            return Float.parseFloat(value);
        }catch (NumberFormatException ex){
            return defValue;
        }
    }

    public static double parseDoubleFromStr(String value, double defValue){
        try {
            return Double.parseDouble(value);
        }catch (NumberFormatException ex){
            return defValue;
        }
    }
}
