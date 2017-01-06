package com.jielan.jiaxing.util;

/**
 * Created by Administrator on 2016/7/5.
 */
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class UpopPayPage
{
    public static final String ENCODE = "UTF-8";
    private static MessageDigest md5;
    private static String LOCK_MD5 = "#L__lock_md5L#";
    public static SimpleDateFormat ORDER_PARAM_SDF;
    public static final String ORDER_PARAM_SDF_FORMAT = "yyyyMMddHHmmss";
    public static final String LOCK_ORDER_PARAM_SDF = "#L_LOCK_ORDER_PARAM_SDF_L#";
    public static SimpleDateFormat DATE_SDF;
    public static final String DATE_SDF_FORMAT = "yyyyMMdd";
    public static final String LOCAL_OUT_PAY_PAGE_URL = "http://111.1.56.137/jPay/pay.do";
    public static final String LOCK_DATE_SDF = "#L_LOCK_DATE_SDF_L#";
    public static final String SHOPNO = "1000000132";
    public static final String KEY = "YjASit3iry3zphJjbNt5t4dmD6asJZKi";

    static
    {
        try
        {
            md5 = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args)
            throws Exception
    {
        Random rdm = new Random();

        Map params = new HashMap();

        params.put("shopno", "1000000132");

        params.put("goodsinfo", "50元话费－13221062340");

        params.put("amount", "1");

        params.put("trantime", dateToOrderParam(new Date()));

        params.put("settledate", dateToDate(new Date()));

        params.put("wechat", "gh_23123123");

        params.put("openid", "FromUser");

        params.put("returnurl", "http://www.baidu.com");

        params.put("notifyurl", "http://www.sina.com");

        params.put("serial", new Date().getTime() + (rdm.nextInt(899) + 100));

        String signStr = sortParamForSign(params);

        String sign = md5LowerCase(signStr + "YjASit3iry3zphJjbNt5t4dmD6asJZKi");

        params.put("goodsinfo", URLEncoder.encode((String)params.get("goodsinfo"), "UTF-8"));

        signStr = sortParamForSign(params);

        Map params2 = new HashMap();

        params2.put("shopno", "1000000132");

        params2.put("wechat", "gh_23123123");

        params2.put("openid", "FromUser");

        params2.put("operationtype", "0");

        signStr = sortParamForSign(params2);

        sign = md5LowerCase(signStr + "YjASit3iry3zphJjbNt5t4dmD6asJZKi");

        System.out.println("http://111.1.56.137/jPay/pay.do?" + signStr + "&sign=" + sign);
    }

    public static final String md5LowerCase(String src)
    {
        byte[] b = (byte[])null;
        synchronized (LOCK_MD5)
        {
            try
            {
                md5.update(src.getBytes("UTF-8"));
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
            b = md5.digest();
            md5.reset();
        }

        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < b.length; offset++)
        {
            int i = b[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }
        return buf.toString();
    }

    public static String sortParamForSign(Map<String, String> params)
    {
        List<String> keyList = Arrays.asList((String[])params.keySet().toArray(new String[params.size()]));
        Collections.sort(keyList);

        StringBuilder sb = new StringBuilder();
        for (String k : keyList)
        {
            if (!"sign".equals(k))
            {
                sb.append(k).append("=")
                        .append((String)params.get(k)).append("&");
            }
        }
        if (params.size() > 0) {
            sb.delete(sb.length() - "&".length(), sb.length());
        }

        return sb.toString();
    }

    public static String dateToDate(Date date)
    {
        if (date == null) {
            return "";
        }
        synchronized ("#L_LOCK_DATE_SDF_L#")
        {
            if (DATE_SDF == null)
                DATE_SDF = new SimpleDateFormat("yyyyMMdd");
            return DATE_SDF.format(date);
        }
    }

    public static String dateToOrderParam(Date date)
    {
        if (date == null) {
            return "";
        }
        synchronized ("#L_LOCK_ORDER_PARAM_SDF_L#")
        {
            if (ORDER_PARAM_SDF == null)
                ORDER_PARAM_SDF = new SimpleDateFormat("yyyyMMddHHmmss");
            return ORDER_PARAM_SDF.format(date);
        }
    }
}
