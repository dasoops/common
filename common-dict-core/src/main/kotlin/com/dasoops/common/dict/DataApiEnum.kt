package com.dasoops.common.dict

import com.dasoops.common.json.core.dataenum.IntDataEnum

/**
 * api值枚举
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/20
 * @see [ApiEnum]
 */
interface ApiEnum : IntDataEnum {
    /**
     * 含义
     */
    val dataMap: HashMap<String, Any>?
        get() = null
}

/**
 * 简单api enum数据
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/20
 * @see [ApiEnumData]
 */
open class ApiEnumData : HashMap<String, Any>()