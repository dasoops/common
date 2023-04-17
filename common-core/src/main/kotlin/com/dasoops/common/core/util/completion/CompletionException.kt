package com.dasoops.common.core.util.completion

import com.dasoops.common.core.IException
import com.dasoops.common.exception.CustomException


/**
 * 补全异常(201xx)
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/30
 * @see [CompletionException]
 */
enum class CompletionException(override val message: String) : IException {
    LIST_SIZE_NOT_MATCH("数据集合长度需与时间集合长度一致"),
    ;

    override val code: Int = 20100 + ordinal
    override fun get() = CompletionExceptionEntity(this)
    open class CompletionExceptionEntity(exception: IException) : CustomException(exception)
}