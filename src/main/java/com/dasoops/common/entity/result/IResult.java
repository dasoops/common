package com.dasoops.common.entity.result;

import java.io.Serializable;

/**
 * @title BaseResult<T>
 * @classPath cn.qiaoml.bean.vo.result.BaseResult<T>
 * @author DasoopsNicole@Gmail.com
 * @date 2022/11/30
 * @version 1.0.0
 * @description 基本结果
 * @see Serializable
 */
public interface IResult extends Serializable {

    /**
     * 获取返回编号
     *
     * @return {@link Integer}
     */
    Integer getCode();

    /**
     * 设置返回编号
     *
     * @param code 编号
     */
    void setCode(Integer code);

    /**
     * 获取返回信息
     *
     * @return {@link String}
     */
    String getMsg();

    /**
     * 设置返回信息
     *
     * @param msg 信息
     */
    void setMsg(String msg);

}
