package com.qinxiandiqi.easyandroid.base;

import android.test.InstrumentationTestCase;

import org.junit.Test;

/**
 * Created by Jianan on 10/28/15.
 */
public class EasyParserTest extends InstrumentationTestCase {

    @Test
    public void testParseLongFromStr(){
        assertEquals(EasyParser.parseLongFromStr("123", 321L), 123L);
        assertEquals(EasyParser.parseLongFromStr("drafad", 321L), 321L);
        assertEquals(EasyParser.parseLongFromStr("-123", 321L), -123L);
        assertEquals(EasyParser.parseLongFromStr("123", 321L), 123);
    }

    @Test
    public void testParseIntFromStr(){
        assertEquals(EasyParser.parseIntFromStr("213", 123), 213);
        assertEquals(EasyParser.parseIntFromStr("asdfa", 123), 123);
        assertEquals(EasyParser.parseIntFromStr("-34", 123), -34);
        assertEquals(EasyParser.parseIntFromStr("23.3", 123), 123);
        assertEquals(EasyParser.parseIntFromStr("23.00", 123), 123);
    }

    @Test
    public void testParseDoubleFromStr(){
        assertEquals(EasyParser.parseDoubleFromStr("23.232323", 0.11), 23.232323);
        assertEquals(EasyParser.parseDoubleFromStr("sdfas", 2), 2D);
        assertEquals(EasyParser.parseDoubleFromStr("-23.55", 0), -23.55);
        assertEquals(EasyParser.parseDoubleFromStr("23.00", 0), 23.00);
    }
}
