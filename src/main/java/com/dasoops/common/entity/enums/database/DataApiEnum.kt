package com.dasoops.common.entity.enums.database

/**
 * 含数据 api枚举
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/20
 * @see [DataApiEnum]
 */
interface DataApiEnum : ApiEnum {
    override val data: ApiEnumData
}