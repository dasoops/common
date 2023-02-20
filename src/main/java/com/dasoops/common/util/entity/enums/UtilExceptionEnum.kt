package com.dasoops.common.util.entity.enums

import com.dasoops.common.entity.enums.exception.IExceptionEnum
import com.dasoops.common.exception.CustomException

/**
 * @Title: UtilException
 * @ClassPath com.dasoops.common.util.entity.enums.UtilException
 * @Author DasoopsNicole@Gmail.com
 * @Date 2023/02/18
 * @Version 1.0.0
 * @Description: 工具类异常
 * @see [UtilException]
 */
abstract class UtilException(exceptionEnum: IUtilExceptionEnum) : CustomException(exceptionEnum)

interface IUtilExceptionEnum : IExceptionEnum