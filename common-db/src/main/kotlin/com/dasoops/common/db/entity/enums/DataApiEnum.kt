package com.dasoops.common.entity.enums.database

import com.dasoops.common.db.entity.enums.ApiEnum

/**
 * 含数据 api枚举
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/20
 * @see [DataApiEnum]
 */
interface DataApiEnum : ApiEnum {
    override val data: ApiEnumData
}