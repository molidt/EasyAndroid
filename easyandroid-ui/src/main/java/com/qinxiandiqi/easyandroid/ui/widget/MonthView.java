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
package com.qinxiandiqi.easyandroid.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Jianan on 2015/12/24.
 */
public class MonthView extends View {

   private int mYear, mMonth;
   private int mCellLength;

   public MonthView(Context context) {
      super(context);
   }

   public MonthView(Context context, AttributeSet attrs) {
      super(context, attrs);
   }

   public MonthView(Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
   }

   @TargetApi(Build.VERSION_CODES.LOLLIPOP)
   public MonthView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
      super(context, attrs, defStyleAttr, defStyleRes);
   }

   @Override
   protected void onDraw(Canvas canvas) {
      super.onDraw(canvas);
   }

   private boolean isLeapYear(int year){
      if(year % 400 == 0){
         return true;
      }
      if(year % 100 == 0){
         return false;
      }
      if(year % 4 == 0){
         return true;
      }
      return false;
   }

   public interface Cell{
      void drawCell(Canvas canvas);
   }

   public static class WeekCell implements Cell{

      @Override
      public void drawCell(Canvas canvas) {

      }
   }

   public static class DayCell implements Cell{

      @Override
      public void drawCell(Canvas canvas) {

      }
   }
}
