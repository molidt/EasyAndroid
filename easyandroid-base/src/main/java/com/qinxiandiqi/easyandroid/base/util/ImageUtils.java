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
package com.qinxiandiqi.easyandroid.base.util;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

import java.io.File;

/**
 * Created by Jianan on 2015/3/5.
 */
public class ImageUtils {


    public static BitmapFactory.Options getBitmapOption(File bitmapFile){
        return ImageUtils.getBitmapOption(bitmapFile.getAbsoluteFile());
    }

    public static BitmapFactory.Options getBitmapOption(String bitmapPath){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(bitmapPath, options);
        return options;
    }

    public static BitmapFactory.Options getBitmapOption(Resources resources, int resourceId){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, resourceId, options);
        return options;
    }

    public static int calculateInSampleSize(int srcWidth, int srcHeight, int reqWidth, int reqHeight){
        int inSampleSize = 1;
        if (srcHeight > reqHeight || srcWidth > reqWidth) {

            final int halfHeight = srcHeight / 2;
            final int halfWidth = srcWidth / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}
