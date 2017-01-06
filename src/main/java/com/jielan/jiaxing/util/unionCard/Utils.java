package com.jielan.jiaxing.util.unionCard;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Utils {
    private static int sequence = 0;

    public static synchronized int getSequence() {
        if (sequence >= 2147483647) {
            sequence = 0;
        }
        sequence += 1;
        return sequence;
    }

    public static String byteArr2HexStr(byte[] arrB) {
        if (arrB == null) {
            return "";
        }
        int iLen = arrB.length;

        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];

            while (intTmp < 0) {
                intTmp += 256;
            }

            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }

    public static byte[] hexStr2ByteArr(String strIn) {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;

        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i += 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[(i / 2)] = ((byte) Integer.parseInt(strTmp, 16));
        }
        return arrOut;
    }

    public static String bin2Str(byte[] data, int len) {
        String tmp = "";

        for (int i = 0; i < len; i++) {
            int itmp = data[i] & 0xFF;
            String sbyte = Integer.toBinaryString(itmp);
            if (sbyte.length() < 8) {
                sbyte = replicate("0", 8 - sbyte.length()) + sbyte;
            }
            tmp = tmp + sbyte;
        }
        return tmp;
    }

    public static String replicate(String rep, int num) {
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= num; i++) {
            sb.append(rep);
        }
        return sb.toString();
    }

    public static String GenMac(byte[] rbdata, int idatalen, byte[] bkey) {
        byte[] bdata = new byte[idatalen - 11];
        System.arraycopy(rbdata, 11, bdata, 0, idatalen - 11);

        byte[] btmp1 = new byte[8];
        byte[] btmp2 = new byte[8];
        byte[] btmp3 = new byte[8];

        int ilen = idatalen - 11;

        int imod = ilen % 8;
        int icnt = ilen / 8;
        if (imod != 0)
            icnt = (icnt + 1) * 8;
        else {
            icnt *= 8;
        }
        byte[] btmp = new byte[icnt];
        System.arraycopy(bdata, 0, btmp, 0, ilen);

        System.arraycopy(btmp, 0, btmp1, 0, 8);

        for (int i = 1; i < icnt / 8; i++) {
            System.arraycopy(btmp, i * 8, btmp2, 0, 8);
            for (int j = 0; j < 8; j++) {
                btmp1[j] = ((byte) (btmp1[j] ^ btmp2[j]));
            }
        }
        String shex = Hex2Str(btmp1, 8);

        btmp1 = Str2Asc(shex.substring(0, 8));

        btmp2 = Str2Asc(shex.substring(8, 16));

        btmp3 = DesUtil.CBCEncrypt(btmp1, bkey);

        for (int j = 0; j < 8; j++) {
            btmp1[j] = ((byte) (btmp3[j] ^ btmp2[j]));
        }

        btmp3 = DesUtil.CBCEncrypt(btmp1, bkey);

        String sret = Hex2Str(btmp3, 8);
        return sret.substring(0, 8);
    }

    public static String genYouHuiQuanMac(byte[] rbdata, int idatalen, byte[] bkey) {
        byte[] bdata = new byte[idatalen - 5];
        System.arraycopy(rbdata, 5, bdata, 0, idatalen - 5);

        byte[] btmp1 = new byte[8];
        byte[] btmp2 = new byte[8];
        byte[] btmp3 = new byte[8];

        int ilen = idatalen - 5;

        int imod = ilen % 8;
        int icnt = ilen / 8;
        if (imod != 0)
            icnt = (icnt + 1) * 8;
        else {
            icnt *= 8;
        }
        byte[] btmp = new byte[icnt];
        System.arraycopy(bdata, 0, btmp, 0, ilen);

        System.arraycopy(btmp, 0, btmp1, 0, 8);

        for (int i = 1; i < icnt / 8; i++) {
            System.arraycopy(btmp, i * 8, btmp2, 0, 8);
            for (int j = 0; j < 8; j++) {
                btmp1[j] = ((byte) (btmp1[j] ^ btmp2[j]));
            }
        }
        String shex = Hex2Str(btmp1, 8);

        btmp1 = Str2Asc(shex.substring(0, 8));

        btmp2 = Str2Asc(shex.substring(8, 16));

        btmp3 = DesUtil.CBCEncrypt(btmp1, bkey);

        for (int j = 0; j < 8; j++) {
            btmp1[j] = ((byte) (btmp3[j] ^ btmp2[j]));
        }

        btmp3 = DesUtil.CBCEncrypt(btmp1, bkey);

        String sret = Hex2Str(btmp3, 8);
        return sret.substring(0, 8);
    }

    public static byte[] Str2Asc(String s) {
        if ((s == null) || ("".equals(s))) {
            return null;
        }

        char[] chars = s.toCharArray();
        byte[] asciiArray = new byte[chars.length];

        for (int i = 0; i < chars.length; i++) {
            asciiArray[i] = ((byte) chars[i]);
        }
        return asciiArray;
    }

    public static String Hex2Str(byte[] b, int len) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < len; n++) {
            stmp = Integer.toHexString(b[n] & 0xFF);
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs.toUpperCase();
    }

    public static String getOctetStringRightFillWithSpace(String str, int length) {
        if (length < 1) {
            return "";
        }
        StringBuffer sb = new StringBuffer("");
        int strLength = 0;
        if (str == null) {
            sb.append("");
        } else {
            sb.append(str);
            strLength = str.getBytes().length;
        }
        if (strLength > length) {
            return str;
        }
        for (int i = 0; i < length - strLength; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    public static String getOctetStringLeftFillWithZero(String str, int length) {
        if (length < 1) {
            return "";
        }
        StringBuffer sb = new StringBuffer("");
        int strLength = 0;
        if (str == null) {
            sb.append("");
        } else {
            sb.append(str);
            strLength = str.getBytes().length;
        }
        if (strLength > length) {
            return str;
        }
        for (int i = 0; i < length - strLength; i++) {
            sb.insert(0, "0");
        }
        return sb.toString();
    }

    public static byte[] readByteArrayFromStream(DataInputStream dis, int length) throws IOException {
        if (length < 1) {
            return null;
        }
        byte[] b = new byte[length];
        dis.readFully(b);
        return b;
    }

    public static String readString(DataInputStream dis, int length) throws IOException {
        return new String(readByteArrayFromStream(dis, length), "GBK");
    }

    public static int readLength(DataInputStream dis, int length) throws IOException {
        return Integer.parseInt(BCDUtil.readBcdString(dis, length));
    }

    public static byte[] getBcdLength(int length, int len) {
        String str = getOctetStringLeftFillWithZero(String.valueOf(length), len);
        return BCDUtil.str2cbcd(str);
    }

    public static String getWorkKey() {
        byte[] workKey = new byte[8];
        for (int i = 0; i < workKey.length; i++) {
            workKey[i] = ((byte) (int) (Math.random() * 256.0D));
        }
        return byteArr2HexStr(workKey);
    }

    public static String yihuo(String hex_a, String hex_b) {
        if ((hex_a == null) || (hex_b == null) || (hex_a.length() != hex_b.length())) {
            return null;
        }
        byte[] a = hexStr2ByteArr(hex_a);
        byte[] b = hexStr2ByteArr(hex_b);
        byte[] c = new byte[a.length];
        for (int i = 0; i < a.length; i++) {
            c[i] = ((byte) (a[i] ^ b[i]));
        }
        return byteArr2HexStr(c);
    }

    public static String getPasswd(String y52, String zhanghao, String pinkey) {
        if ((StringUtil.isEmpty(y52)) || (StringUtil.isEmpty(zhanghao)) || (StringUtil.isEmpty(pinkey))) {
            return null;
        }
        String yihuo = DesUtil.CBCDecryptString(y52, pinkey);
        zhanghao = "0000000000000" + zhanghao;
        String pan = "0000" + zhanghao.substring(zhanghao.length() - 13, zhanghao.length() - 1);
        String password = yihuo(yihuo, pan);
        int length = Integer.parseInt(password.substring(0, 2));
        return password.substring(2, length + 2);
    }

    public static String getPasswds(String y52, String zhanghao, String pinkey) {
        if ((StringUtil.isEmpty(y52)) || (StringUtil.isEmpty(zhanghao)) || (StringUtil.isEmpty(pinkey))) {
            return null;
        }
        String yihuo = DesUtil.CBCDecryptString(y52, pinkey);
        zhanghao = "0000000000000" + zhanghao;
        String pan = "0000" + zhanghao.substring(zhanghao.length() - 13, zhanghao.length() - 1);
        String password = yihuo(yihuo, pan);
        int length = Integer.parseInt(password.substring(0, 2));
        return password.substring(2, length + 2);
    }

    public static List<Byte> byteArr2List(byte[] bs, int len) {
        List list = new ArrayList();
        for (int i = 0; i < len; i++) {
            list.add(Byte.valueOf(bs[i]));
        }
        return list;
    }

    public static byte[] list2ByteArr(List<Byte> list) {
        Byte[] bs = (Byte[]) list.toArray(new Byte[list.size()]);
        byte[] bs2 = new byte[bs.length];
        for (int i = 0; i < bs.length; i++) {
            bs2[i] = bs[i].byteValue();
        }

        return bs2;
    }

    public static String sortParamForSign(Map<String, String> params) {
        List<String> keyList = Arrays.asList((String[]) params.keySet().toArray(new String[params.size()]));
        Collections.sort(keyList);

        StringBuilder sb = new StringBuilder();
        for (String k : keyList) {
            if (!"sign".equals(k)) {
                sb.append(k).append("=").append((String) params.get(k)).append("&");
            }
        }
        if (params.size() > 1) {
            sb.delete(sb.length() - "&".length(), sb.length());
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    public static String BinstrToStr(String binStr) {
        String[] tempStr = StrToStrArray(binStr);
        char[] tempChar = new char[tempStr.length];
        for (int i = 0; i < tempStr.length; i++) {
            tempChar[i] = BinstrToChar(tempStr[i]);
        }
        return String.valueOf(tempChar);
    }

    public String BinstrToBinstr16(String input) {
        StringBuffer output = new StringBuffer();
        String[] tempStr = StrToStrArray(input);
        for (int i = 0; i < tempStr.length; i++) {
            for (int j = 16 - tempStr[i].length(); j > 0; j--)
                output.append('0');
            output.append(tempStr[i] + " ");
        }
        return output.toString();
    }

    public static String[] StrToStrArray(String str) {
        return str.split(" ");
    }

    public static char BinstrToChar(String binStr) {
        int[] temp = BinstrToIntArray(binStr);
        int sum = 0;
        for (int i = 0; i < temp.length; i++) {
            sum += (temp[(temp.length - 1 - i)] << i);
        }
        return (char) sum;
    }

    public static int[] BinstrToIntArray(String binStr) {
        char[] temp = binStr.toCharArray();
        int[] result = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            temp[i] -= '0';
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(getWorkKey());
    }
}
