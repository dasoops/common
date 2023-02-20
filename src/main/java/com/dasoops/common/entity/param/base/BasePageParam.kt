//package com.dasoops.common.entity.param.base
//
//import com.baomidou.mybatisplus.core.metadata.IPage
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page
//import com.dasoops.common.entity.dbo.base.BaseDo
//import io.swagger.annotations.ApiModelProperty
//import org.apache.poi.ss.formula.functions.T
//
///**
// * @Title: BasePageParam
// * @ClassPath com.dasoops.common.entity.param.base.BasePageParam
// * @Author DasoopsNicole@Gmail.com
// * @Date 2022/12/28
// * @Version 1.0.0
// * @Description: 基本页面参数
// */
//abstract class BasePageParam<T : BaseDo> : BaseFastBuildParam<T>(), IBuildSelectPage<T> {
//    /**
//     * 每页显示数量
//     */
//    @ApiModelProperty(value = "每页显示数量", notes = "每页显示数量", required = false)
//    var size: Int = 10
//
//    /**
//     * 当前页码
//     */
//    @ApiModelProperty(value = "当前页码", notes = "当前页码", required = false)
//    var current: Int = 1
//
//    override fun buildSelectPage(): IPage<T> {
//        return Page(current.toLong(), size.toLong())
//    }
//}

package com.dasoops.common.entity.param.base

import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.dasoops.common.entity.dbo.base.BaseDo
import io.swagger.annotations.ApiModelProperty

/**
 * @Title: BasePageParam
 * @ClassPath com.dasoops.common.entity.param.base.BasePageParam
 * @Author DasoopsNicole@Gmail.com
 * @Date 2022/12/28
 * @Version 1.0.0
 * @Description: 基本页面参数
 */
abstract class BasePageParam<T : BaseDo> : BaseFastBuildParam<T>(), IBuildSelectPage<T> {
    /**
     * 每页显示数量
     */
    @ApiModelProperty(value = "每页显示数量", notes = "每页显示数量", example = "10", required = false)
    var size = 10

    /**
     * 当前页码
     */
    @ApiModelProperty(value = "当前页码", notes = "当前页码", example = "1", required = false)
    var current = 1

    override fun buildSelectPage(): IPage<T> {
        return Page(current.toLong(), size.toLong())
    }
}