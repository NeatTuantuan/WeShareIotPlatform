package edu.xd.bdilab.iotplatform.netty.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Decription TODO
 * @Author Humphrey
 * @Date 2019/10/21 20:04
 * @Version 1.0
 **/


public class HBaseConnection {
    private static Configuration conf = null;
    private static Connection conn = null;

    static {
        Properties property = new Properties();
        try {
            InputStream file= HBaseConnection.class.getClassLoader().getResourceAsStream("conf.properties");
            property.load(file);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("未找到配置文件");
        }
        conf = HBaseConfiguration.create();
        conf.set("hbase.master", property.getProperty("HBASE_ZOOKEEPER_QUORUM"));
        conf.set("hbase.zookeeper.quorum", property.getProperty("HBASE_MASTER"));
        conf.set("hbase.zookeeper.property.clientport", property.getProperty("ZK_PORT"));
        try {
            conn = ConnectionFactory.createConnection(conf);
        } catch (IOException e) {
            System.out.println("HBase获取连接失败");
            e.printStackTrace();
        }
    }

    public static Connection getConn() {
        return conn;
    }
}
