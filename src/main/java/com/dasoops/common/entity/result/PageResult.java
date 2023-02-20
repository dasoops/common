package com.dasoops.common.entity.result;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dasoops.common.util.Convert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @title PageResult
 * @classPath cn.qiaoml.bean.vo.result.PageResult
 * @author DasoopsNicole@Gmail.com
 * @date 2022/11/30
 * @version 1.0.0
 * @description 分页数据
 * @see Result
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "分页数据结果集", description = "分页数据结果类集")
public class PageResult<T> extends BaseResult {

    /**
     * 返回数据
     */
    @ApiModelProperty(value = "返回数据", notes = "返回数据", required = false)
    public List<T> data;

    /**
     * 总记录数
     */
    @ApiModelProperty(value = "总记录数", notes = "总记录数", required = true)
    public Integer total;

    /**
     * 成功
     *
     * @param dataList 数据集合
     * @param total 总计
     * @return {@link PageResult}<{@link T}>
     */
    public static <T> PageResult<T> success(List<T> dataList, Integer total) {
        PageResult<T> result = new PageResult<>();
        result.setCode(200);
        result.setMsg("请求成功");
        result.setData(dataList);
        result.setTotal(total);
        return result;
    }

    /**
     * 成功
     *
     * @param page 查询结果page对象
     * @return {@link PageResult}<{@link T}>
     */
    public static <T> PageResult<T> success(IPage<T> page) {
        PageResult<T> result = new PageResult<>();
        result.setCode(200);
        result.setMsg("请求成功");
        if (page == null) {
            result.setTotal(0);
            return result;
        }
        result.setData(page.getRecords());
        result.setTotal((int) page.getTotal());
        return result;
    }

    public static <T, T2> PageResult<T2> success(IPage<T> page, Class<T2> clazz) {
        return PageResult.success(Convert.to(page.getRecords(), clazz), (int) page.getTotal());
    }
}
