package com.dasoops.common.code.entity

import com.dasoops.common.entity.dataenum.ApiEnum
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag

/**
 * 代码类型
 * @author DasoopsNicole@Gmail.com
 * @date 2023/04/06
 * @see [CodeType]
 */
@Tag(name = "代码类型")
@Schema(description = "代码类型")
enum class CodeType(override val data: Int) : ApiEnum {
    TS(0),
}
