package com.dasoops.common.entity.param.base

import io.swagger.annotations.ApiModelProperty

/**
 * pageParam顶层接口
 * @title: IPageParam
 * @classPath com.dasoops.common.entity.param.base.IPageParam
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/28
 * @version 1.0.0
 * @see [IPageParam]
 */
interface IPageParam {

    /**
     * 当前页码
     */
    val current:Int

    /**
     * 每页显示数量
     */
    val size:Int

}
