package com.dasoops.common.core.entity.vo

import com.dasoops.common.exception.ProjectException
import io.swagger.v3.oas.annotations.media.Schema

/**
 * 简单分页vo
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/28
 * @see [SimpleListVo]
 */
class SimpleListVo<T : Any>(
    /**
     * 数据集合
     */
    @field:Schema(description = "数据集合", required = false)
    val dataList: List<T>
) {

    init {
        dataList.ifEmpty { throw ProjectException.NO_RECORD.get() }
    }

    companion object
}