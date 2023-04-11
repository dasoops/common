package com.dasoops.common.code.ts.style

import cn.hutool.core.text.StrPool
import com.dasoops.common.code.entity.Entity
import com.dasoops.common.code.entity.EntityType
import com.dasoops.common.code.entity.Property
import com.dasoops.common.code.entity.PropertyType

object EntityDefined {
    fun `for`(entity: Entity) = """
        |${Anno.`for`(entity.description)}
        |${entityBody(entity)}
    """.trimMargin() + StrPool.CRLF

    private fun entityBody(entity: Entity) =
        """
            |export interface ${entity.name}{
            |${propertyList(entity.propertyList)}
            |}
        """.trimMargin()

    private fun propertyList(propertyList: List<Property>?): String {
        propertyList ?: return StrPool.CRLF
        return propertyList.joinToString(StrPool.CRLF + StrPool.CRLF) {
            """
               |${Anno.`for`(it.description, "    ")}
               |    ${property(it)}
            """.trimMargin()
        }
    }

    private fun property(property: Property): String {
        return "${property.name}: ${entityType(property.type, property.ref, true)};"
    }

    private fun entityType(propertyType: PropertyType, entity: Entity?, require: Boolean): String {
        return when (propertyType) {
            PropertyType.DATE -> "Date"
            PropertyType.STRING -> "string"
            PropertyType.INT, PropertyType.NUMBER -> "number"
            PropertyType.BOOLEAN -> "boolean"
            PropertyType.ARRAY -> entityType(entity, require) + "[]"
            PropertyType.OBJECT -> entityType(entity, require)
        } + if (!require) {
            "?"
        } else {
            ""
        }
    }

    private fun entityType(entity: Entity?, require: Boolean): String {
        entity ?: return "any"
        return when (entity.type) {
            EntityType.INT -> "number"
            EntityType.STRING -> "string"
            EntityType.ARRAY -> entity.name + "[]"
            EntityType.OBJECT -> entity.name
        } + if (!require) {
            "?"
        } else {
            ""
        }
    }

    //export interface ImageData {
    //    rowId: number;
    //    keyword: string;
    //    filePath: string;
    //    groupId: number;
    //    authorId: number;
    //    author: string;
    //    authorName: string;
    //    updateTime: string;
    //    fileName: string;
    //}
}