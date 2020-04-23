package jdbc;

import java.sql.*;

public class JdbcDemo02 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {//注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //定义sql
            String sql = "INSERT into person VALUES(22,'赵得泽')";
            //获取Connection
            conn = DriverManager.getConnection("jdbc:mysql:///mydb?serverTimezone=UTC",
                    "root", "123456");
            //获取执行sql的对象 Statement
            stmt = conn.createStatement();
            //执行sql语句
            int count = stmt.executeUpdate(sql);/*影响的行数*/

            System.out.println(count);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            //避免空指针异常 判断stmt 不等于null
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
