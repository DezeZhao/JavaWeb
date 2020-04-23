package jdbc;

import java.sql.*;

public class JdbcDemo04 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取连接对象
            conn = DriverManager.getConnection("jdbc:mysql:///mydb?serverTimezone=UTC",
                    "root", "123456");
            //定义sql语句
            String sql = "SELECT * FROM person";
            //获取执行SQL语句对象
            stmt = conn.createStatement();
            //执行sql
            rs = stmt.executeQuery(sql);
//            System.out.println(rs);
            //处理结果
            while (rs.next()) {//游标从字段行向下移动到数据行第一行
                int age = rs.getInt(1);
                String name = rs.getString(2);
                System.out.println("age=" + age + ",name=" + name);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
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

    }
}
