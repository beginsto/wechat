package com.jielan.jiaxing.util;

/**
 * Created by Administrator on 2016/7/5.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Set;

public final class HttpUtils
{
    public static String sendGet(String url, String charSet)
    {
        if ((charSet == null) || (charSet.length() == 0)) {
            charSet = "utf-8";
        }
        StringBuffer result = new StringBuffer();
        try {
            URL U = new URL(url);
            URLConnection connection = U.openConnection();
            connection.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), charSet));
            String line;
            while ((line = in.readLine()) != null)
            {
                result.append(line);
            }
            in.close();
        } catch (MalformedURLException e) {
            throw new RuntimeException("发送get请求出错,url:" + url, e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("不支持的字符集,charSet:" + charSet, e);
        } catch (IOException e) {
            throw new RuntimeException("发送get请求IO出错，url:" + url, e);
        }
        return result.toString();
    }

    public static String sendWapGet(String url, String mobile, String charSet) {
        if ((charSet == null) || (charSet.length() == 0)) {
            charSet = "utf-8";
        }
        StringBuffer result = new StringBuffer();
        try {
            URL U = new URL(url);
            URLConnection connection = U.openConnection();
            connection.addRequestProperty("x-up-calling-line-id", "86" + mobile);
            connection.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), charSet));
            String line;
            while ((line = in.readLine()) != null)
            {
                result.append(line);
            }
            in.close();
        } catch (MalformedURLException e) {
            throw new RuntimeException("发送get请求出错,url:" + url, e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("不支持的字符集,charSet:" + charSet, e);
        } catch (IOException e) {
            throw new RuntimeException("发送get请求IO出错，url:" + url, e);
        }
        return result.toString();
    }


    @SuppressWarnings("rawtypes")
    public static String sendPost(String url, Map<String, String> param, String charSet) {
        StringBuffer result = new StringBuffer();
        try {
            URL httpurl = new URL(url);
            HttpURLConnection httpConn = (HttpURLConnection)httpurl.openConnection();
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            PrintWriter out = new PrintWriter(httpConn.getOutputStream());
            int i = 0;
            Set<Map.Entry<String, String>> set = param.entrySet();
            for (Map.Entry entry : set) {
                out.print((String)entry.getKey());
                out.print("=");
                out.print((String)entry.getValue());
                if (i != set.size() - 1) {
                    out.print("&");
                }
                i++;
            }
            out.flush();
            out.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), charSet));
            String line;
            while ((line = in.readLine()) != null)
            {
                result.append(line);
            }
            in.close();
        } catch (MalformedURLException e) {
            throw new RuntimeException("发送post请求出错,url:" + url, e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("不支持的字符集,charSet:" + charSet, e);
        } catch (IOException e) {
            throw new RuntimeException("发送post请求IO出错,url:" + url, e);
        }
        return result.toString();
    }
}
