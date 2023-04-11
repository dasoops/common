package com.dasoops.common.code.entity

/**
 * 代码压缩文件实体类
 * @author DasoopsNicole@Gmail.com
 * @date 2023/04/07
 * @see [CodeFileZip]
 */
data class CodeFileZip(
    /**
     * 代码文件信息
     */
    val info: CodeFileZipInfo,

    /**
     * 实体类文件集合
     */
    val entityFileList: List<EntityCodeFile>?,

    /**
     * 请求文件集合
     */
    val requestFileList: List<RequestCodeFile>?,
)

data class CodeFileZipInfo(

    /**
     * 作者
     */
    val another: String,

    /**
     * axios请求实体类名称
     */
    val axiosModelName: String,

    /**
     * 请求文件基础引入
     */
    val requestFileBaseImport: String?,

    /**
     * 实体类文件基础引入
     */
    val entityFileBaseImport: String?,
)