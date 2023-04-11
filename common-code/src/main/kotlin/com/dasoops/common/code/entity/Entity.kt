package com.dasoops.common.code.entity

import com.dasoops.common.code.CodeException
import io.swagger.v3.oas.models.media.Schema
import kotlin.properties.Delegates

/**
 * 实体
 * @author DasoopsNicole@Gmail.com
 * @date 2023/04/07
 * @see [Entity]
 */
data class Entity(
    /**
     * 分组
     */
    val group: String,

    /**
     * 类型
     */
    val type: EntityType,

    /**
     * 名称
     */
    val name: String,

    /**
     * 属性集合
     */
    val propertyList: List<Property>?,

    /**
     * 描述
     */
    val description: String?,

    /**
     * 原始导入集合
     */
    val originalImportList: List<String>?,
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
    var importList: Set<Entity>? = null
}

/**
 * 属性
 * @author DasoopsNicole@Gmail.com
 * @date 2023/04/07
 * @see [Property]
 */
data class Property(
    /**
     * 名称
     */
    val name: String?,

    /**
     * 属性类型
     */
    val type: PropertyType,

    /**
     * 原始类型引用
     */
    val originalRef: String?,

    /**
     * 类型引用
     */
    var ref: Entity?,

    /**
     * 子属性
     */
    val item: Property?,

    /**
     * 是否必须
     */
    val require: Boolean,

    /**
     * 描述
     */
    val description: String?,
) {
    companion object {
        private const val SCHEMA_PREFIX = "#/components/schemas/"

        fun from(property: Map.Entry<String, Schema<*>>, requireList: List<String>?): Property {
            return from(property.value, property.key, requireList)
        }

        private fun from(schema: Schema<*>, name: String?, requireList: List<String>?): Property {
            return Property(
                name = name,
                type = PropertyType.of(schema.type, schema.format),
                originalRef = schema.`$ref`?.removePrefix(SCHEMA_PREFIX),
                ref = null,
                require = requireList?.contains(name) ?: false,
                description = schema.description,
                item = innerFrom(schema.items, schema.required)
            )
        }

        fun from(schema: Schema<*>, require: Boolean): Property {
            return Property(
                name = null,
                type = PropertyType.of(schema.type, schema.format),
                originalRef = schema.`$ref`?.removePrefix(SCHEMA_PREFIX),
                ref = null,
                require = require,
                description = schema.description,
                item = innerFrom(schema.items, schema.required),
            )
        }

        private fun innerFrom(schema: Schema<*>?, requireList: List<String>?): Property? {
            schema ?: return null
            return Property(
                name = null,
                type = PropertyType.of(schema.type, schema.format),
                originalRef = schema.`$ref`,
                ref = null,
                description = schema.description,
                item = innerFrom(schema.items, schema.required),
                require = requireList?.contains(schema.name) ?: false,
            )
        }
    }
}

/**
 * 实体类型
 * @author DasoopsNicole@Gmail.com
 * @date 2023/04/07
 * @see [EntityType]
 */
enum class EntityType {
    INT, STRING, ARRAY, OBJECT;

    companion object {
        fun of(value: String): EntityType {
            return when (value) {
                "integer" -> INT
                "string" -> STRING
                "array" -> ARRAY
                "object" -> OBJECT
                else -> throw CodeException.UNDEFINED_ENTITY_TYPE.get()
            }
        }
    }
}

/**
 * 属性类型
 * @author DasoopsNicole@Gmail.com
 * @date 2023/04/07
 * @see [PropertyType]
 */
enum class PropertyType {
    DATE, STRING, INT, NUMBER, BOOLEAN, ARRAY, OBJECT;

    companion object {
        fun of(type: String?, format: String?): PropertyType {
            return when (format) {
                "int32", "int64" -> INT
                "float" -> NUMBER
                "binary" -> STRING
                "array" -> ARRAY
                "date-time" -> DATE
                else -> when (type) {
                    "boolean" -> BOOLEAN
                    "string" -> STRING
                    else -> OBJECT
                }
            }
        }
    }
}
