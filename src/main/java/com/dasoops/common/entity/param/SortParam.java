package com.dasoops.common.entity.param;

import cn.hutool.core.util.EnumUtil;
import com.dasoops.common.entity.dbo.base.BaseDo;
import com.dasoops.common.entity.dto.base.BaseDto;
import com.dasoops.common.entity.enums.ExceptionEnum;
import com.dasoops.common.entity.enums.SortRuleEnum;
import com.dasoops.common.entity.enums.base.ISortColumnEnum;
import com.dasoops.common.entity.param.base.BaseFastBuildParam;
import com.dasoops.common.exception.LogicException;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Arrays;
import java.util.Optional;

/**
 * @title SortDto
 * @classPath com.dasoops.common.entity.dto.SortDto
 * @author DasoopsNicole@Gmail.com
 * @date 2023/01/12
 * @version 1.0.0
 * @description 排序dto
 * @see BaseDto
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SortParam<T extends BaseDo> extends BaseFastBuildParam<T> {

    /**
     * 排序字段
     */
    @ApiModelProperty(value = "排序字段", notes = "排序字段", example = "0", required = true)
    private Integer sortColumn;

    /**
     * 排序规则(0:降序;1:升序)
     */
    @ApiModelProperty(value = "排序规则(0:降序;1:升序)", notes = "排序规则(0:降序;1:升序)", example = "0", required = true)
    private Integer sortRule;

    public <R extends ISortColumnEnum> R buildColumnEnum(Class<R> clazz) {
        R[] enumConstants = clazz.getEnumConstants();
        Optional<R> enumOptional = Arrays.stream(enumConstants).filter(enumConstant -> enumConstant.getIntegerValue().equals(sortColumn)).findFirst();
        if (enumOptional.isEmpty()) {
            throw new LogicException(ExceptionEnum.UNKNOWN_KEYWORD);
        }
        return enumOptional.get();
    }

    public SortRuleEnum buildRuleEnum() {
        SortRuleEnum sortRuleEnum = EnumUtil.getBy(SortRuleEnum::getIntegerValue, sortRule);
        if (sortRuleEnum == null) {
            throw new LogicException(ExceptionEnum.UNKNOWN_KEYWORD);
        }
        return sortRuleEnum;
    }

}
