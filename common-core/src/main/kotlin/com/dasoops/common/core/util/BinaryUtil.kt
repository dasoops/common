package com.dasoops.common.util

/**
 * 二进制工具
 * @author DasoopsNicole@Gmail.com
 * @date 2023/03/20
 * @see [BinaryUtil]
 */
object BinaryUtil {

    //8个的数据为零的数组
    private val ZERRO_16_ARRAY = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

    /**
     * @Title: buildNumber2Binary16Array
     * @Description:(将整型的数据转成16位的二进制int数组)
     * @param number
     * @return
     */
    fun buildNumber2Binary16Array(number: Int): Array<Int> {
        if (number == 0) {
            return ZERRO_16_ARRAY
        }
        val array = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        //得到高位数据
        val heightNumber = number and 0xFF00 shr 8
        //得到低位数据
        val lowerNumber = number and 0xFF

        //高8位 #将数据倒叙
        val heigh8Bit = StringBuilder(Integer.toBinaryString(heightNumber)).reverse().toString()
        //String heigh8Bit = Integer.toBinaryString(reverse(heightNumber));
        //低8位 #将数据倒叙
        val lowe8Bit = StringBuilder(Integer.toBinaryString(lowerNumber)).reverse().toString()
        //String lowe8Bit = Integer.toBinaryString(reverse(lowerNumber));
        if (heigh8Bit != "0") {
            val charArray = heigh8Bit.toCharArray()
            for (i in heigh8Bit.indices) {
                array[i] = charArray[i].code - 48
            }
        }
        if (lowe8Bit != "0") {
            val length = lowe8Bit.length
            val charArray = lowe8Bit.toCharArray()
            for ((index, i) in (8 until 8 + length).withIndex()) {
                array[i] = charArray[index].code - 48
            }
        }
        return array
    }

}