package com.dasoops.common.code.converter

import com.dasoops.common.code.CodeFileUtil
import com.dasoops.common.code.entity.Entity
import com.dasoops.common.code.entity.OpenApiSchema
import io.swagger.v3.oas.models.OpenAPI

/**
 * 实体类代码文件构建器
 * @author DasoopsNicole@Gmail.com
 * @date 2023/04/08
 * @see [EntityCodeFileBuilder]
 */
class EntityListBuilder(
    val source: OpenAPI,
    val entityFileBasePath: String,
) {
    companion object {
        const val ENTITY_ID_BEGIN = 0
    }

    fun build() = source.components.schemas.map {
        OpenApiScheme2EntityConverter.convert(OpenApiSchema(it))
    }.run {
        setEntityId(this)
        //实体类名称->id
        resloveEntityOriginalImportAndSetFinalImport(this)
        resloveEntityOriginalRefAndSetFinalRef(this)
        resloveFilePathAndNameAndSetImportPath(this)
        this
    }.ifEmpty { null }

    /**
     * 设置实体唯一标识
     * @param [entityList] 实体集合
     */
    private fun setEntityId(entityList: List<Entity>) {
        entityList.forEachIndexed { index, entity -> entity.id = ENTITY_ID_BEGIN + index }
    }

    /**
     * 解析实体原始引用并设置最终引用
     * @param [entityList] 实体集合
     */
    private fun resloveEntityOriginalRefAndSetFinalRef(entityList: List<Entity>) {
        val entityNameOtoEntityMap = entityList.associateBy { it.name }
        entityList.forEach { entity ->
            entity.propertyList?.forEach {
                it.ref = entityNameOtoEntityMap[it.originalRef]
            }
        }
    }

    /**
     * 解析实体原始import集合并设置最终可使用的导入集合
     * @param [entityList] 实体集合
     */
    private fun resloveEntityOriginalImportAndSetFinalImport(entityList: List<Entity>) {
        val entityNameOtoEntityMap = entityList.associate {
            it.name to it
        }
        entityList.forEach { entity ->
            entity.originalImportList?.run {
                entity.importList = this.mapNotNull { entityNameOtoEntityMap[it] }.toSet()
            }
        }
    }

    /**
     * 解析文件路径和名字和设置导入路径
     * @param [entityList] 实体集合
     */
    private fun resloveFilePathAndNameAndSetImportPath(entityList: List<Entity>) =
        entityList.forEach {
            it.importPath = CodeFileUtil.importPath(it.group, entityFileBasePath)
        }
}
