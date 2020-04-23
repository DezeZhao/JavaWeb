package jdbc;

import java.sql.*;

public class JdbcDemo03 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            //注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取连接对象
            conn = DriverManager.getConnection("jdbc:mysql:///mydb?serverTimezone=UTC",
                    "root", "123456");
            //定义sql语句
            //String sql = "UPDATE person SET age = 25 where age=22";//DML
            //String sql = "DELETE FROM person where age =25";//DML
            String sql = "CREATE TABLE student (id int ,name varchar(20))";//DDL
            //获取执行SQL语句对象
            stmt = conn.createStatement();
            //执行sql
            int count = stmt.executeUpdate(sql);
            System.out.println(count);

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
        }

    }
}
