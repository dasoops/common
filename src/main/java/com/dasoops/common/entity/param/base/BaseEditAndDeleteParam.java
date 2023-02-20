package com.dasoops.common.entity.param.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dasoops.common.entity.dbo.base.BaseDo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @title BaseEditParam
 * @classPath com.dasoops.common.entity.param.base.BaseEditParam
 * @author DasoopsNicole@Gmail.com
 * @date 2023/01/12
 * @version 1.0.0
 * @description 编辑param基类
 * @see BaseFastBuildParam
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class BaseEditAndDeleteParam<T extends BaseDo> extends BaseFastBuildParam<T> {

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id", notes = "主键id", example = "1", required = true)
    private Long rowId;

    @Override
    public QueryWrapper<T> buildQueryWrapper() {
        return super.buildQueryWrapper().eq("rowId", rowId);
    }
}
