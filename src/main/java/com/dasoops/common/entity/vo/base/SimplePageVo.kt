package com.dasoops.common.entity.vo.base

import com.baomidou.mybatisplus.core.metadata.IPage
import com.dasoops.common.exception.NoRecordException
import io.swagger.annotations.ApiModelProperty

/**
 * 简单页面vo
 * @title: SimplePageVo
 * @classPath com.dasoops.common.entity.vo.base.SimplePageVo
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/28
 * @version 1.0.0
 * @see [SimplePageVo]
 */
class SimplePageVo<T : BaseInnerVo>(
    /**
     * 总记录数
     */
    @ApiModelProperty(value = "总记录数", notes = "总记录数", required = true)
    override val total: Int,

    /**
     * 数据集合
     */
    @ApiModelProperty(value = "数据集合", example = "[]", required = false)
    val dataList: List<T>
) : BasePageVo(total) {

    init {
        dataList.ifEmpty { throw NoRecordException }
    }
    constructor(dataList: List<T>) : this(dataList.size, dataList)
    constructor(page: IPage<T>) : this(page.total.toInt(), page.records)
}