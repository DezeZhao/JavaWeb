package datasource.druid;

import utils.JdbcUtils2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
    使用工具类
 */
public class druidDemo02 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        //获取连接
        try {
            conn = JdbcUtils2.getConnection();
            //定义sql语句
            String sql = "update person set balance=? where name=? ";
            //获取PreparedStatement对象
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 5000);
            pstmt.setString(2, "zdz");

            //执行
            int count = pstmt.executeUpdate();
            System.out.println(count);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils2.close(pstmt, conn);
        }


    }


}
