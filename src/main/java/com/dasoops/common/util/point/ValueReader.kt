package com.dasoops.common.util.point

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
            iterator.next()
        }
        return this
    }

    /**
     * int
     * @return [Int] int
     */
    fun int(): Int {
        return Parser.int(iterator.next())
    }

    /**
     * int
     * @return [Int]
     */
    fun int2(): Int {
        return Parser.int(iterator.next(), iterator.next())
    }

    /**
     * float
     * @return [Float] float
     */
    fun float(): Float {
        return Parser.float(iterator.next())
    }

    /**
     * float
     * @return [Float] float
     */
    fun float2(): Float {
        return Parser.float(iterator.next(), iterator.next())
    }

    /**
     * bool
     * @return [List<Boolean>] boolList
     */
    fun bool16(): List<Boolean> {
        return Parser.bool16(iterator.next())
    }

    /**
     * bool
     * @return [List<Boolean>] boolList
     */
    fun bool(): Boolean {
        dotIterator ?: {
            dotIterator = iterator.next().toCharArray().iterator()
        }
        return Parser.bool(dotIterator!!.next())
    }

    /**
     * 点迭代器置空
     * @return [ValueReader] this
     */
    fun dotClear(): ValueReader {
        dotIterator = null
        return this
    }

    /**
     * 报警
     * @return [List<E>] enumList
     */
    inline fun <reified E : Enum<*>> enum(): List<E> {
        return Parser.enum(iterator.next())
    }
}