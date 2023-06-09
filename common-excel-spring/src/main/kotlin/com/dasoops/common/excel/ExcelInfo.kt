package com.dasoops.common.excel

/**
 * @Title: ExportDto
 * @ClassPath com.dasoops.common.core.util.entity.ExportDto
 * @Author DasoopsNicole@Gmail.com
 * @Date 2023/02/01
 * @Version 1.0.0
 * @Description: 导出dto
 * @see [ExcelInfo]
 */
data class ExcelInfo<T>(

    /**
     * 数据集合
     */
    val dataList: List<T>,

    /**
     * 数据类对象
     */
    val dataClass: Class<out T>,

    /**
     * sheet编号
     */
    val sheetNo: Int = 0,

    /**
     * sheet名称
     */
    val sheetName: String = "sheet0",

    ) {

    companion object {
        @JvmSynthetic
        inline fun <reified T> ktBuild(dataList: List<T>): ExcelInfo<T> {
            return ExcelInfo(dataList, T::class.java)
        }

        @JvmSynthetic
        inline fun <reified T> ktBuild(dataList: List<T>, sheetNo: Int, sheetName: String): ExcelInfo<T> {
            return ExcelInfo(dataList, T::class.java, sheetNo, sheetName)
        }

        fun <T> build(dataList: List<T>): ExcelInfo<T> {
            val java: Class<out T> = dataList.first()!!::class.java
            return ExcelInfo(dataList, java)
        }
    }
}