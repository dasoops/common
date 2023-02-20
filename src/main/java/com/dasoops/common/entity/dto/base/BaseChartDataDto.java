package com.dasoops.common.entity.dto.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @title IndexDataDto
 * @classPath cn.qiaoml.bean.dto.history.IndexDataDto
 * @author DasoopsNicole@Gmail.com
 * @date 2022/11/25
 * @version 1.0.0
 * @description 带索引数据dto基类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class BaseChartDataDto extends BaseDto {

    /**
     * 索引
     */
    private List<String> indexList;

}
