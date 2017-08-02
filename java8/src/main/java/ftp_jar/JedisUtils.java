package ftp_jar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by wh on 2016/8/10.
 */
public class JedisUtils {

    private static final Logger logger = LoggerFactory.getLogger(JedisUtils.class);

    private static JedisUtils js;
    private static JedisPool pool;

    private JedisUtils() {
    }

    public static JedisUtils getInstance() {
        if (js == null) {
            synchronized (JedisUtils.class) {
                if (js == null) {
                    js = new JedisUtils();
                }
            }
        }
        return js;
    }

    /**
     * 初始化jedispool
     */
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(6000);
            config.setMaxIdle(2000);
            config.setMaxWaitMillis(-1);
            config.setMinEvictableIdleTimeMillis(1800000);
            config.setSoftMinEvictableIdleTimeMillis(1800000);
            config.setTestOnBorrow(true);
            pool = new JedisPool(config, ResourcesUtil.redisip(), Integer.valueOf(ResourcesUtil.redisport()),
                    10000, ResourcesUtil.redispwd());
        } catch (Exception e) {
            logger.info("exception:" + e.getMessage());
            pool = null;
        }

    }

    /**
     * 获取redis资源
     *
     * @return
     */
    public synchronized static Jedis getResource() {
        try {
            if (pool != null) {
                Jedis resource = pool.getResource();
                return resource;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 释放redis资源
     *
     * @param jedis
     */
    public static void returnResource(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }


    /**
     * String数据结构的插入
     */


    public void set(String key, String value) {
        Jedis jedis = getResource();
        jedis.set(key, value);
        returnResource(jedis);
    }


    public void del(String key) {
        Jedis jedis = getResource();
        jedis.del(key);
        returnResource(jedis);
    }

    public void set(String key, int time, String value) {
        Jedis jedis = getResource();
        jedis.setex(key, time, value);
        returnResource(jedis);
    }

    public String get(String key) {
        Jedis jedis = getResource();
        String value = jedis.get(key);
        returnResource(jedis);
        return value;
    }


    /**
     * 存入队列
     *
     * @param key
     * @param value
     */
    public void lpush(String key, String value) {
        Jedis jedis = getResource();

        if (jedis != null) {
            jedis.lpush(key, value);
            returnResource(jedis);
        }
    }

    /**
     * 移除队列
     *
     * @param key
     * @return
     */
    public String rpop(String key) {
        Jedis jedis = getResource();
        String value = "";
        if (jedis != null) {
            value = jedis.rpop(key);
            returnResource(jedis);
        }
        return value;
    }

}
