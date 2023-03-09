package com.dasoops.common.entity.param

import com.dasoops.common.entity.dbo.base.BaseDo
import com.dasoops.common.entity.param.base.BaseEditAndDeleteParam
import io.swagger.annotations.Api
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * 简单删除param
 * @title: SimpleDeleteParam
 * @classPath com.dasoops.common.entity.param.SimpleDeleteParam
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/09
 * @version 1.0.0
 * @see [SimpleDeleteParam]
 */
@Api("any")
@ApiModel(description = "编辑param基类")
class SimpleDeleteParam<T : BaseDo>(
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id", example = "1", required = true)
    override var rowId: Long
) : BaseEditAndDeleteParam<T>(rowId)