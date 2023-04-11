package com.dasoops.common.code.entity

import com.dasoops.common.code.CodeException
import kotlin.properties.Delegates

/**
 * 请求实体类
 * @author DasoopsNicole@Gmail.com
 * @date 2023/04/08
 * @see [Request]
 */
data class Request(

    /**
     * 分组
     */
    val group: String,

    /**
     * 类型
     */
    val type: RequestType,

    /**
     * 名称
     */
    val path: String,

    /**
     * 原始参数集合
     */
    var paramList: Collection<Param>?,

    /**
     * 返回值
     */
    val result: Property?,

    /**
     * 描述
     */
    val description: String?,

    /**
     * 名称
     */
    val name: String?,
) {
    /**
     * 唯一标识符
     */
    var id by Delegates.notNull<Int>()

    /**
     * 引入路径
     */
    lateinit var importPath: String

    /**
     * 导入集合
     */
    var importList: Collection<Entity>? = null
}

data class Param(
    val name: String,
    val `in`: In,
    val require: Boolean,
    val originalProperty: Property
) {
    lateinit var entityType: PropertyType
    var entity: Entity? = null
}

enum class In {
    QUERY, PATH, BODY;
}

/**
 * 实体类型
 * @author DasoopsNicole@Gmail.com
 * @date 2023/04/07
 * @see [EntityType]
 */
enum class RequestType {
    GET, POST, PUT, DELETE;

    companion object {
        fun of(value: String): RequestType {
            return when (value) {
                "get" -> GET
                "post" -> POST
                "put" -> PUT
                "delete" -> DELETE
                else -> throw CodeException.UNDEFINED_ENTITY_TYPE.get()
            }
        }
    }
}

data class RequestCodeFile(
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
    val requestList: List<Request>,

    /**
     * 引入集合
     */
    var importList: List<Entity>,
)
