package com.jielan.jiaxing.util.oracle.jx;

import com.jielan.jiaxing.util.oracle.jx.DBClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/7/4.
 */
public class LocalJDBCTemplate {
    public static void doInTransaction(com.jielan.jiaxing.util.hzdb.LocalJDBCTemplate.JdbcCallback callback)
            throws Exception
    {
        Connection conn = null;
        try {
            conn = DBClass.getConnection();
            conn.setAutoCommit(false);
            callback.executeSQL(conn);
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                throw new Exception(e1);
            }
            throw new Exception(e);
        } finally {
            close(conn, null, null);
        }
    }

    public static Connection getLocalConn()
    {
        Connection conn = null;
        conn = DBClass.getConnection();
        return conn;
    }

    public static void executeSQL(String sql, Object[] params, Connection conn)
            throws Exception
    {
        PreparedStatement ps = null;

        ps = conn.prepareStatement(sql);
        addParamUtil(params, ps);
        ps.executeUpdate();

        close(null, ps, null);
    }

    public static int queryForInt(String sql)
            throws Exception
    {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = DBClass.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new Exception();
        } finally {
            close(conn, ps, rs);
        }
        return count;
    }

    public static int queryForInt(String sql, Object[] params)
            throws Exception
    {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = DBClass.getConnection();
            ps = conn.prepareStatement(sql);
            addParamUtil(params, ps);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new Exception();
        } finally {
            close(conn, ps, rs);
        }
        return count;
    }

    public static List queryForList(String sql, Object[] params)
            throws Exception
    {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList arr = new ArrayList();
        try {
            conn = DBClass.getConnection();
            ps = conn.prepareStatement(sql);
            addParamUtil(params, ps);
            rs = ps.executeQuery();
            int resultcount = rs.getMetaData().getColumnCount();
            Object[] obj = new Object[resultcount];
            while (rs.next()) {
                for (int i = 0; i < resultcount; i++) {
                    String typeName = rs.getMetaData().getColumnTypeName(i + 1);

                    if (typeName.toLowerCase().indexOf("number") >= 0) {
                        int tmpInt = rs.getInt(i + 1);
                        obj[i] = Integer.valueOf(tmpInt);
                    }
                    if (typeName.toLowerCase().indexOf("varchar") >= 0) {
                        String tmpString = rs.getString(i + 1);
                        obj[i] = tmpString;
                    }
                    if (typeName.toLowerCase().indexOf("long") >= 0) {
                        String tmpString = rs.getString(i + 1);
                        obj[i] = tmpString;
                    }
                }

                Object[] result = (Object[])null;
                result = new Object[resultcount];
                for (int j = 0; j < resultcount; j++) {
                    result[j] = obj[j];
                }
                arr.add(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception();
        } finally {
            close(conn, ps, rs);
        }
        return arr;
    }

    public static int insertData(String sql, Object[] params)
            throws Exception
    {
        Connection conn = null;
        PreparedStatement ps = null;
        int flag = 0;
        try {
            conn = DBClass.getConnection();
            ps = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            addParamUtil(params, ps);
            flag = ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            throw new Exception();
        } finally {
            close(conn, ps, null);
        }
        return flag;
    }

    public static int updateData(String sql, Object[] params)
            throws Exception
    {
        Connection conn = null;
        PreparedStatement ps = null;
        int flag = 0;
        try {
            conn = DBClass.getConnection();
            ps = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            addParamUtil(params, ps);
            flag = ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            throw new Exception();
        } finally {
            close(conn, ps, null);
        }
        return flag;
    }

    public static boolean isInt(Object obj)
    {
        if ((obj instanceof Integer)) {
            return true;
        }
        return false;
    }

    public static void addParamUtil(Object[] params, PreparedStatement ps)
            throws SQLException
    {
        int param_count = params.length;
        for (int i = 0; i < param_count; i++)
            if (isInt(params[i]))
                ps.setInt(i + 1, ((Integer)params[i]).intValue());
            else
                ps.setString(i + 1, (String)params[i]);
    }

    public static void close(Connection conn, PreparedStatement ps, ResultSet rs)
    {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null)
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public static abstract interface JdbcCallback
    {
        public abstract Object executeSQL(Connection paramConnection)
                throws Exception;
    }
}
