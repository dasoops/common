package com.dasoops.common.entity.enums.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author DasoopsNicole@Gmail.com
 * @version 1.0.0
 * @title BaseRedisKeyEnum
 * @classPath com.dasoops.common.entity.enums.cache.BaseRedisKeyEnum
 * @date 2023/01/31
 * @description RedisKey枚举
 * @see Enum
 */
@AllArgsConstructor
public enum CacheModuleEnum {

    /**
     * 核心
     */
    CORE("core:"),

    /**
     * 字典
     */
    CONFIG(CORE.key + "dict:"),

    /**
     * 权限
     */
    AUTH(CORE.key + "auth:"),
    ;

    @Getter
    final String key;
}
