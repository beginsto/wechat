package com.jielan.jiaxing.util.unionCard;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;

public class BCDUtil
{
    public static byte[] str2cbcd(String s)
    {
        if (s.length() % 2 != 0) {
            s = s + "0";
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i += 2) {
            int high = cs[i] - '0';
            int low = cs[(i + 1)] - '0';
            baos.write(high << 4 | low);
        }
        return baos.toByteArray();
    }

    public static String cbcd2string(byte[] b) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            int h = ((b[i] & 0xFF) >> 4) + 48;
            sb.append((char)h);
            int l = (b[i] & 0xF) + 48;
            sb.append((char)l);
        }
        return sb.toString();
    }

    public static String readBcdString(DataInputStream dis, int length) throws IOException {
        if (length < 1) {
            return "";
        }
        int len = length;
        if (len % 2 != 0) {
            len++;
        }
        String str = cbcd2string(Utils.readByteArrayFromStream(dis, len / 2));
        return str.substring(0, length);
    }

    public static void main(String[] args) {
        byte[] bcd = str2cbcd("0800");
        for (int i = 0; i < bcd.length; i++) {
            System.out.println(bcd[i]);
        }
        System.out.println(str2cbcd("0810").length);
    }
}
