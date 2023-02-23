package com.dasoops.common.exception

import com.dasoops.common.entity.enums.exception.ExceptionEnum

/**
 * 预期外的异常
 * @title: UnExpectedException
 * @classPath com.dasoops.common.exception.UnExpectedException
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/23
 * @version 1.0.0
 * @see [UnexpectedException]
 */
class UnexpectedException : CustomException(ExceptionEnum.UN_EXPECTED)