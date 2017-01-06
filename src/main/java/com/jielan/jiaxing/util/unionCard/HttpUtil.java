package com.jielan.jiaxing.util.unionCard;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
/**
 * Created by Administrator on 2016/8/2.
 */
public class HttpUtil {
    private static Logger log = Logger.getLogger(HttpUtil.class);
    public static final String HTTP_ENCODE = "UTF-8";
    public static final String SMS_ENCODE = "GBK";
    public static final String FORM_SEPARATE = "&";
    public static final String FORM_EP = "=";
    public static final String HTTP_METHOD_POST = "POST";
    public static final String HTTP_METHOD_GET = "GET";

    public static void main(String[] args)
    {
        Map map = new HashMap();

        map.put("name", "123");
        map.put("pass", "123");

        System.out.println(paraToForm(map));
    }

    public static String sendPost(String url, String param, String encode)
    {
        return sendPost(url, param, true, encode);
    }

    public static String sendPost(String url, Map<String, String> param, String encode)
    {
        return sendPost(url, param, true, encode);
    }

    public static String sendPost(String url, Map<String, String> param, boolean isForm, String encode)
    {
        return sendPost(url, paraToForm(param), isForm, encode);
    }

    public static String sendPost(String url, String param, boolean isForm, String encode)
    {
        log.info("Method[sendPost] 请求URL[" + url + "] 参数[" + param + "]");
        StringBuilder result = new StringBuilder();
        try
        {
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)httpUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            if (isForm)
                conn.setRequestProperty("Content-type",
                        "application/x-www-form-urlencoded;charset=" + encode);
            else {
                conn.setRequestProperty("Content-type", "text/html;charset=" + encode);
            }
            conn.connect();

            conn.getOutputStream().write(param.getBytes(encode));
            conn.getOutputStream().flush();
            conn.getOutputStream().close();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn
                    .getInputStream(), encode));
            String line = null;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            in.close();

            conn.disconnect();
        } catch (IOException e) {
            log.error("Method[sendPost] 网络异常 url[" + url + "]", e);
            throw new RuntimeException(url + " : " + e.getMessage());
        }

        log.info("Method[sendPost] result [" + result.toString() + "]");

        return result.toString();
    }

    public static String sendGet(String url, String encode)
    {
        log.info("Method[sendPost] 请求URL[" + url + "]");
        StringBuilder result = new StringBuilder();
        try
        {
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)httpUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            conn.setUseCaches(false);
            conn.setRequestProperty("Content-type",
                    "application/x-www-form-urlencoded");
            conn.connect();

            String line = null;
            BufferedReader in = new BufferedReader(new InputStreamReader(conn
                    .getInputStream(), encode));
            while ((line = in.readLine()) != null) {
                result.append(line);
            }

            conn.getInputStream().close();
            conn.disconnect();
        } catch (IOException e) {
            log.error("Method[sendGet] 网络异常 url[" + url + "]", e);
            throw new RuntimeException(url + " : " + e.getMessage());
        }
        log.info("Method[sendGet] result[" + result.toString() + "]");
        return result.toString();
    }

    public static String paraToForm(Map<String, String> para)
    {
        if (para == null) {
            return "";
        }
        StringBuilder result = new StringBuilder();

        for (Iterator it = para.entrySet().iterator(); it
                .hasNext(); )
        {
            Map.Entry e = (Map.Entry)it.next();
            result.append((String)e.getKey()).append("=").append((String)e.getValue()).append(
                    "&");
        }

        if (result.length() > 0) {
            result.delete(result.length() - 1, result.length());
        }
        return result.toString();
    }

    public static Map<String, String> getParam(HttpServletRequest req)
    {
        Map map = new HashMap();

        Enumeration es = req.getParameterNames();

        while (es.hasMoreElements()) {
            String name = (String)es.nextElement();
            String value = req.getParameter(name);

            map.put(name, value);
        }

        return map;
    }

    public static Map<String, String> postParam(HttpServletRequest req)
            throws IOException
    {
        Map map = new HashMap();

        StringBuilder sb = new StringBuilder();
        String line = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(req.getInputStream()));
        while ((line = in.readLine()) != null) {
            sb.append(line);
        }
        if (StringUtil.isNull(sb.toString())) {
            return map;
        }
        String[] row = sb.toString().split("&");

        for (String r : row) {
            if ((!StringUtil.isNull(r)) && (r.length() != r.indexOf("=")))
            {
                map.put(r.substring(0, r.indexOf("=")), r.substring(r
                        .indexOf("=") + 1));
            }
        }
        return map;
    }

    public static Map<String, String> parseURLParam(String strs)
    {
        Map map = new HashMap();

        if (StringUtil.isNull(strs)) {
            return map;
        }
        String[] str = strs.split("&");

        for (String s : str) {
            String[] str2 = s.split("=");
            if ((str2 != null) && (str2.length != 0))
            {
                if (str2.length == 1)
                    map.put(str2[0], "");
                else {
                    map.put(str2[0], str2[1]);
                }
            }
        }
        return map;
    }
}
