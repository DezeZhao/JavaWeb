package jdbc;

import domain.Emp;
import utils.JdbcUtils1;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JdbcDemo05 {
    public static void main(String[] args) {
        List<Emp> list = new ArrayList<>();
        list = finaAll2();
        System.out.println(list);
    }

    public static List<Emp> finaAll() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Emp> list = new ArrayList<>();
        try {
            //注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取连接对象
            conn = DriverManager.getConnection("jdbc:mysql:///mydb?serverTimezone=UTC&characterEncoding=utf-8", "root", "123456");
            //定义sqlyuju
            String sql = "SELECT * FROM emp";
            //获取执行sql语句的对象
            stmt = conn.createStatement();
            //执行sql
            rs = stmt.executeQuery(sql);
            Emp emp = null;
            while (rs.next()) {
                int id = rs.getInt("id");
                String ename = rs.getString("ename");
                int job_id = rs.getInt("job_id");
                int mgr = rs.getInt("mgr");
                Date joindate = rs.getDate("joindate");
                double salary = rs.getDouble("salary");
                double bonus = rs.getDouble("bonus");
                int dept_id = rs.getInt("dept_id");
                emp = new Emp();

                emp.setId(id);
                emp.setEname(ename);
                emp.setJob_id(job_id);
                emp.setMgr(mgr);
                emp.setJoindate(joindate);
                emp.setSalary(salary);
                emp.setBonus(bonus);
                emp.setDept_id(dept_id);

                //装载集合
                list.add(emp);

            }
        } catch (Exception e) {
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

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        }
        return list;
    }

    public static List<Emp> finaAll2() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Emp> list = new ArrayList<>();
        try {
            /*//注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取连接对象
            conn = DriverManager.getConnection("jdbc:mysql:///mydb?serverTimezone=UTC", "root", "123456");*/
            conn = JdbcUtils1.getConnection();
            //定义sqlyuju
            String sql = "SELECT * FROM emp";
            //获取执行sql语句的对象
            stmt = conn.createStatement();
            //执行sql
            rs = stmt.executeQuery(sql);
            Emp emp = null;
            while (rs.next()) {
                int id = rs.getInt("id");
                String ename = rs.getString("ename");
                int job_id = rs.getInt("job_id");
                int mgr = rs.getInt("mgr");
                Date joindate = rs.getDate("joindate");
                double salary = rs.getDouble("salary");
                double bonus = rs.getDouble("bonus");
                int dept_id = rs.getInt("dept_id");
                emp = new Emp();

                emp.setId(id);
                emp.setEname(ename);
                emp.setJob_id(job_id);
                emp.setMgr(mgr);
                emp.setJoindate(joindate);
                emp.setSalary(salary);
                emp.setBonus(bonus);
                emp.setDept_id(dept_id);

                //装载集合
                list.add(emp);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            //避免空指针异常 判断stmt 不等于null
            JdbcUtils1.close(stmt, conn, rs);
        }
        return list;
    }
}
