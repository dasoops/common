package com.dasoops.common.util.dbcolumn

import com.dasoops.common.entity.enums.database.IDbColumnEnum
import com.dasoops.common.exception.DbColumnException
import java.util.*

/**
 * 数据库字段工具
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/18
 */
object DbColumnUtil {
    /**
     * 获取所有可能值
     *
     * @param clazz clazz
     * @return [List]<[Integer]>
     */
    fun getAllProbableValue(clazz: Class<out IDbColumnEnum>): List<Int> {
        if (!clazz.isEnum) {
            throw DbColumnException.NOT_ENUM.get()
        }

        val enumConstantArray = clazz.enumConstants
        return Arrays.stream(enumConstantArray).map { obj: IDbColumnEnum -> obj.dbValue }.toList()
    }

    /**
     * 获取
     */
    fun <T : IDbColumnEnum> getBy(clazz: Class<out T>, value: Int): T? {
        if (!clazz.isEnum) {
            throw DbColumnException.NOT_ENUM.get()
        }

        return Arrays.stream(clazz.enumConstants).filter { it.dbValue == value }.findFirst().orElse(null)
    }

}