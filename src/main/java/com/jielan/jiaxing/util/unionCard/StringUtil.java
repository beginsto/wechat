package com.jielan.jiaxing.util.unionCard;

/**
 * Created by Administrator on 2016/8/2.
 */
public class StringUtil
{
    public static boolean isNumber(Object str)
    {
        return (str != null) && (str.toString().matches("[0-9]+"));
    }

    public static boolean isNumLen(Object str, int min, int max, boolean isTrim)
    {
        if (isNull(str)) return false;

        if (str.toString().replace(" ", "").matches("^[\\d]{" + min + "," + max + "}$")) {
            return true;
        }
        return false;
    }

    public static boolean isNumber(Object str, int len)
    {
        return (str != null) && (str.toString().matches("[0-9]{" + len + "}"));
    }

    public static boolean isNull(Object str)
    {
        return (str == null) || (str.toString().trim().length() < 1);
    }

    public static String hideMobile(String mobile)
    {
        return hide(mobile, '*', 3, 7);
    }

    public static String hideCard(String card)
    {
        if ((card == null) || (card.length() < 4)) return card;

        return hide(card, '*', card.length() - 12, card.length() - 4);
    }

    public static String hideName(String name)
    {
        return hide(name, '*', 0, 1);
    }

    public static String hide(String replaceFrom, char replaceTo, int start, int end)
    {
        StringBuilder hidden = new StringBuilder();

        if ((replaceFrom == null) || (end < 0)) return "";

        if (start < 0) start = 0;

        if (end > replaceFrom.length()) end = replaceFrom.length();

        hidden.append(replaceFrom.substring(0, start));

        for (int i = start; i < end; i++) {
            hidden.append(replaceTo);
        }

        hidden.append(replaceFrom.substring(end, replaceFrom.length()));

        return hidden.toString();
    }

    public static boolean isNullArray(Object[] arr)
    {
        return (arr == null) || (arr.length < 1);
    }

    public static boolean isLen(Object str, int min, int max, boolean isTrim)
    {
        if (isNull(str)) return false;

        if (isTrim) {
            str = str.toString().trim();
        }

        if (((min != -1) && (str.toString().length() < min)) || ((max != -1) && (str.toString().length() > max))) {
            return false;
        }
        return true;
    }

    public static boolean isSmsLen(String msg)
    {
        return isLen(msg, -1, 70, false);
    }

    public static String nullToStr(Object obj)
    {
        return nullToStr(obj, true);
    }

    public static String nullToStr(Object obj, boolean trim)
    {
        return trim ? obj.toString().trim() : isNull(obj) ? "" : obj.toString();
    }

    public static String format(String src, String plac, Object[] param)
    {
        if ((isNull(src)) || (isNullArray(param))) return "";

        int len = param.length;
        for (int i = 0; i < len; i++)
        {
            if (len < i) return plac;

            if (isNull(param[i]))
                src = src.replaceFirst(plac, "");
            else {
                src = src.replaceFirst(plac, param[i].toString());
            }
        }
        return src;
    }

    public static boolean isEmpty(String str)
    {
        return isNull(str);
    }

    public static void main(String[] args) {
        System.out.println(isNumLen("595", 1, 8, true));
    }

    public static String jiami(String mobile, String pwd, String pinkey) {
        mobile = "000000" + mobile.substring(0, 10);

        pwd = "06" + pwd + "FFFFFFFF";

        String str = Utils.yihuo(pwd, mobile);

        str = DesUtil.CBCEncryptString(str, pinkey);

        return str;
    }

    public static boolean isHaveStr(String str, String target)
    {
        if (str.indexOf(target) != -1) {
            return true;
        }
        return false;
    }

    public static String buling(String str, String num)
    {
        return String.format("%0" + num + "d", new Object[] { Integer.valueOf(Integer.parseInt(str)) });
    }
}
