/*
 * 文件名：DBUtils.java
 * 版权：Copyright 2007-2015 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： DBUtils.java
 * 修改人：hongjian
 * 修改时间：2015年12月3日
 * 修改内容：新增
 */
package javaFXClient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author hongjian
 */
public class DBUtils {

    private static String url = "jdbc:mysql://";
    private static String driver = "com.mysql.jdbc.Driver";
    private static Connection con;

    public static Connection getConnection(String ip, String dataBaseName, String password, String username) throws SQLException {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String urlStr = url + ip + "/" + dataBaseName + "?useUnicode=true&characterEncoding=UTF-8";
        try {
            con = DriverManager.getConnection(urlStr, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return con;
    }

    public static void reaseConnection(Connection con, Statement st, ResultSet rs, PreparedStatement pt) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pt != null) {
                pt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
