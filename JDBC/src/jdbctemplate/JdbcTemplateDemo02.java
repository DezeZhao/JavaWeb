package jdbctemplate;

import domain.Emp1;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.JdbcUtils2;

import java.util.List;
import java.util.Map;

/*
* Spring框架对JDBC的简单封装。提供了一个JDBCTemplate对象简化JDBC的开发
* 步骤：
	1. 导入jar包
	2. 创建JdbcTemplate对象。依赖于数据源DataSource
		* JdbcTemplate template = new JdbcTemplate(ds);

	3. 调用JdbcTemplate的方法来完成CRUD的操作
		* update():执行DML语句。增、删、改语句
		* queryForMap():查询结果将结果集封装为map集合，将列名作为key，将值作为value 将这条记录封装为一个map集合
			* 注意：这个方法查询的结果集长度只能是1
		* queryForList():查询结果将结果集封装为list集合
			* 注意：将每一条记录封装为一个Map集合，再将Map集合装载到List集合中
		* query():查询结果，将结果封装为JavaBean对象
			* query的参数：RowMapper
				* 一般我们使用BeanPropertyRowMapper实现类。可以完成数据到JavaBean的自动封装
				* new BeanPropertyRowMapper<类型>(类型.class)
		* queryForObject：查询结果，将结果封装为对象
			* 一般用于聚合函数的查询

 */
public class JdbcTemplateDemo02 {

    //创建 JdbcTemplate对象
    private JdbcTemplate template = new JdbcTemplate(JdbcUtils2.getDataSource());

    /*
        在方法中进行测试
     */
    /*
        修改 zdz 的年龄为 23
     */
    @Test
    public void test1() {
        String sql = "update person set age=? where name=?";
        template.update(sql, 23, "zdz");
    }

    /*
        添加一条记录
     */
    @Test
    public void test2() {
        String sql = "insert into person(balance,age,name) values (?,?,?)";
        template.update(sql, 1000, 23, "zq");
    }

    /*
        删除添加的数据
     */
    @Test
    public void test3() {
        String sql = "delete from person where name=?";
        template.update(sql, "zq");
    }

    /*
        查询名字为zdz 的记录并将其封装为 Map
        这个方法查询的结果集长度只能是1
     */
    @Test
    public void test4() {
        String sql = "select * from person where name=?";
        Map<String, Object> objectMap = template.queryForMap(sql, "zdz");
        System.out.println(objectMap);
    }

    /*
        查询所有记录，将其封装为List
     */
    @Test
    public void test5() {
        String sql = "select * from person";
        List<Map<String, Object>> list = template.queryForList(sql);
        System.out.println(list);
    }

    /*
        查询所有记录，将其封装为Emp对象的List集合
     */
    @Test
    public void test6() {
        String sql = "select * from emp";
        List<Emp1> list = template.query(sql, new BeanPropertyRowMapper<Emp1>(Emp1.class));
        for (Emp1 emp1 : list) {
            System.out.println(emp1);
        }
        //Failed to convert property value of type 'null' to required type 'double' for property 'bonus';
        //此处是由于 bonus值为null 而bean中     private double bonus 基本类型不能接收null值 故定义为引用类型的Double即可;
    }
}
