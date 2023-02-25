package com.dasoops.common.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dasoops.common.entity.dbo.base.BaseDo;
import com.dasoops.common.entity.enums.param.ISortColumnEnum;
import com.dasoops.common.entity.enums.param.SortRuleEnum;
import com.dasoops.common.entity.param.SortParam;
import com.dasoops.common.exception.CustomException;
import com.dasoops.common.util._assert.AssertExceptionEnum;

import java.util.List;

/**
 * @title SortParamUtil
 * @classPath com.dasoops.common.util.SortParamUtil
 * @author DasoopsNicole@Gmail.com
 * @date 2023/01/13
 * @version 1.0.0
 * @description 排序param工具
 */
public class QueryWrapperUtil {

    public static <R extends BaseDo, E extends ISortColumnEnum> QueryWrapper<R> addSortParam(final QueryWrapper<R> wrapper, List<SortParam<R>> paramList, Class<E> enumClass) {
        if (wrapper == null) {
            throw new CustomException(AssertExceptionEnum.PARAMETER_NOT_NULL);
        }

        if (paramList == null || paramList.size() <= 0) {
            return wrapper;
        }

        paramList.forEach(param -> {
            SortRuleEnum sortRuleEnum = param.buildRuleEnum();
            E sortColumnEnum = param.buildColumnEnum(enumClass);
            wrapper.orderBy(true, sortRuleEnum.equals(SortRuleEnum.ASC), sortColumnEnum.getColumnName());
        });

        return wrapper;
    }

}
