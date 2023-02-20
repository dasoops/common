package com.dasoops.common.entity.enums.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.dasoops.common.entity.enums.base.BaseRedisKeyEnum.PLUGIN;

/**
 * @title RedisKeyEnum
 * @classPath com.dasoops.dasq.general.entity.RedisKeyEnum
 * @author DasoopsNicole@Gmail.com
 * @date 2022/10/07
 * @version 1.0.0
 * @description 复述, 关键枚举
 * @see Enum
 */
@Getter
@AllArgsConstructor
public enum RedisKeyEnum implements IRedisKeyEnum {

    /**
     * 未加载插件
     */
    UN_LOAD_PLUGIN(getBasePath() + "un_load_plugin"),
    /**
     * 已加载插件
     */
    LOAD_PLUGIN(getBasePath() + "load_plugin");

    private static String getBasePath() {
        return PLUGIN.getKey();
    }

    /**
     * 描述
     */
    final String key;
}
