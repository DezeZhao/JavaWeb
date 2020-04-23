package datasource.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class c3p0Demo01 {
    public static void main(String[] args) throws SQLException {
        //默认读取配置文件 c3p0-config.xml
        //创建核心对象 数据库连接池对象 ComboPooledDataSource
        DataSource ds = new ComboPooledDataSource();
        //获取连接： getConnection
//        Connection conn = ds.getConnection();
        //System.out.println(conn);
        for (int i = 0; i < 11; i++) {
            Connection conn = ds.getConnection();
            System.out.println(conn);
        }

    }
}
