package jdbc;

import java.sql.*;

public class JdbcDemo01 {
    public static void main(String[] args) throws Exception {
        //导入jar包
        //注册驱动  不需要注册驱动 在service包中已经注册了
        /*try {
            Class.forName("com.mysql.cj.jdbc.Driver");//类的字节码文件加载进内存
            System.out.println("Success loading MySQL Driver!");
        } catch (Exception e) {
            System.out.println("Error loading MySQL Driver!");
            e.printStackTrace();
        }*/
        //获取数据库连接对象
        Connection conn = null;
        try {
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC&characterEncoding=utf-8",
//                    "root", "123456");
            conn = DriverManager.getConnection("jdbc:mysql:///mydb?serverTimezone=UTC",
                    "root", "123456");
            System.out.println("Success connect MySQL Server!");
        } catch (Exception e) {
            System.out.println("Error connect MySQL server!");
            e.printStackTrace();
        }
        //return (getConnection(url, info, Reflection.getCallerClass()));
        //定义sql语句
        String sql = "UPDATE person SET age = 20 WHERE name LIKE '%zhaodeze%' ";
        //获取执行sql的对象 Statement
        assert conn != null;
        Statement stmt = conn.createStatement();
        //执行sql
        int count = stmt.executeUpdate(sql);//返回执行结果影响的行数
        //处理结果
        System.out.println(count);

        //释放资源
        stmt.close();
        conn.close();
    }
}
