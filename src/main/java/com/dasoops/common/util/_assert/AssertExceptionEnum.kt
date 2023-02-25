package com.dasoops.common.util._assert

import com.dasoops.common.entity.enums.exception.IExceptionEnum
import com.dasoops.common.util.IUtilExceptionEnum
import com.dasoops.common.util.UtilException

/**
 * @author DasoopsNicole@Gmail.com
 * @version 1.0.0
 * @title ExportExceptionEnum
 * @classPath com.dasoops.common.util.export.ExportExceptionEnum
 * @date 2022/12/30
 * @description 断言异常(3xxx)
 * @see IExceptionEnum
 */
enum class AssertExceptionEnum(private val msg: String) : IUtilExceptionEnum {

    IS_TRUE("值必须为true"),
    IS_FALSE("值必须为false"),
    PARAMETER_NOT_NULL("参数不可为空"),
    PARAMETER_IS_NULL("参数必须为空"),
    PARAMETER_OUT_OF_SCOPE("超出界定范围的参数"),
    DB_EXECUTE_FAILED("数据库操作失败"),
    DB_EXECUTE_RETURN_NOT_ZERO("数据库执行生效记录数为0"),
    DB_EXECUTE_RETURN_NOT_NULL("数据库返回不应为null"),
    ;

    override fun getCode(): Int {
        return 3000 + ordinal
    }

    override fun getMsg(): String {
        return msg
    }

    override fun getException(): AssertException {
        return AssertException(this)
    }

    class AssertException(exceptionEnum: AssertExceptionEnum) : UtilException(exceptionEnum)
}