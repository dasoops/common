package com.dasoops.common.code.converter

import com.dasoops.common.code.CodeFileUtil
import com.dasoops.common.code.entity.Entity
import com.dasoops.common.code.entity.EntityCodeFile

/**
 * 实体类代码文件构建器
 * @author DasoopsNicole@Gmail.com
 * @date 2023/04/08
 * @see [EntityCodeFileBuilder]
 */
class EntityCodeFileBuilder(
    val entityList: List<Entity>?,
    val entityFileBasePath: String,
) {
    companion object {
        const val ENTITY_ID_BEGIN = 0
    }

    fun build(): List<EntityCodeFile>? {
        entityList ?: return null
        return groupEntity2File(entityList).ifEmpty { null }
    }

    /**
     * 分组构建实体类文件
     * @param [entityList] 实体集合
     * @return [List<EntityCodeFile>]
     */
    private fun groupEntity2File(entityList: List<Entity>): List<EntityCodeFile> {
        return entityList.groupBy { it.group }.map { entry ->
            val (group, groupEntityList) = entry
            EntityCodeFile(
                fileName = CodeFileUtil.fileName(group),
                filePath = CodeFileUtil.filePath(group, entityFileBasePath),
                entityList = groupEntityList,
                importList = importList(groupEntityList)
            )
        }
    }

    private fun importList(entityList: List<Entity>) =
        entityList.flatMap { entity ->
            entity.importList?.map { entity } ?: emptyList()
        }
}
