package com.dasoops.common.dict

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
interface ApiEnum : IntDataEnum {
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