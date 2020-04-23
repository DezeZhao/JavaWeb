package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils2 {
    private static DataSource ds;

    /*
    静态代码块
     */
    static {
        try {
            //加载配置文件
            Properties pro = new Properties();
            ClassLoader classLoader = JdbcUtils2.class.getClassLoader();
            InputStream res = classLoader.getResourceAsStream("druid.properties");
            pro.load(res);
            //获取连接池对象 通过工厂
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
       获取连接
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /*
        关闭资源
     */
    public static void close(Statement stmt, Connection conn) {
        /*if (stmt != null) {
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
        }*/
        close(stmt, conn, null);
    }

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

    /*
        获取连接池
     */
    public static DataSource getDataSource() {
        return ds;
    }
}
