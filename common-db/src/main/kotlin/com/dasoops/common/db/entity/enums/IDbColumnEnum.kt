package com.dasoops.common.entity.enums.database

import com.baomidou.mybatisplus.annotation.IEnum
import com.fasterxml.jackson.annotation.JsonValue

/**
 * @author DasoopsNicole@Gmail.com
 * @version 1.0.0
 * @title IDbColumnEnum
 * @classPath com.dasoops.common.entity.enums.database.IDbColumnEnum
 * @date 2023/02/23
 * @description 数据库列枚举
 * @see IEnum
 */
interface IDbColumnEnum : IEnum<Int> {
    /**
     * 获取数据库值
     *
     * @return [Integer]
     */
    @get:JsonValue
    val dbValue: Int

    /**
     * 重写getValue调用getDbValue,因为这个表达性更强(我觉得好看)
     *
     * @return [Int]
     */
    override fun getValue(): Int {
        return dbValue
    }
}