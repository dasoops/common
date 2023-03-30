package com.dasoops.common.entity.param.base

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.dasoops.common.entity.dbo.base.BaseDo
import io.swagger.annotations.ApiModelProperty

/**
 * @title BaseEditParam
 * @classPath com.dasoops.common.entity.param.base.BaseEditParam
 * @author DasoopsNicole@Gmail.com
 * @date 2023/01/12
 * @version 1.0.0
 * @description 编辑param基类
 * @see BaseFastBuildParam
 */
abstract class BaseEditAndDeleteParam<T : BaseDo>(
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id", notes = "主键id", example = "1", required = true)
    open val rowId: Long
) : BaseFastBuildParam<T>() {
    override fun buildQueryWrapper(): QueryWrapper<T> {
        return super.buildQueryWrapper().eq("rowId", rowId)
    }
}