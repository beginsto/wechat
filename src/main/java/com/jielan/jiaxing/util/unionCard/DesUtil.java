package com.jielan.jiaxing.util.unionCard;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class DesUtil
{
    public static String CBCEncryptString(String strData)
    {
        if (!StringUtil.isEmpty(strData)) {
            while (strData.length() < 16) {
                strData = strData + "0";
            }
        }
        byte[] data = CBCEncrypt(str2ByteArr(strData));
        return byte2hex(data);
    }

    public static String CBCEncryptString(String strData, String key) {
        if (!StringUtil.isEmpty(strData)) {
            while (strData.length() < 16) {
                strData = strData + "0";
            }
        }
        byte[] data = CBCEncrypt(str2ByteArr(strData), str2ByteArr(key));
        return byte2hex(data);
    }

    public static byte[] CBCEncrypt(byte[] data, byte[] key) {
        return CBCEncrypt(data, key, new byte[8]);
    }

    public static byte[] CBCEncrypt(byte[] data) {
        return CBCEncrypt(data, new byte[] { -118, -77, 97, 90, -68, -40, 44, 45 });
    }

    public static byte[] CBCEncrypt(byte[] data, byte[] key, byte[] iv)
    {
        try
        {
            DESKeySpec dks = new DESKeySpec(key);

            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");

            SecretKey secretKey = keyFactory.generateSecret(dks);

            Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");

            IvParameterSpec param = new IvParameterSpec(iv);

            cipher.init(1, secretKey, param);

            return cipher.doFinal(data);
        }
        catch (Exception e)
        {
            System.err.println("DES算法，加密数据出错!");

            e.printStackTrace();
        }

        return null;
    }

    public static String byte2hex(byte[] b)
    {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++)
        {
            stmp = Integer.toHexString(b[n] & 0xFF);
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs.toUpperCase();
    }

    public static byte[] hex2byte(byte[] b) {
        if (b.length % 2 != 0)
            throw new IllegalArgumentException("长度不是偶数");
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);

            b2[(n / 2)] = ((byte)Integer.parseInt(item, 16));
        }

        return b2;
    }

    public static byte[] str2ByteArr(String strIn) {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;

        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i += 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[(i / 2)] = ((byte)Integer.parseInt(strTmp, 16));
        }
        return arrOut;
    }

    public static String CBCDecryptString(String strData)
    {
        if (!StringUtil.isEmpty(strData)) {
            while (strData.length() < 16) {
                strData = strData + "0";
            }
        }
        byte[] data = CBCDecrypt(str2ByteArr(strData));
        return byte2hex(data);
    }

    public static String CBCDecryptString(String strData, String key) {
        if (!StringUtil.isEmpty(strData)) {
            while (strData.length() < 16) {
                strData = strData + "0";
            }
        }
        byte[] data = CBCDecrypt(str2ByteArr(strData), str2ByteArr(key));
        return byte2hex(data);
    }

    public static byte[] CBCDecrypt(byte[] data) {
        return CBCEncrypt(data, new byte[] { -118, -77, 97, 90, -68, -40, 44, 45 });
    }

    public static byte[] CBCDecrypt(byte[] data, byte[] key) {
        return CBCDecrypt(data, key, new byte[8]);
    }

    public static byte[] CBCDecrypt(byte[] data, byte[] key, byte[] iv)
    {
        return CBCDecrypt(false, data, key, iv);
    }

    public static byte[] CBCDecrypt(boolean isEn, byte[] data, byte[] key, byte[] iv)
    {
        try
        {
            DESKeySpec dks = new DESKeySpec(key);

            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(dks);

            Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");

            IvParameterSpec param = new IvParameterSpec(iv);
            if (isEn)
                cipher.init(1, secretKey, param);
            else {
                cipher.init(2, secretKey, param);
            }

            return cipher.doFinal(data);
        }
        catch (Exception e)
        {
            System.err.println("DES算法，解密出错。");
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args)
    {
        String data = CBCEncryptString("2FF", "6543219876543210");
        System.out.println("encrypt:" + data);
        data = CBCDecryptString("D6E05EC9CFEC3041", "6543219876543210");
        System.out.println("decrypt:" + data);
    }
}
