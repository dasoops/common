package com.dasoops.common.cache;

import cn.hutool.core.lang.func.VoidFunc0;
import com.dasoops.common.entity.enums.base.IRedisHashKeyEnum;
import com.dasoops.common.entity.enums.base.IRedisKeyEnum;
import com.dasoops.common.entity.enums.base.PrefixRedisKeyShamEnum;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import com.alibaba.fastjson2.JSON;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @title BaseCache
 * @classPath com.dasoops.common.cache.BaseCache
 * @author DasoopsNicole@Gmail.com
 * @date 2023/01/01
 * @version 1.0.0
 * @description 基地缓存
 */
public class BaseCache {

    private final StringRedisTemplate redis;

    public BaseCache(StringRedisTemplate redis) {
        this.redis = redis;
    }

    /* -- Operations Begin -- */

    private ValueOperations<String, String> value() {
        return redis.opsForValue();
    }

    private ListOperations<String, String> list() {
        return redis.opsForList();
    }

    private SetOperations<String, String> set() {
        return redis.opsForSet();
    }

    private ZSetOperations<String, String> zSet() {
        return redis.opsForZSet();
    }

    private HashOperations<String, String, String> hash() {
        return redis.opsForHash();
    }

    /* -- Operations End -- */

    /* -- Global Begin -- */

    protected Set<String> keys() {
        return redis.keys("*");
    }

    protected Set<String> keys(String keyword) {
        return redis.keys("*" + keyword + "*");
    }

    protected Set<String> keys4Prefix(IRedisKeyEnum redisKeyEnum) {
        return redis.keys(redisKeyEnum.getKey() + "*");
    }

    protected void remove(IRedisKeyEnum redisKeyEnum) {
        redis.delete(redisKeyEnum.getKey());
    }

    protected void remove4Prefix(IRedisKeyEnum redisKeyEnum) {
        Set<String> keys = this.keys(redisKeyEnum.getKey() + "*");
        if (keys != null && keys.size() > 0) {
            redis.delete(keys);
        }
    }

    protected void expire(IRedisKeyEnum redisKeyEnum, Long timeout, TimeUnit timeUnit) {
        redis.expire(redisKeyEnum.getKey(), timeout, timeUnit);
    }

    protected void expire4Prefix(IRedisKeyEnum redisKeyEnum, Long timeout, TimeUnit timeUnit) {
        Set<String> keys = redis.keys(redisKeyEnum.getKey() + "*");
        if (keys == null) {
            return;
        }
        keys.forEach(key -> redis.expire(key, timeout, timeUnit));
    }

    protected DataType type(IRedisKeyEnum redisKeyEnum) {
        return redis.type(redisKeyEnum.getKey());
    }

    protected String getJSONString(IRedisKeyEnum redisKeyEnum) {
        DataType type = type(redisKeyEnum);
        switch (type) {
            case SET -> {
                return JSON.toJSONString(members(redisKeyEnum));
            }
            case HASH -> {
                return JSON.toJSONString(entries(redisKeyEnum));
            }
            case LIST -> {
                return JSON.toJSONString(lget(redisKeyEnum));
            }
            case STRING -> {
                return get(redisKeyEnum);
            }
            default -> {
                return null;
            }
        }
    }

    protected void transaction(VoidFunc0 func) {
        redis.setEnableTransactionSupport(true);
        redis.multi();

        func.callWithRuntimeException();

        redis.exec();
        redis.setEnableTransactionSupport(false);
    }

    protected Boolean hasKey(IRedisKeyEnum redisKey) {
        return redis.hasKey(redisKey.getKey());
    }
    /* -- Global End -- */

    /* -- Value Begin -- */

    protected void set(IRedisKeyEnum redisKeyEnum, String value) {
        value().set(redisKeyEnum.getKey(), value);
    }

    protected String get(IRedisKeyEnum redisKeyEnum) {
        return value().get(redisKeyEnum.getKey());
    }

    protected String getAndDelete(IRedisKeyEnum redisKeyEnum) {
        return value().getAndDelete(redisKeyEnum.getKey());
    }

    protected void setAndExpire(IRedisKeyEnum redisKeyEnum, String value, Long time, TimeUnit timeUnit) {
        value().set(redisKeyEnum.getKey(), value);
        expire(redisKeyEnum, time, timeUnit);
    }
    /* -- Value End -- */

    /* -- Hash Begin -- */

    protected Map<String, String> entries(IRedisKeyEnum redisKeyEnum) {
        return hash().entries(redisKeyEnum.getKey());
    }

    protected Map<String, Map<String, String>> entries4Prefix(PrefixRedisKeyShamEnum redisKeyShamEnum) {
        Set<String> keySet = this.keys(redisKeyShamEnum.getKey());
        return keySet.stream().collect(Collectors.toMap(key -> key, key -> hash().entries(key)));
    }

    protected String hget(IRedisKeyEnum redisKeyEnum, IRedisHashKeyEnum redisHashKeyEnum) {
        return hash().get(redisKeyEnum.getKey(), redisHashKeyEnum.getKey());
    }

    protected String hget(IRedisKeyEnum redisKeyEnum, String redisHashKey) {
        return hash().get(redisKeyEnum.getKey(), redisHashKey);
    }

    protected void hset(IRedisKeyEnum redisKeyEnum, IRedisHashKeyEnum redisHashKeyEnum, String value) {
        hash().put(redisKeyEnum.getKey(), redisHashKeyEnum.getKey(), value);
    }

    protected void hset(IRedisKeyEnum redisKeyEnum, String hashKey, String value) {
        hash().put(redisKeyEnum.getKey(), hashKey, value);
    }

    protected void hset(IRedisKeyEnum redisKeyEnum, Map<String, String> valueMap) {
        hash().putAll(redisKeyEnum.getKey(), valueMap);
    }

    protected boolean hhasKey(IRedisKeyEnum redisKeyEnum, String hashKey) {
        return hash().hasKey(redisKeyEnum.getKey(), hashKey);
    }

    protected String hgetAndDelete(IRedisKeyEnum redisKeyEnum, String hashKey) {
        boolean b = this.hhasKey(redisKeyEnum, hashKey);
        if (!b) {
            return null;
        }
        String str = hash().get(redisKeyEnum.getKey(), hashKey);
        hdelete(redisKeyEnum, hashKey);
        return str;
    }

    protected void hdelete(IRedisKeyEnum redisKeyEnum, String hashKey) {
        hash().delete(redisKeyEnum.getKey(), hashKey);
    }

    /* -- Hash End -- */

    /* -- List Begin -- */

    protected void lset(IRedisKeyEnum redisKeyEnum, List<String> valueList) {
        list().rightPushAll(redisKeyEnum.getKey(), valueList);
    }

    protected void lset(IRedisKeyEnum redisKeyEnum, String value) {
        list().rightPush(redisKeyEnum.getKey(), value);
    }

    protected List<String> lget(IRedisKeyEnum redisKeyEnum) {
        return list().range(redisKeyEnum.getKey(), 0, -1);
    }

    protected String lget(IRedisKeyEnum redisKeyEnum, Integer index) {
        return list().index(redisKeyEnum.getKey(), index);
    }

    /* -- List End -- */

    /* -- Set Begin -- */

    protected void sadd(IRedisKeyEnum redisKeyEnum, List<String> valueList) {
        set().add(redisKeyEnum.getKey(), valueList.toArray(new String[0]));
    }

    protected void sadd(IRedisKeyEnum redisKeyEnum, Set<String> valueSet) {
        set().add(redisKeyEnum.getKey(), valueSet.toArray(new String[0]));
    }

    protected void saddAndExpire(IRedisKeyEnum redisKeyEnum, Set<String> valueSet, Long time, TimeUnit timeUnit) {
        this.transaction(() -> {
            set().add(redisKeyEnum.getKey(), valueSet.toArray(new String[0]));
            redis.expire(redisKeyEnum.getKey(), time, timeUnit);
        });
    }

    protected void sadd(IRedisKeyEnum redisKeyEnum, String value) {
        set().add(redisKeyEnum.getKey(), value);
    }

    protected Set<String> members(IRedisKeyEnum redisKeyEnum) {
        return set().members(redisKeyEnum.getKey());
    }

    /**
     * 根据前缀获取所有项并分组
     *
     * @param redisKeyEnum 复述,关键枚举
     * @param groupingKeyConvertFunction 分组键转换函数
     * @param valueConvertFunction 分组值转换函数
     * @return {@link Map}<{@link R1}, {@link Set}<{@link R2}>>
     */
    protected <R1, R2> Map<R1, Set<R2>> sGetGroupingByPrefix(IRedisKeyEnum redisKeyEnum, Function<String, R1> groupingKeyConvertFunction, Function<String, R2> valueConvertFunction) {
        Set<String> keyStringSet = this.keys4Prefix(redisKeyEnum);
        HashMap<R1, Set<R2>> resultMap = new HashMap<>(keyStringSet.size());
        for (String key : keyStringSet) {
            Set<String> members = set().members(key);
            if (members == null) {
                return resultMap;
            }
            Set<R2> set = members.stream().map(valueConvertFunction).collect(Collectors.toSet());
            String keyString = key.substring(key.lastIndexOf(":") + 1);
            R1 groupingKey = groupingKeyConvertFunction.apply(keyString);
            resultMap.put(groupingKey, set);
        }
        return resultMap;
    }

    protected Map<String, Set<String>> sGetGroupingByPrefix(IRedisKeyEnum redisKeyEnum) {
        return this.sGetGroupingByPrefix(redisKeyEnum, string -> string, string -> string);
    }

    /* -- Set End -- */
}
