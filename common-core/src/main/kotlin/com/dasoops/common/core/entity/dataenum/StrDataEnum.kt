package com.dasoops.common.core.entity.dataenum

import com.fasterxml.jackson.annotation.JsonValue

/**
 * str数据枚举
 * @author DasoopsNicole@Gmail.com
 * @date 2023/05/29
 * @see [StrDataEnum]
 */
interface StrDataEnum {
    @get:JsonValue
    val data: Int
}