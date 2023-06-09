package com.dasoops.common.core.util.point

import cn.hutool.core.util.HexUtil

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
}