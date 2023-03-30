package com.dasoops.common

import com.dasoops.common.exception.CustomException
import io.swagger.annotations.ApiModelProperty
import java.io.Serializable


/**
 * 自动初始化接口
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/24
 * @see [AutoInit]
 */
interface AutoInit {

    /**
     * 初始化方法
     */
    fun init()
}

/**
 * Cache顶层接口
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/19
 * @see [ICache]
 */
interface ICache

/**
 * 缓存管理器顶层接口
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/30
 * @see [ICacheManager]
 */
interface ICacheManager : AutoInit

/**
 * do顶层接口
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/15
 * @see [IDo]
 */
interface IDo : Serializable

/**
 * Param顶层接口
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/15
 * @see [IParam]
 */
interface IParam : Serializable

/**
 * result顶层接口
 * @author DasoopsNicole@Gmail.com
 * @date 2022/11/30
 * @see [IResult]
 */
interface IResult : Serializable {
    /**
     * 返回信息码
     */
    val code: Int

    /**
     * 返回信息
     */
    val msg: String
}

/**
 * service顶层接口
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/24
 * @see [IService]
 */
interface IService

/**
 * vo顶层接口
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/15
 * @see [IVo]
 */
interface IVo : Serializable

/**
 * exception顶层接口
 * @author DasoopsNicole@Gmail.com
 * @date 2023/01/14
 * @see [IException]
 */
interface IException {
    /**
     * 获取错误代码
     *
     * @return [Integer]
     */
    val code: Int

    /**
     * 获取错误描述
     *
     * @return [String]
     */
    val message: String

    /**
     * 获取异常
     *
     * @return [CustomException]
     */
    fun get(): CustomException
}

