package com.jielan.jiaxing.util;

import com.jielan.jiaxing.util.unionCard.DigestUitl;
import com.jielan.jiaxing.util.unionCard.HttpUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/2.
 */
public class SmsUtil {
    //	private static final String MER_ID = "08890";
//	private static final String KEY = "zdp123xxz7wdgt";
//	private static final String URL = "http://www.jsmms.com/messageSend.do";

    private static final String MER_ID = "08890";
    private static final String KEY = "zdp123xxz7wdgtxm";
    private static final String URL = "http://www.jsmms.com/messageSend.do";

    /**
     * 鍙戦�鐭俊
     *
     * @param mobile
     * @param content
     * @return
     * @throws Exception
     */
    public static String sendSms( String mobile, String content ) {
        try {
            Map<String, String> param = new HashMap<String, String>();
            param.put( "merId", MER_ID );
            param.put( "mobile", mobile );
            param.put( "content", content);
            param.put( "rank", "5" );
            param.put( "reservedTime", "2014-10-30" );
            param.put( "exCode", "123456" );
            param.put( "tranCode", "1001" );
            param.put( "sign", sign( param, KEY ) );
            String reqParams = mapParamsToUrl( param );

            String result = HttpUtil.sendPost(URL, reqParams, "UTF-8");


            // #璇锋眰缃戝叧
            //		System.out.println( "璇锋眰鐭俊鍙戦�鍙傛暟:" + reqParams );
            //		String reuslt = sendPost( URL, reqParams );
            //		System.out.println( "鐭俊鍙戦�缃戝叧鍝嶅簲:" + reuslt );
            return result;
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     *
     *
     * @param param
     * @param secretKey
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    private static String sign( Map<String, String> param, String secretKey )
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        StringBuffer signStr = new StringBuffer();
        signStr.append( param.get("merId") )
                .append( param.get("mobile") )
                .append( param.get("content") )
                .append( param.get("rank") )
                .append( param.get("reservedTime") )
                .append( param.get("exCode") )
                .append( param.get( "tranCode" ) ).append( secretKey );
        //	System.out.println(signStr.toString());
        return DigestUitl.MD5LowerCase(signStr.toString());
    }

    /**
     *
     *
     * @param url
     * @param param
     * @return
     */
    public static String sendPost( String url, String param ) {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuffer result = new StringBuffer();
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty( "accept", "*/*" );
            conn.setRequestProperty( "connection", "Keep-Alive" );
            conn.setRequestProperty( "user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)" );
            conn.setDoOutput(true);
            conn.setDoInput(true);

            out = new PrintWriter(conn.getOutputStream());
            out.print( param );
            out.flush();

            InputStream is = conn.getInputStream();
            byte[] b = new byte[1024];
            int len = -1;
            while ( ( len = is.read(b) ) != -1 ) {
                result.append( new String( b, 0, len, "utf-8" ) );
            }
            return result.toString();
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            try{
                if ( out != null ) out.close();
                if ( in != null ) in.close();
            } catch ( IOException ex ) {}
        }
        return "";
    }

    /**
     * 灏嗗弬鏁癕ap杞崲涓鸿姹倁rl
     *
     * @param params
     * @return
     */
    public static String mapParamsToUrl( Map<String, String> params ) {
        StringBuilder sb = new StringBuilder();
        for ( String s : params.keySet() ) {
            sb.append( s ).append( "=" ).append( params.get(s) ).append( "&" );
        }
        if ( params.size() > 1 )
            sb.delete( sb.length() - 1, sb.length() );
        return sb.toString();
    }

    public static void main(String[] args) {
        try {
            System.out.println(sendSms("15905836605","test"));
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}
