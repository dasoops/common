package com.dasoops.common.db.entity.enums

import com.dasoops.common.entity.enums.database.IDbColumnEnum

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
    val data: HashMap<String, String>?
        get() = null
}