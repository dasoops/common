package com.dasoops.common.util.point

import cn.hutool.core.util.NumberUtil
import com.dasoops.common.entity.enums.database.DbBooleanEnum
import com.dasoops.common.entity.enums.database.IDbColumnEnum

/**
 * 点解析器
 * @title: PointReslover
 * @classPath com.herdsman.wgts.util.PointReslover
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/24
 * @version 1.0.0
 * @see [ValueReader]
 */
open class ValueReader(val iterator: Iterator<String>) {

    private var dotIterator: Iterator<Char>? = null

    /**
     * skip
     * @param [count] skip count
     * @return [ValueReader] this
     */
    fun skip(count: Int): ValueReader {
        for (i in 0..count) {
            next()
        }
        return this
    }

    /**
     * skip dto
     * @param [count] skip count
     * @return [ValueReader] this
     */
    fun skipDot(count: Int): ValueReader {
        for (i in 0..count) {
            nextDot()
        }
        return this
    }

    /**
     * 声明开始解析一个点
     * @return [ValueReader] this
     */
    fun dot(): ValueReader {
        dotIterator = NumberUtil.getBinaryStr(this.int()).toCharArray().iterator()
        return this
    }

    /**
     * 清除点迭代器
     * @return [ValueReader] this
     */
    fun dotClear(): ValueReader {
        dotIterator = null
        return this
    }

    /**
     * next value
     * @return [String] string value
     */
    fun next(): String {
        return next()
    }

    /**
     * get string
     * @return [String] string value
     */
    fun str(): String {
        return next()
    }

    /**
     * convert to int
     * @return [Int] int value
     */
    fun int(): Int {
        return Parser.int(next())
    }

    /**
     * convert 2 value to int
     * @return [Int] int value
     */
    fun int2(): Int {
        return Parser.int(next(), next())
    }

    /**
     * convert value to float
     * @return [Float] float value
     */
    fun float(): Float {
        return Parser.float(next())
    }

    /**
     * convert 2 value to float
     * @return [Float] float value
     */
    fun float2(): Float {
        return Parser.float(next(), next())
    }

    /**
     * convert value to boolean list
     * @return [List<Boolean>] boolean value List
     */
    fun bool16(): List<Boolean> {
        return Parser.bool16(next())
    }

    /**
     * next dot value
     * @return [Char] char value
     */
    fun nextDot(): Char {
        return dotIterator!!.next()
    }

    /**
     * convert dot value to boolean value
     * @return [List<Boolean>] boolean value
     */
    fun bool(): Boolean {
        dotIterator ?: dot()
        return Parser.bool(nextDot())
    }

    /**
     * convert dot value to dataBase boolean enum
     * @return [DbBooleanEnum] dataBase boolean enum value
     */
    fun dbBool(): DbBooleanEnum {
        dotIterator ?: dot()
        return Parser.dbBool(nextDot())
    }

    /**
     * convert value to enum list
     * @return [List<E>] enum list
     */
    inline fun <reified E : IDbColumnEnum> enum(): List<E>? {
        return Parser.enum(next())
    }
}