package com.dasoops.common.entity.dataenum

import com.baomidou.mybatisplus.annotation.IEnum
import com.fasterxml.jackson.annotation.JsonValue

/**
 * @author DasoopsNicole@Gmail.com
 * @version 1.0.0
 * @title DataEnum
 * @classPath com.dasoops.common.dataenum.DataEnum
 * @date 2023/02/23
 * @description 数据库列枚举
 * @see IEnum
 */
interface DataEnum : IEnum<Int> {
    /**
     * 获取数据库值
     *
     * @return [Integer]
     */
    @get:JsonValue
    val value: Int

    override fun getValue() = value
}

/**
 * 含数据 api枚举
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/20
 * @see [DataApiEnum]
 */
interface DataApiEnum : ApiEnum {
    override val data: ApiEnumData
}

/**
 * api值枚举
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/20
 * @see [ApiEnum]
 */
interface ApiEnum : DataEnum {
    /**
     * 含义
     */
    val data: HashMap<String, String>?
        get() = null
}

/**
 * 简单api enum数据
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/20
 * @see [ApiEnumData]
 */
open class ApiEnumData : HashMap<String, String>()