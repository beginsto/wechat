package com.jielan.jiaxing.util.hzdb;

import java.sql.*;
/**
 * Created by Administrator on 2016/7/4.
 */
public class DBClassLocal {
    private static String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static String URL = "jdbc:oracle:thin:@111.1.56.139:1521:orcl";
    private static String user_local = "u_zshz_db";
    private static String password_local = "zsjl_188hzdb";

    /*private static String DRIVER = "com.mysql.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/db_jx";
    private static String user_local = "root";
    private static String password_local = "P0o9i8u";*/

    public DBClassLocal() throws ClassNotFoundException {
        Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
    }

    public static Connection getConnection() {
        Connection conn=null;
        try {
            if (conn == null || conn.isClosed())
                //conn = DriverManager.getConnection("proxool.LocalProxool");
                Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL,user_local,password_local);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return new ConnectionControl(conn);
        return conn;
    }

    /**
     * 关闭
     */
    public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
