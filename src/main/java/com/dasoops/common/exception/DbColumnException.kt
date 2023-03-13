package com.dasoops.common.exception

import com.dasoops.common.entity.enums.exception.IExceptionEnum
import com.dasoops.common.exception.DbColumnException.DbColumnExceptionEntity

/**
 * @Title: DbValueEnumExceptionEnum
 * @ClassPath com.dasoops.common.util.entity.DbValueEnumExceptionEnum
 * @Author DasoopsNicole@Gmail.com
 * @Date 2023/01/30
 * @Version 1.0.0
 * @Description: db值枚举异常枚举(902xx)
 * @see [DbColumnExceptionEntity]
 */
enum class DbColumnException(private val msg: String) : IExceptionEnum {

    NOT_ENUM("不是一个enum类"),
    UNDEFINEND_VALUE("未定义的枚举值"),
    ;

    override fun getCode(): Int {
        return 90200 + ordinal;
    }

    override fun getMsg(): String {
        return msg;
    }

    override fun get(): DbColumnExceptionEntity {
        return DbColumnExceptionEntity()
    }

    inner class DbColumnExceptionEntity : CustomException(this)
}