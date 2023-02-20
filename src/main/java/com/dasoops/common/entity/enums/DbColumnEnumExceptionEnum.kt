package com.dasoops.common.entity.enums

import com.dasoops.common.entity.enums.base.IExceptionEnum

/**
 * @Title: DbValueEnumExceptionEnum
 * @ClassPath com.dasoops.common.util.entity.DbValueEnumExceptionEnum
 * @Author DasoopsNicole@Gmail.com
 * @Date 2023/01/30
 * @Version 1.0.0
 * @Description: db值枚举异常枚举(902xx)
 * @see [DbColumnEnumExceptionEnum]
 */
enum class DbColumnEnumExceptionEnum(private val msg: String) : IExceptionEnum {

    NOT_ENUM("不是一个enum类"),
    UNDEFINEND_BOOLEAN_VALUE("未定义的Boolean枚举值"),
    ;

    override fun getCode(): Int {
        return 90200 + ordinal;
    }

    override fun getMsg(): String {
        return msg;
    }
}