package com.jielan.jiaxing.util;

import org.apache.commons.lang.StringUtils;
/**
 * Created by Administrator on 2016/7/6.
 */
public class CharacterEncoding {
    public static String stringToUnicode(String str) {

        StringBuffer temp = new StringBuffer();

        for (int i = 0; i < str.length(); i++) {

            // 取出每一个字符
            char c = str.charAt(i);

            // 转换为unicode
            temp.append("\\u" + Integer.toHexString(c));
        }

        return temp.toString();
    }

    public static String unicodeToString(String unicode) {

        StringBuffer string = new StringBuffer();

        String[] hex = unicode.split("\\\\u");

        for (int i = 1; i < hex.length; i++) {

            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);

            // 追加成string
            string.append((char) data);
        }

        return string.toString();
    }
    public static void main(String[] args) {
        String test = "测试";

        String unicode = stringToUnicode(test);

        String string = unicodeToString(unicode) ;

        System.out.println(unicode);

        System.out.println(string);

    }
}
