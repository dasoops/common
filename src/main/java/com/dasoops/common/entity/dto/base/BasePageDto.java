package com.dasoops.common.entity.dto.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @title BasePageDto
 * @classPath cn.qiaoml.bean.dto.BasePageDto
 * @author DasoopsNicole@Gmail.com
 * @date 2022/11/30
 * @version 1.0.0
 * @description 分页数据dto基类
 * @see BaseDto
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class BasePageDto<T> extends BaseDto {

    /**
     * 数据
     */
    private List<T> data;

    /**
     * 每页显示数量
     */
    private Integer size = 10;

    /**
     * 当前页码
     */
    private Integer current = 1;

    /**
     * 总数
     */
    private Integer total;

}
