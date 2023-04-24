package com.dasoops.common.core.entity.dataenum

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
    val data: Int

    override fun getValue(): Int {
        return data
    }
}

/**
 * 含数据 api枚举
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/20
 * @see [DataApiEnum]
 */
interface DataApiEnum : ApiEnum {
    override val dataMap: ApiEnumData
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
    val dataMap: HashMap<String, String>?
        get() = null
}

/**
 * 简单api enum数据
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/20
 * @see [ApiEnumData]
 */
open class ApiEnumData : HashMap<String, String>()