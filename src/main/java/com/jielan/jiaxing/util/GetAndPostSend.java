package com.jielan.jiaxing.util;

/**
 * Created by Administrator on 2016/7/5.
 */
import  java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class GetAndPostSend
{
    public static String sendGet(String url)
    {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString);

            URLConnection connection = realUrl.openConnection();

            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            connection.connect();

            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null)
            {
                result = result + line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
            try
            {
                if (in != null)
                    in.close();
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        finally
        {
            try
            {
                if (in != null)
                    in.close();
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
}
