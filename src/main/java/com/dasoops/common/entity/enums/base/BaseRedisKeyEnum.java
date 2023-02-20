package com.dasoops.common.entity.enums.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @title BaseRedisKeyEnum
 * @classPath com.dasoops.common.entity.enums.base.BaseRedisKeyEnum
 * @author DasoopsNicole@Gmail.com
 * @date 2023/01/31
 * @version 1.0.0
 * @description RedisKey枚举
 * @see Enum
 */
@AllArgsConstructor
@Getter
public enum BaseRedisKeyEnum implements IRedisKeyEnum {

    /**
     * 核心
     */
    CORE("core:"),

    /**
     * 插件
     */
    PLUGIN("plugin:"),

    /**
     * 注册表
     */
    REGISTER(CORE.key + "register:"),

    /**
     * 配置
     */
    CONFIG(CORE.key + "config:"),

    /**
     * 系统级插件(默认增强等)
     */
    SYS_PLUGIN(CORE.key + "plugin:"),

    /**
     * 权限
     */
    AUTH(CORE.key + "auth:"),
    ;

    final String key;


    @Override
    public String toString() {
        return key;
    }
}
