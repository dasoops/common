package com.dasoops.common.code.converter

import com.dasoops.common.code.entity.Entity
import com.dasoops.common.code.entity.EntityType
import com.dasoops.common.code.entity.OpenApiSchema
import com.dasoops.common.code.entity.Property

/**
 * scheme2Entity转换器
 * @author DasoopsNicole@Gmail.com
 * @date 2023/04/07
 * @see [OpenApiScheme2EntityConverter]
 */
object OpenApiScheme2EntityConverter {

    fun convert(source: OpenApiSchema): Entity {
        val (name, scheme) = source
        val propertyList = scheme.properties.map { Property.from(it, scheme.required) }

        return Entity(
            group = scheme.title ?: "default",
            type = EntityType.of(scheme.type),
            name = name,
            propertyList = propertyList.ifEmpty { null },
            description = scheme.description,
            originalImportList = scanImport(propertyList)
        )
    }

    /**
     * 扫描导入集合
     * @param [propertyList] 财产集合
     * @return [MutableList<String>]
     */
    private fun scanImport(propertyList: List<Property>): MutableList<String>? {
        val importList = mutableListOf<String>()
        propertyList.forEach {
            it.originalRef?.run { importList.add(this) }
            scanImportSave2List(importList, it.item)
        }
        return importList.ifEmpty { null }
    }

    private fun scanImportSave2List(importList: MutableList<String>, item: Property?) {
        if (item != null) {
            item.originalRef?.run { importList.add(this) }
            scanImportSave2List(importList, item.item)
        }
    }
}