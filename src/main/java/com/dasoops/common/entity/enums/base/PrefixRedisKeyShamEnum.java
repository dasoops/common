package com.dasoops.common.entity.enums.base;

import lombok.Getter;

/**
 * @title PrefixRedisKeyEnum
 * @classPath com.dasoops.common.entity.enums.base.PrefixRedisKeyEnum
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/13
 * @version 1.0.0
 * @description 前缀匹配redisKey枚举
 * @see IRedisKeyEnum
 */
@Getter
public class PrefixRedisKeyShamEnum implements IRedisKeyEnum {

    private final String key;

    public PrefixRedisKeyShamEnum(String key) {
        this.key = key;
    }
}
