package jdbc;

import utils.JdbcUtils1;

import java.sql.*;

/*
  事务transaction 操作
 */
public class JdbcDemo07 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;

        try {
            /*//注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取连接对象
            conn = DriverManager.getConnection("jdbc:mysql:///mydb?serverTimezone=UTC", "root", "123456");*/
            conn = JdbcUtils1.getConnection();

            //开启事务
            conn.setAutoCommit(false);

            //定义sqlyuju
            String sql1 = "update person set balance = balance - ? where name like ?";
            String sql2 = "update person set balance = balance + ? where name like ?";
            //获取执行sql语句的对象
            pstmt1 = conn.prepareStatement(sql1);
            pstmt1.setDouble(1, 500);
            pstmt1.setString(2, "zhaodeze");

            pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setDouble(1, 500);
            pstmt2.setString(2, "zdz");
            //执行sql
            pstmt1.executeUpdate();
            //int x = 3 / 0;
            pstmt2.executeUpdate();

            //提交事务
            conn.commit();

        } catch (Exception e) {
            try {
                if (conn != null) {
                    //事务回滚
                    conn.rollback();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            //释放资源
            //避免空指针异常 判断stmt 不等于null
            JdbcUtils1.close(pstmt1, conn);
            JdbcUtils1.close(pstmt2, null);

        }
    }


}
