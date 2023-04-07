package com.dasoops.common.entity.vo

import com.dasoops.common.exception.ProjectException
import io.swagger.v3.oas.annotations.media.Schema

/**
 * 简单分页vo
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/28
 * @see [SimplePageVo]
 */
class SimplePageVo<T : Any>(
    override val page: PageVo,
    /**
     * 数据集合
     */
    @Schema(description = "数据集合", required = false)
    val dataList: List<T>
) : BasePageVo(page) {

    init {
        dataList.ifEmpty { throw ProjectException.NO_RECORD.get() }
    }

    constructor(total: Int, dataList: List<T>) : this(PageVo(total), dataList)

    companion object
}