package datasource.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class druidDemo01 {
    public static void main(String[] args) throws Exception {
        //1.导入jar包
        //2.定义配置文件
        //3.加载配置文件
        //创建Properties对象
        Properties pro = new Properties();
        //加载配置文件
        ClassLoader loader = druidDemo01.class.getClassLoader();
        InputStream is = loader.getResourceAsStream("druid.properties");
        pro.load(is);
        //4.获取连接池对象  通过工厂 DruidDataSourceFactory
        DataSource ds = DruidDataSourceFactory.createDataSource(pro);
        //获取连接
        Connection conn = ds.getConnection();
        System.out.println(conn);
    }
}
