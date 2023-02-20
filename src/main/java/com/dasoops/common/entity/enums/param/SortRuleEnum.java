package com.dasoops.common.entity.enums.param;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @title SortRuleEnum
 * @classPath com.dasoops.common.entity.enums.param.SortRuleEnum
 * @author DasoopsNicole@Gmail.com
 * @date 2023/01/12
 * @version 1.0.0
 * @description 排序规则枚举
 * @see Enum
 */
@AllArgsConstructor
@Getter
public enum SortRuleEnum {

    /**
     * 降序
     */
    DESC(0),
    /**
     * 升序
     */
    ASC(1),
    ;

    final Integer integerValue;

}
