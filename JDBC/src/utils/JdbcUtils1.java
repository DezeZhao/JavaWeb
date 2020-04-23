package utils;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

/*
 JDBC工具类
 */
public class JdbcUtils1 {
    private static String url;
    private static String user;
    private static String pwd;

    /**
     * 文件读取 只需要读取一次使用静态代码块
     */
    static {

        try {
            //1. Properties 集合类
            Properties pro = new Properties();
            // 获取src路径下文件  ClassLoader
            //2. 加载文件
//            pro.load(new FileReader("src/jdbc.properties"));
            ClassLoader classLoader = JdbcUtils1.class.getClassLoader();
            URL res = classLoader.getResource("jdbc.properties");
            assert res != null;
            String path = res.getPath();
            System.out.println(path);

            pro.load(new FileReader(path));

            //3. 获取数值
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            pwd = pro.getProperty("pwd");
            //4. 注册驱动
            String driver = pro.getProperty("driver");
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取连接
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pwd);
    }

    /**
     * 释放资源
     */
    public static void close(Statement stmt, Connection conn, ResultSet rs) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }
    /**
     * 释放资源 重载函数
     */
    public static void close(Statement stmt,Connection conn){
        if( stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if( conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



}
