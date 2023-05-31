package com.dasoops.common.json.core

import cn.hutool.core.util.HexUtil
import com.dasoops.common.core.util.point.Parser
import com.dasoops.common.core.util.point.PointReader
import com.dasoops.common.core.util.point.ValueReader
import com.dasoops.common.json.core.dataenum.BooleanEnum
import com.dasoops.common.json.core.dataenum.DataEnum
import com.dasoops.common.json.core.dataenum.IntDataEnum


fun Parser.dbBool(char: Char): BooleanEnum {
    return if (this.bool(char)) {
        BooleanEnum.TRUE
    } else {
        BooleanEnum.FALSE
    }
}

fun Parser.dbBool(int: Int): BooleanEnum {
    return if (this.bool(int)) {
        BooleanEnum.TRUE
    } else {
        BooleanEnum.FALSE
    }
}

inline fun <reified E : IntDataEnum> Parser.enum(str: String): List<E>? {
    val enumConstantArray = E::class.java.enumConstants
    return HexUtil.toHex(int(str))
        .filter { it.code == 49 }
        .map { value -> enumConstantArray.first { it.data == value.digitToInt() } }
        .ifEmpty { null }
}



inline fun <reified E : IntDataEnum> PointReader.enum(index: Int): List<E>? {
    return Parser.enum(get(index))
}

/**
 * convert dot value to dataBase boolean enum
 * @return [BooleanEnum] dataBase boolean enum value
 */
fun ValueReader.dbBool(): BooleanEnum {
    dotIterator ?: dot()
    return Parser.dbBool(nextDot())
}

/**
 * convert value to enum list
 * @return [List<E>] enum list
 */
inline fun <reified E : IntDataEnum> ValueReader.enum(): List<E>? {
    return Parser.enum(next())
}