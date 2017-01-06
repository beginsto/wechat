package com.jielan.jiaxing.util.unionCard;

import java.security.MessageDigest;

/**
 * MD5摘要工具类
 * @author Hanley
 *
 */
public class DigestUitl {

    private static final String ENCODE = "UTF-8";
    private static final String ALGOR = "MD5";

    public final static String MD5LowerCase( String src ) {
        StringBuffer buf = new StringBuffer("");
        try {
            // 获取MD5摘要算法对象
            MessageDigest digest = MessageDigest.getInstance(ALGOR);
            // 使用指定的字节更新摘要
            digest.update(src.getBytes(ENCODE));
            // 获取密文
            byte[] b = digest.digest();
            //! 将密文转换成16进制的字符串形式
            int i = 0;
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) i += 256;
                if (i < 16) buf.append("0");
                buf.append(Integer.toHexString(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        System.out.println(MD5LowerCase("1234563246243643ffffdsaa"));
    }
}
