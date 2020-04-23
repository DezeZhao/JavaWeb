package jdbc;

import utils.JdbcUtils1;

import java.sql.*;
import java.util.Scanner;

/**
 * 登录案例
 */
public class JdbcDemo06 {
    public static void main(String[] args) {
        //键盘录入用户名密码
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String name = sc.nextLine();
        System.out.println("请输入密码：");
        String pwd = sc.nextLine();

        //调用login
        boolean b = login2(name, pwd);
        if (b) {
            System.out.println("login successful!");

        } else {
            System.out.println("username or password error!");
        }
    }

    public static boolean login(String name, String pwd) {
        if (name == null || pwd == null) {
            return false;
        }
        //连接数据库
        Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            //1.获取连接z
            conn = JdbcUtils1.getConnection();
            //2. 定义sql
            //存在sql注入问题 即sql语句拼接问题 用PreparedStatement解决
            String sql = "select * from user where username = '" + name + "' and password = '" + pwd + "' ";
            System.out.println(sql);
            //3 .获取执行sql的对象
            stmt = conn.createStatement();
            //4. 执行查询
            rs = stmt.executeQuery(sql);
            //5. 判断
            return rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils1.close(stmt, conn, rs);
        }
        return false;
    }

    //预编译 PreparedStatement 用？做占位符在SQL语句中
    public static boolean login2(String name, String pwd) {
        if (name == null || pwd == null) {
            return false;
        }
        //连接数据库
        PreparedStatement pstmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            //1.获取连接z
            conn = JdbcUtils1.getConnection();
            //2. 定义sql
            //存在sql注入问题 即sql语句拼接问题 用PreparedStatement解决
            String sql = "select * from user where username = ? and password=?";
//            System.out.println(sql);
            //3 .执行sql语句
            pstmt = conn.prepareStatement(sql);
            //给？赋值
            pstmt.setString(1, name);
            pstmt.setString(2, pwd);
            //4. 执行查询 不需要传参
            rs = pstmt.executeQuery();
            //5. 判断
            return rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils1.close(pstmt, conn, rs);//Statement 是 PreparedStatement 的父类 此处pstmt发生了向上转型
        }
        return false;
    }

}