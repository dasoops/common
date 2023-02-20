package com.dasoops.common.entity.enums.base

/**
 * @Title: IRedisKeyEnum
 * @ClassPath com.dasoops.common.entity.enums.base.IRedisKeyEnum
 * @Author DasoopsNicole@Gmail.com
 * @Date 2023/01/31
 * @Version 1.0.0
 * @Description: iredis enum关键
 * @see [IRedisKeyEnum]
 */
interface IRedisKeyEnum {

    /**
     * 获取key
     * @return [String]
     */
    fun getKey(): String;

}