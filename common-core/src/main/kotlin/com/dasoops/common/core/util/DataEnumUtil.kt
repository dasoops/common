package com.dasoops.common.core.util

import com.dasoops.common.core.entity.dataenum.DataEnum
import com.dasoops.common.exception.DataEnumException
import java.util.*

/**
 * 数据库字段工具
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/18
 */
object DataEnumUtil {
    /**
     * 获取所有可能值
     *
     * @param clazz clazz
     * @return [List]<[Integer]>
     */
    fun getAllProbableValue(clazz: Class<out DataEnum>): List<Int> {
        if (!clazz.isEnum) {
            throw DataEnumException.NOT_ENUM.get()
        }

        val enumConstantArray = clazz.enumConstants
        return Arrays.stream(enumConstantArray).map { it.data }.toList()
    }

    /**
     * 获取
     */
    fun <T : DataEnum> getBy(clazz: Class<out T>, value: Int): T? {
        if (!clazz.isEnum) {
            throw DataEnumException.NOT_ENUM.get()
        }

        return Arrays.stream(clazz.enumConstants).filter { it.data == value }.findFirst().orElse(null)
    }
}