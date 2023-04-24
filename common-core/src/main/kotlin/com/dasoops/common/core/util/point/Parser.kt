package com.dasoops.common.core.util.point

import cn.hutool.core.util.HexUtil
import com.dasoops.common.core.entity.dataenum.BooleanEnum
import com.dasoops.common.core.entity.dataenum.DataEnum

/**
 * str解析器
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/25
 */
object Parser {
    fun int(str: String): Int {
        return str.toInt()
    }

    fun int(str: String, str2: String): Int {
        return str.toInt() + str2.toInt() * 65535
    }

    fun float(str: String): Float {
        return str.toFloat()
    }

    fun float(str: String, str2: String): Float {
        return java.lang.Float.intBitsToFloat((str + str2).toInt(32))
    }

    fun bool16(str: String): List<Boolean> {
        return HexUtil.toHex(int(str)).map { it.code == 49 }
    }

    fun bool(str: String, index: Int): Boolean {
        return HexUtil.toHex(int(str))[index].code == 49
    }

    fun bool(char: Char): Boolean {
        return char.code != 48
    }

    fun bool(int: Int): Boolean {
        return int != 0
    }

    fun dbBool(char: Char): BooleanEnum {
        return if (this.bool(char)) {
            BooleanEnum.TRUE
        } else {
            BooleanEnum.FALSE
        }
    }

    fun dbBool(int: Int): BooleanEnum {
        return if (this.bool(int)) {
            BooleanEnum.TRUE
        } else {
            BooleanEnum.FALSE
        }
    }

    inline fun <reified E : DataEnum> enum(str: String): List<E>? {
        val enumConstantArray = E::class.java.enumConstants
        return HexUtil.toHex(int(str))
            .filter { it.code == 49 }
            .map { value -> enumConstantArray.first { it.data == value.digitToInt() } }
            .ifEmpty { null }
    }
}