package com.dasoops.common.code.entity

import io.swagger.v3.oas.annotations.media.Schema

/**
 * 获取代码参数
 * @author DasoopsNicole@Gmail.com
 * @date 2023/04/06
 * @see [CodeParam]
 */
@Schema(title = "code", description = "获取代码参数")
data class CodeParam(
    /**
     * 代码类型
     */
    @field:Schema(description = "代码类型", required = false)
    var codeType: CodeType = CodeType.TS,

    /**
     * 作者名称
     */
    @field:Schema(description = "作者名称", required = false)
    var another: String = "root",

    /**
     * 排除请求基本路径
     */
    @field:Schema(description = "排除请求基本路径", required = false)
    var excludeRequestPrefix: String? = null,

    /**
     * 实体类文件路径
     */
    @field:Schema(description = "实体类文件路径", required = false)
    var entityFileBasePath: String = "/entity/",

    /**
     * 请求文件路径
     */
    @field:Schema(description = "请求文件路径", required = false)
    var requestFileBasePath: String = "/request/",

    /**
     * axios请求实体类名称
     */
    @field:Schema(description = "axios请求实体类名称", required = false)
    var axiosModelName: String = "axios",

    /**
     * 请求文件基础引入
     */
    @field:Schema(description = "axios请求实体类名称", required = false)
    var requestFileBaseImport: String? = null,

    /**
     * 实体类文件基础引入
     */
    @field:Schema(description = "axios请求实体类名称", required = false)
    var entityFileBaseImport: String? = null,
)