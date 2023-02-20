package com.dasoops.common.entity.vo.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @title BaseChartDataDto
 * @classPath cn.qiaoml.bean.vo.BaseChartDataDto
 * @author DasoopsNicole@Gmail.com
 * @date 2022/11/28
 * @version 1.0.0
 * @description 图表数据vo基类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class BaseChartDataVo extends BaseVo{

    /**
     * 索引
     */
    @ApiModelProperty(value = "索引", notes = "索引", example = "[\"1月1日\",\"1月2日\"]", required = true)
    private List<String> indexList;

}
