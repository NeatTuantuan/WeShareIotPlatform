package edu.xd.bdilab.iotplatform.netty.redis;


import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Slf4j
@Mapper
@Component
public class RedisUtil {
    //服务器IP地址
    private static String ADDR = "47.103.29.15";
    //端口
    private static int PORT = 6379;
    //密码
    private static String AUTH = "123456";
    //连接实例的最大连接数
    private static int MAX_ACTIVE = 1024;
    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 200;
    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException
    private static int MAX_WAIT = 10000;
    //连接超时的时间　　
    private static int TIMEOUT = 10000;
    // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;

    private static JedisPool jedisPool = null;
    //数据库模式是16个数据库 0~15
    public static final int DEFAULT_DATABASE = 0;
    /**
     * 初始化Redis连接池
     */
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH, DEFAULT_DATABASE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取Jedis实例
     */
    public synchronized static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                logger.info("redis--服务正在运行: " + resource.ping());
                return resource;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /***
     *
     * 释放资源
     */
    public static void returnResource(final Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }
    /**
     * 获取指定key的值,如果key不存在返回null，如果该Key存储的不是字符串，会抛出一个错误
     * @param key
     * @return
     */
    public String get(String key) {
        Jedis jedis = getJedis();
        String value  = jedis.get(key);
        return value;
    }
    /**
     * 设置key的值为value,默认0库
     *
     * @param key
     * @param value
     * @return
     */
    public void set(String key, String value) {

        Jedis jedis = getJedis();
        jedis.select(0);
        jedis.set(key,value);

    }

    /**
     * 设置key的值为value,可选库
     *
     * @param key
     * @param value
     * @return
     */
    public void set(String key, String value,int index) {

        Jedis jedis = getJedis();
        jedis.select(index);
        jedis.set(key,value);

//        return jedis.set(key, value);
    }

    /**
     * 拿到testLilst长度
     * @return
     */

    public Long getLen(){
        Jedis jedis = getJedis();
        jedis.select(1);
        return jedis.llen("testList");
    }

    /**
     * pop数据
     */
    public String popData(){
        Jedis jedis = getJedis();
        jedis.select(1);
       return jedis.rpop("testList");
    }

    public String getTime(String gateWay){
        Jedis jedis = getJedis();
        jedis.select(1);
        return jedis.get(gateWay);
    }

    public void setTime(String gateWay,String startTime) {
        Jedis jedis = getJedis();
        jedis.select(1);
        jedis.set(gateWay, startTime);
    }

    /**
     * 删除key
     * @param keyName
     * @return
     */

    public boolean deleteKey(String keyName, int index){
        Jedis jedis = getJedis();
        jedis.select(1);


        if (jedis.exists(keyName)) {
            if (jedis.del(keyName) == 1) {
                System.out.println("删除数据成功");
                return true;
            } else {
                System.out.println("删除数据失败");
                return false;
            }
        } else {
            System.out.println(keyName + "不存在");
            return false;
        }
    }
}