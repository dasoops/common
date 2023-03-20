package com.dasoops.common.entity.enums.database

/**
 * api值枚举
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/20
 * @see [ApiEnum]
 */
interface ApiEnum : IDbColumnEnum {
    /**
     * 含义
     */
    val key: String
}