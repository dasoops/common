package com.dasoops.common.entity.dto.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @title BaseTimePageDto
 * @classPath cn.qiaoml.bean.dto.user.BaseTimePageDto
 * @author DasoopsNicole@Gmail.com
 * @date 2022/12/05
 * @version 1.0.0
 * @description 时间分页dto基类
 * @see BaseDto
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class BaseTimePageDto extends BasePageDto {

    /**
     * 开始时间
     */
    private String beginTime;

    /**
     * 结束时间
     */
    private String endTime;
}
