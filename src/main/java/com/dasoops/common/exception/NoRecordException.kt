package com.dasoops.common.exception

import com.dasoops.common.entity.enums.exception.ExceptionEnum

/**
 * 没有记录异常
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/02
 */
object NoRecordException : CustomException(ExceptionEnum.NO_RECORD)