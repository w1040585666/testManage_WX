package com.hw.example.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * jedis操作类
 * @author wang xin.
 * @version 1.0
 * @date 2019/8/2 15:10.
 * @Copyright：2018 汉王智远科技有限公司 All rights reserved.
 */
@Component
public class RedisClient {

    protected final static Logger logger = LoggerFactory.getLogger(RedisClient.class);

    @Autowired
    private JedisPool jedisPool;

    /**
     * 设置缓存
     * @param key 键
     * @param value 值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public String set(String key, String value, int cacheSeconds) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.set(key, value);
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
            logger.debug("set "+ key + " = " + value);
        } catch (Exception e) {
            logger.warn("set "+ key + " = " + value);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
        return result;
    }

    /**
     * 设置缓存
     * @param key 键
     * @param value 值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public String set(byte[] key, byte[] value, int cacheSeconds) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.set(key, value);
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
            logger.debug("set "+ key + " = " + value);
        } catch (Exception e) {
            logger.warn("set "+ key + " = " + value);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
        return result;
    }

    /**
     * 设置缓存
     * @param key 键
     * @param value 值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public String setObject(String key, Object value, int cacheSeconds) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.set(JSON.toJSONBytes(key), JSON.toJSONBytes(value));
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
            logger.debug("setObject "+ key + " = " + value);
        } catch (Exception e) {
            logger.warn("setObject "+ key + " = " + value);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
        return result;
    }

    /**
     * 获取缓存
     * @param key 键
     * @return 值
     */
    public String get(String key) {
        String value = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (jedis.exists(key)) {
                value = jedis.get(key);
                //value = StringUtil.isNotBlank(value) && !"nil".equalsIgnoreCase(value) ? value : null;
                logger.debug("get "+ key + " = " + value);
            }
        } catch (Exception e) {
            logger.warn("get "+ key + " = " + value);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
        return value;
    }

    /**
     * 获取缓存
     * @param key 键
     * @return 值
     */
    public byte[] get(byte[] key) {
        byte[] value = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (jedis.exists(key)) {
                value = jedis.get(key);
                logger.debug("get "+ key + " = " + value);
            }
        } catch (Exception e) {
            logger.warn("get "+ key + " = " + value);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
        return value;
    }

    /**
     * 获取缓存
     * @param key 键
     * @return 值
     */
    public Object getObject(String key,Class<?> clazz) {
        Object value = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (jedis.exists(JSON.toJSONBytes(key))) {
                value = JSON.parseObject(((JSONObject) JSON.parse(jedis.get(JSON.toJSONBytes(key)))).toString(), clazz);
                logger.debug("getObject "+ key + " = " + value);
            }
        } catch (Exception e) {
            logger.warn("getObject "+ key + " = " + value);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
        return value;
    }

    /**
     * 缓存是否存在
     * @param key 键
     * @return
     */
    public boolean exists(String key) {
        boolean result = false;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.exists(key);
            logger.debug("exists "+ key );
        } catch (Exception e) {
            logger.warn("exists "+ key );
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
        return result;
    }

    /**
     * 缓存是否存在
     * @param key 键
     * @return
     */
    public boolean existsObject(String key) {
        boolean result = false;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.exists(JSON.toJSONBytes(key));
            logger.debug("existsObject "+ key );
        } catch (Exception e) {
            logger.warn("existsObject "+ key );
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
        return result;
    }

    /**
     * 对某个键的值自增
     * @author liboyi
     * @param key 键
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public long setIncr(String key, int cacheSeconds) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result =jedis.incr(key);
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
            logger.debug("set "+ key + " = " + result);
        } catch (Exception e) {
            logger.warn("set "+ key + " = " + result);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
        return result;
    }

    public Set<String> keys(String key) {
        Set<String> value = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (jedis.exists(key)) {
                value = jedis.keys(key);
                logger.debug("keys "+ key + " = " + value);
            }
        } catch (Exception e) {
            logger.warn("get "+ key + " = " + value);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
        return value;
    }

    /**
     * 获取List缓存
     * @param key 键
     * @return 值
     */
    public List<String> getList(String key) {
        List<String> value = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (jedis.exists(key)) {
                value = jedis.lrange(key, 0, -1);
                logger.debug("getList "+ key + " = " + value);
            }
        } catch (Exception e) {
            logger.warn("getList "+ key + " = " + value);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
        return value;
    }

    /**
     * 设置List缓存
     * @param key 键
     * @param value 值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public long setList(String key, List<String> value, int cacheSeconds) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (jedis.exists(key)) {
                jedis.del(key);
            }
            result = jedis.rpush(key, (String[])value.toArray());
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
            logger.debug("setList "+ key + " = " + value);
        } catch (Exception e) {
            logger.warn("setList "+ key + " = " + value);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
        return result;
    }

    /**
     * 向List缓存中添加值
     * @param key 键
     * @param value 值
     * @return
     */
    public long listAdd(String key, String... value) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.rpush(key, value);
            logger.debug("listAdd "+ key + " = " + value);
        } catch (Exception e) {
            logger.warn("listAdd "+ key + " = " + value);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
        return result;
    }

    /**
     * 获取缓存
     * @param key 键
     * @return 值
     */
    public Set<String> getSet(String key) {
        Set<String> value = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (jedis.exists(key)) {
                value = jedis.smembers(key);
                logger.debug("getSet "+ key + " = " + value);
            }
        } catch (Exception e) {
            logger.warn("getSet "+ key + " = " + value);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
        return value;
    }

    /**
     * 设置Set缓存
     * @param key 键
     * @param value 值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public long setSet(String key, Set<String> value, int cacheSeconds) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (jedis.exists(key)) {
                jedis.del(key);
            }
            result = jedis.sadd(key, (String[])value.toArray());
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
            logger.debug("setSet "+ key + " = " + value);
        } catch (Exception e) {
            logger.warn("setSet "+ key + " = " + value);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
        return result;
    }

    /**
     * 向Set缓存中添加值
     * @param key 键
     * @param value 值
     * @return
     */
    public long setSetAdd(String key, String... value) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.sadd(key, value);
            logger.debug("setSetAdd "+ key + " = " + value);
        } catch (Exception e) {
            logger.warn("setSetAdd "+ key + " = " + value);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
        return result;
    }

    /**
     * 获取Map缓存
     * @param key 键
     * @return 值
     */
    public Map<String, String> getMap(String key) {
        Map<String, String> value = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (jedis.exists(key)) {
                value = jedis.hgetAll(key);
                logger.debug("getMap "+ key + " = " + value);
            }
        } catch (Exception e) {
            logger.warn("getMap "+ key + " = " + value);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
        return value;
    }

    /**
     * 设置Map缓存
     * @param key 键
     * @param value 值
     * @param cacheSeconds 超时时间，0为不超时
     * @return
     */
    public String setMap(String key, Map<String, String> value, int cacheSeconds) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (jedis.exists(key)) {
                jedis.del(key);
            }
            result = jedis.hmset(key, value);
            if (cacheSeconds != 0) {
                jedis.expire(key, cacheSeconds);
            }
            logger.debug("setMap "+ key + " = " + value);
        } catch (Exception e) {
            logger.warn("setMap "+ key + " = " + value);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
        return result;
    }

    /**
     * 向Map缓存中添加值
     * @param key 键
     * @param value 值
     * @return
     */
    public String mapPut(String key, Map<String, String> value) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.hmset(key, value);
            logger.debug("mapPut "+ key + " = " + value);
        } catch (Exception e) {
            logger.warn("mapPut "+ key + " = " + value);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
        return result;
    }

    /**
     * 移除Map缓存中的值
     * @param key 键
     * @param mapKey 值
     * @return
     */
    public long mapRemove(String key, String mapKey) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.hdel(key, mapKey);
            logger.debug("mapRemove "+ key + " = " + mapKey);
        } catch (Exception e) {
            logger.warn("mapRemove "+ key + " = " + mapKey);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
        return result;
    }

    /**
     * 判断Map缓存中的Key是否存在
     * @param key 键
     * @param mapKey 值
     * @return
     */
    public boolean mapExists(String key, String mapKey) {
        boolean result = false;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.hexists(key, mapKey);
            logger.debug("mapExists "+ key + " = " + mapKey);
        } catch (Exception e) {
            logger.warn("mapExists "+ key + " = " + mapKey);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
        return result;
    }

    /**
     * 判断Map缓存中的Key是否存在
     * @param key 键
     * @param mapKey 值
     * @return
     */
    public boolean mapObjectExists(String key, String mapKey) {
        boolean result = false;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.hexists(JSON.toJSONBytes(key), JSON.toJSONBytes(mapKey));
            logger.debug("mapObjectExists "+ key + " = " + mapKey);
        } catch (Exception e) {
            logger.warn("mapObjectExists "+ key + " = " + mapKey);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
        return result;
    }

    /**
     * 删除缓存
     * @param key 键
     * @return
     */
    public long del(String key) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (jedis.exists(key)){
                result = jedis.del(key);
                logger.debug("del "+ key );
            }else{
                logger.debug("del "+ key + "not exists");
            }
        } catch (Exception e) {
            logger.warn("del "+ key);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
        return result;
    }

    /**
     * 删除缓存
     * @param key 键
     * @return
     */
    public long del(String[] key) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.del(key);
            logger.debug("del "+ key );
        } catch (Exception e) {
            logger.warn("del "+ key);
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
        return result;
    }

    /**
     * 删除缓存
     * @param key 键
     * @return
     */
    public long delObject(String key) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (jedis.exists(key)){
                result = jedis.del(key);
                logger.debug("delObject "+ key );
            }else{
                logger.debug("delObject "+ key + "not exists");
            }
        } catch (Exception e) {
            logger.warn("delObject "+ key + "not exists");
        } finally {
            if (jedis != null) {
                jedisPool.returnResource(jedis);
            }
        }
        return result;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }
}
