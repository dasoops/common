package com.dasoops.common.entity.enums.database;

import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * @author DasoopsNicole@Gmail.com
 * @version 1.0.0
 * @title IDbColumnEnum
 * @classPath com.dasoops.common.entity.enums.database.IDbColumnEnum
 * @date 2023/02/18
 * @description 数据库字段枚举顶层接口
 * @see IEnum
 */
public interface IDbColumnEnum extends IEnum<Integer> {
    /**
     * 获取数据库值
     *
     * @return [Integer]
     */
    int getDbValue();


    /**
     * 重写getValue调用getDbValue,因为这个表达性更强(我觉得好看)
     *
     * @return [Int]
     */
    @Override
    default Integer getValue() {
        return getDbValue();
    }
}
