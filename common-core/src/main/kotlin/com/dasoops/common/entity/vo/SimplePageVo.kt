package com.dasoops.common.entity.vo

import com.dasoops.common.db.entity.vo.BasePageVo
import com.dasoops.common.exception.ProjectException
import io.swagger.annotations.ApiModelProperty

/**
 * 简单分页vo
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/28
 * @see [SimplePageVo]
 */
class SimplePageVo<T : Any>(
    /**
     * 总记录数
     */
    @ApiModelProperty(value = "总记录数", required = true)
    override val total: Int,

    /**
     * 数据集合
     */
    @ApiModelProperty(value = "数据集合", required = false)
    val dataList: List<T>
) : BasePageVo(total) {

    init {
        dataList.ifEmpty { throw ProjectException.NO_RECORD.get() }
    }

    constructor(dataList: List<T>) : this(dataList.size, dataList)

    companion object
}