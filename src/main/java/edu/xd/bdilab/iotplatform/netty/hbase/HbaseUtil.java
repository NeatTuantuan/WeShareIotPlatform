package edu.xd.bdilab.iotplatform.netty.hbase;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class HbaseUtil {


    public static Configuration config;

    static {
        Properties properties = new Properties();
        config = HBaseConfiguration.create();
        try {
            InputStream file= HBaseConnection.class.getClassLoader().getResourceAsStream("conf.properties");
            properties.load(file);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("未找到配置文件");
        }

        // 数据库元数据操作对象
        // Admin admin;

        // config.addResource(new Path("D:\\hbase-1.2.5\\conf\\hbase-site.xml"));

        // 取得一个数据库连接的配置参数对象

        config.set("hbase.master",properties.getProperty("HBASE_MASTER"));
        // 设置连接参数：HBase数据库所在的主机IP
        config.set("hbase.zookeeper.quorum", properties.getProperty("HBASE_ZOOKEEPER_QUORUM"));
        // 设置连接参数：HBase数据库使用的端口
        config.set("hbase.zookeeper.property.clientPort", properties.getProperty("ZK_PORT"));

        config.set("HADOOP_HOME","/usr/local/hadoop-2.7.0");

        /*
        config = HBaseConfiguration.create();
        config.set("hbase.zookeeper.quorum", "127.0.0.1");
        config.set("hbase.zookeeper.property.clientPort", "2181");
        config.set("hbase.master", "127.0.0.1:60010");
        */


    }

    /**
     * 创建表
     *
     * @param tableName 表名
     * @param columns   列族
     * @return
     */
    public boolean createTable(String tableName, String[] columns) {
        boolean result = false;
        try {
            HBaseAdmin Hbaseadmin = new HBaseAdmin(config);
            if (Hbaseadmin.tableExists(tableName)) {
                System.out.println("表已经存在！");
                result = false;
            } else {
                HTableDescriptor desc = new HTableDescriptor(tableName);
                for (String column : columns) {
                    desc.addFamily(new HColumnDescriptor(column));
                }
                Hbaseadmin.createTable(desc);
                System.out.println("表创建成功！");
                result = true;
            }
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }

        return result;

    }

    /**
     * 清空表
     *
     * @param tabName
     * @return true 表示成功
     */
    public boolean truncateTable(String tabName) {
        boolean result = false;
        try {
            System.out.println("---------------清空表 START-----------------");
            // 取得目标数据表的表名对象
            TableName tableName = TableName.valueOf(tabName);
            Admin admin = new HBaseAdmin(config);
            // 设置表状态为无效
            admin.disableTable(tableName);
            // 清空指定表的数据
            admin.truncateTable(tableName, true);
            System.out.println("---------------清空表 End-----------------");
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除表
     *
     * @param tabName
     * @return true 删除成功;
     */
    public boolean deleteTable(String tabName) {
        boolean result = false;
        try {
            System.out.println("---------------删除表 START-----------------");
            // 设置表状态为无效
            Admin admin = new HBaseAdmin(config);
            admin.disableTable(TableName.valueOf(tabName));
            // 删除指定的数据表
            admin.deleteTable(TableName.valueOf(tabName));
            System.out.println("---------------删除表 End-----------------");
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * 删除行
     *
     * @param tabName
     * @param rowkey
     * @return
     * @throws IOException
     */
    public boolean deleteByRowKey(String tabName, String rowkey) throws IOException {
        boolean result = false;
        try {
            System.out.println("---------------删除行 START-----------------");
            // 取得待操作的数据表对象
            Connection connection = ConnectionFactory.createConnection(config);
            Table table = connection.getTable(TableName.valueOf(tabName));
            // 创建删除条件对象
            Delete delete = new Delete(Bytes.toBytes(rowkey));
            // 执行删除操作
            table.delete(delete);
            System.out.println("---------------删除行 End-----------------");
            result = true;
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 新建列族
     *
     * @param tabName
     * @param fName
     * @return
     * @throws IOException
     */
    public boolean addColumnFamily(String tabName, String fName) {
        boolean result = false;
        try {
            System.out.println("---------------新建列族 START-----------------");
            Admin admin = new HBaseAdmin(config);
            // 取得目标数据表的表名对象
            TableName tableName = TableName.valueOf(tabName);
            // 创建列族对象
            HColumnDescriptor columnDescriptor = new HColumnDescriptor(fName);
            // 将新创建的列族添加到指定的数据表
            admin.addColumn(tableName, columnDescriptor);
            System.out.println("---------------新建列族 END-----------------");
            result = true;
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除列族
     *
     * @param tabName
     * @param fname
     * @return
     */
    public boolean deleteColumnFamily(String tabName, String fname) {
        boolean result = false;
        try {
            System.out.println("---------------删除列族 START-----------------");
            Admin admin = new HBaseAdmin(config);
            // 取得目标数据表的表名对象
            TableName tableName = TableName.valueOf(tabName);
            // 删除指定数据表中的指定列族
            admin.deleteColumn(tableName, fname.getBytes());
            System.out.println("---------------删除列族 END-----------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 插入数据
     */
    public boolean insert(String tabName, List<Put> putList) {
        boolean result = false;
        try {
            System.out.println("---------------插入数据 START-----------------");
            Connection connection = ConnectionFactory.createConnection(config);
            // 取得一个数据表对象
            Table table = connection.getTable(TableName.valueOf(tabName));
            // 将数据集合插入到数据库
            table.put(putList);

            System.out.println("---------------插入数据 END-----------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 查询整表数据
     */

    public ResultScanner queryAllTable(String tabName) {
        ResultScanner resultx = null;
        try {
            System.out.println("---------------查询整表数据 START-----------------");

            // 取得数据表对象
            Connection connection = ConnectionFactory.createConnection(config);
            Table table = connection.getTable(TableName.valueOf(tabName));
            // 取得表中所有数据
            resultx = table.getScanner(new Scan());
            // 循环输出表中的数据
            for (Result result : resultx) {
                byte[] row = result.getRow();
                System.out.println("row key is:" + new String(row));
                List<Cell> listCells = result.listCells();
                for (Cell cell : listCells) {
                    byte[] familyArray = cell.getFamilyArray();
                    byte[] qualifierArray = cell.getQualifierArray();
                    byte[] valueArray = cell.getValueArray();
                    System.out.println("row value is:" + new String(familyArray) + new String(qualifierArray)
                            + new String(valueArray));
                }
            }
            System.out.println("---------------查询整表数据 END-----------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultx;
    }


    /**
     * 按行键查询表数据
     *
     * @param tabName
     * @param rowKey
     * @return
     * @throws IOException
     */
    public Result queryTableByRowKey(String tabName, String rowKey) {
        Result result = null;
        try {
            if (StringUtils.isEmpty(tabName) || StringUtils.isEmpty(rowKey)) {
                return null;
            }
            System.out.println("---------------按行键查询表数据 START-----------------");
            // 取得数据表对象
            Connection connection = ConnectionFactory.createConnection(config);
            Table table = connection.getTable(TableName.valueOf(tabName));
            // 新建一个查询对象作为查询条件
            Get get = new Get(rowKey.getBytes());
            // 按行键查询数据
            result = table.get(get);
            byte[] row = result.getRow();
            System.out.println("row key is:" + new String(row));
            List<Cell> listCells = result.listCells();
            for (Cell cell : listCells) {
                byte[] familyArray = cell.getFamilyArray();
                byte[] qualifierArray = cell.getQualifierArray();
                byte[] valueArray = cell.getValueArray();
                System.out.println("row value is:" + new String(familyArray) + new String(qualifierArray) + new String(valueArray));
            }
            System.out.println("---------------按行键查询表数据 END-----------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }


    /**
     * 按条件查询表数据
     */

    public ResultScanner queryTableByCondition(String tabName, Filter filter) {
        ResultScanner resultScanner = null;
        try {
            System.out.println("---------------按条件查询表数据 START-----------------");
            // 取得数据表对象
            Connection connection = ConnectionFactory.createConnection(config);
            Table table = connection.getTable(TableName.valueOf(tabName));
            // 创建一个查询过滤器
            // Filter filter = new SingleColumnValueFilter(Bytes.toBytes("base"), Bytes.toBytes("name"), CompareOp.EQUAL, Bytes.toBytes("bookName6"));
            // 创建一个数据表扫描器
            Scan scan = new Scan();
            // 将查询过滤器加入到数据表扫描器对象
            scan.setFilter(filter);
            // 执行查询操作，并取得查询结果
            resultScanner = table.getScanner(scan);

            // 循环输出查询结果
            for (Result result : resultScanner) {
                byte[] row = result.getRow();
                System.out.println("row key is:" + new String(row));
                List<Cell> listCells = result.listCells();
                for (Cell cell : listCells) {
                    System.out.println("family:" + Bytes.toString(CellUtil.cloneFamily(cell)));
                    System.out.println("qualifier:" + Bytes.toString(CellUtil.cloneQualifier(cell)));
                    System.out.println("value:" + Bytes.toString(CellUtil.cloneValue(cell)));
                }
            }

            System.out.println("---------------按条件查询表数据 END-----------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultScanner;
    }


    public static void main(String[] args) throws IOException {
        String[] columns = new String[]{"id", "name"};
        //createTable("info_user",columns);


    }

}
