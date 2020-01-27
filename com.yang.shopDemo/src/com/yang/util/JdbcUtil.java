package com.yang.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

@Component
public class JdbcUtil {
    // 声明一个数据库资源，方便后续进行操作
    public static DataSource ds = null;

    static {
        // 1. 初始化读取资源文件
        Properties properties = new Properties();
        try {
            // 2. 读取mysql的配置信息到内存中
            FileInputStream fileInputStream = new FileInputStream("/Users/yangshixiong/Desktop/springDemo/com.yang.shopDemo/resources/mysql.properties");
            // 3. 转化配置信息
            properties.load(fileInputStream);
            // 4。 生成德鲁伊数据库连接池
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 从数据库连接中获取连接对象
    public static Connection getConn(){
        try{
            return ds.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭数据库连接对象，释放资源，数据库连接池关闭对象就会把连接放回池中，而不是关闭
     * @param conn 连接对象
     * @param st  连接语句
     * @param rs  查询结果集
     */
    public  static void close(Connection conn, Statement st, ResultSet rs){
        // 关闭结果集
        if(rs!=null){
            try{
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        // 关闭查询
        if(st!=null){
            try{
                st.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        // 关闭连接对象
        if(conn!=null){
            try{
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }


}
