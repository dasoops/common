package com.dasoops.common.entity.vo.base

import io.swagger.annotations.ApiModelProperty

/**
 * 页面vo基类
 * @title: BasePageVo
 * @classPath com.dasoops.common.entity.vo.base.BasePageVo
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/28
 * @version 1.0.0
 * @see [BasePageVo]
 */
abstract class BasePageVo(
    /**
     * 总记录数
     */
    @ApiModelProperty(value = "总记录数", notes = "总记录数", required = true)
    open val total: Int
) : BaseVo()