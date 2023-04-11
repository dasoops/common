package com.dasoops.common.code.entity

/**
 * 实体类代码文件实体类
 * @author DasoopsNicole@Gmail.com
 * @date 2023/04/08
 * @see [EntityCodeFile]
 */
data class EntityCodeFile(

    /**
     * 文件路径
     */
    val filePath: String,

    /**
     * 文件名
     */
    val fileName: String,

    /**
     * 实体类集合
     */
    val entityList: List<Entity>,

    /**
     * 引入集合
     */
    var importList: List<Entity>,
)