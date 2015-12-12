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
 * Created by Jianan on 2015/9/29.
 */
public class HtmlTextView extends TextView{

    private HtmlListener mListener;
    private Html.ImageGetter mImageGatter;
    private Html.TagHandler mTagHandler;

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
        initHtml(getContext().getString(res), null);
    }

    public void setHtml(int res, HtmlListener listener){
        initHtml(getContext().getString(res), listener);
    }

    public void setHtml(String strHtml){
        initHtml(strHtml, null);
    }

    public void setHtml(String strHtml, HtmlListener listener){
        initHtml(strHtml, listener);
    }

    public void setHtmlListener(HtmlListener listener){
        if(listener != null){
            mListener = listener;
        }
        if(mImageGatter == null){
            mImageGatter = new Html.ImageGetter() {
                @Override
                public Drawable getDrawable(String source) {
                    if(mListener != null){
                        return mListener.getDrawable(source);
                    }else {
                        return null;
                    }
                }
            };
        }
        if(mTagHandler == null){
            mTagHandler = new Html.TagHandler() {
                @Override
                public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
                    if(mListener != null){
                        mListener.handleTag(opening, tag, output, xmlReader);
                    }
                }
            };
        }
    }

    private void initHtml(String strHtml, HtmlListener listener){
        setHtmlListener(listener);

        CharSequence charSequence = Html.fromHtml(strHtml, mImageGatter, mTagHandler);
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
                    if (mListener != null) {
                        mListener.onClickLink(link);
                    }
                }
            }, start, end, flag);
            builder.removeSpan(span);
        }

        setLinksClickable(true);
        setMovementMethod(LinkMovementMethod.getInstance());
        setText(charSequence);
    }

    public static class HtmlListener {

        /**
         * when click <a> tag, this method would be invoked.
         * @param link
         */
        public void onClickLink(String link){

        }

        /**
         * @param source
         * @return
         */
        public Drawable getDrawable(String source) {
            return null;
        }

        /**
         * @param opening
         * @param tag
         * @param output
         * @param xmlReader
         */
        public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {

        }
    }
}
