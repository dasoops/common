package com.dasoops.common.util.point

import com.dasoops.common.entity.dataenum.DataEnum

/**
 * 点解析器
 * @title: PointReslover
 * @classPath com.dasoops.common.util.PointReslover
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/24
 * @version 1.0.0
 * @see [PointReader]
 */
open class PointReader private constructor(map: Map<Int, String>) : LinkedHashMap<Int, String>(map) {
    /**
     * 重写,不为空
     */
    override fun get(key: Int): String {
        return get(key)
    }

    /**
     * 剪切
     * @param [start] 开始
     * @param [length] 长度
     * @return [ValueReader]
     */
    fun cut(start: Int, length: Int): ValueReader {
        val entryList = this.entries.filter { it.key >= start }
        if (entryList.size < length) {
            var flag = start
            val arrayList = ArrayList(entryList)
            for ((index, _) in entryList.withIndex()) {
                if (index != flag) {
                    arrayList.add(flag, SimpleEntry(flag, "0"))
                }
                flag++
            }
        }

        return ValueReader(entryList.map { it.value }.iterator())
    }

    /**
     * int
     * @param [index] 索引
     * @return [Int] int
     */
    fun int(index: Int): Int {
        return Parser.int(get(index))
    }

    /**
     * int
     * @param [index] 索引1
     * @param [index2] 索引2
     * @return [Int]
     */
    fun int(index: Int, index2: Int): Int {
        return Parser.int(get(index), get(index2))
    }

    /**
     * float
     * @param [index] 索引
     * @return [Float] float
     */
    fun float(index: Int): Float {
        return Parser.float(get(index))
    }

    /**
     * float
     * @param [index] 索引
     * @return [Float] float
     */
    fun float(index: Int, index2: Int): Float {
        return Parser.float(get(index), get(index2))
    }

    /**
     * bool
     * @param [index] 索引
     * @return [List<Boolean>] boolList
     */
    fun bool16(index: Int): List<Boolean> {
        return Parser.bool16(get(index))
    }

    /**
     * bool
     * @param [index] 索引
     * @return [List<Boolean>] boolList
     */
    fun bool(index: Int, innerIndex: Int): Boolean {
        return Parser.bool(get(index), innerIndex)
    }

    /**
     * 报警
     * @param [index] 索引
     * @return [List<E>] enumList
     */
    inline fun <reified E : DataEnum> enum(index: Int): List<E>? {
        return Parser.enum(get(index))
    }


    companion object {
        /**
         * 构建
         * @param [indexList] 索引集合
         * @param [valueList] 值集合
         * @param [offset] 偏移量
         * @return [PointReader]
         */
        fun from(indexList: Collection<Int>, valueList: Collection<String>, offset: Int): PointReader {
            /* no check 效率
            if (inputList.size != valueList.size) {
                throw PointResloverExceptionEnum.SIZE_NOT_MATCH.exception
            }

            if (inputList.stream().anyMatch { inputList.contains(it) }) {
                throw PointResloverExceptionEnum.REPEAT_INPUT.exception
            }
            */

            /*
                有偏移量 先计算
                然后调用zip直接转
             */
            return PointReader(if (offset != 0) {
                indexList.map { it + offset }
            } else {
                indexList
            }.run {
                // 转map
                this.zip(valueList).toMap()
            })
        }
    }
}