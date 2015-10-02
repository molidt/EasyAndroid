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
package com.qinxiandiqi.easyandroid.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import org.xml.sax.XMLReader;

/**
 * Created by Jianan on 9/29/15.
 */
public class HtmlTextView extends TextView{

    private HtmlClickListener mListener;

    public HtmlTextView(Context context) {
        super(context);
    }

    public HtmlTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HtmlTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setHtml(int res){
        initHtml(getContext().getString(res));
    }

    public void setHtml(String strHtml){
        initHtml(strHtml);
    }

    private void initHtml(String strHtml){
        CharSequence charSequence = Html.fromHtml(strHtml, new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                return mListener.getDrawable(source);
            }
        }, new Html.TagHandler() {
            @Override
            public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
                mListener.handleTag(opening, tag, output, xmlReader);
            }
        });

        SpannableStringBuilder builder = new SpannableStringBuilder(charSequence);
        URLSpan[] urlSpans = builder.getSpans(0, charSequence.length(), URLSpan.class);
        for(URLSpan span : urlSpans){
            int start = builder.getSpanStart(span);
            int end = builder.getSpanEnd(span);
            int flag = builder.getSpanFlags(span);
            final String link = span.getURL();
            builder.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    mListener.onClickLink(link);
                }
            }, start, end, flag);
            builder.removeSpan(span);
        }
        setText(charSequence);
        setLinksClickable(true);
        setMovementMethod(LinkMovementMethod.getInstance());
    }

    public static class HtmlClickListener{

        public void onClickLink(String link){

        }

        public Drawable getDrawable(String source) {
            return null;
        }

        public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {

        }
    }
}
