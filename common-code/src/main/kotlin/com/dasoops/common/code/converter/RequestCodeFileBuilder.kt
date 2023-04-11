package com.dasoops.common.code.converter

import com.dasoops.common.code.CodeFileUtil
import com.dasoops.common.code.entity.Entity
import com.dasoops.common.code.entity.OpenApiPath
import com.dasoops.common.code.entity.Request
import com.dasoops.common.code.entity.RequestCodeFile
import io.swagger.v3.oas.models.OpenAPI

/**
 * 请求代码文件实体类构建器
 * @author DasoopsNicole@Gmail.com
 * @date 2023/04/08
 * @see [RequestCodeFileBuilder]
 */
class RequestCodeFileBuilder(
    val source: OpenAPI,
    val entityList: List<Entity>?,
    val requestFileBasePath: String,
    val excludeRequestPrefix: String?
) {
    companion object {
        const val REQUEST_ID_BEGIN = 1000
    }

    fun build() = source.paths.map {
        OpenApiPath2RequestConverter.convert(OpenApiPath(it))
    }.flatten().run {
        setRequestId(this)
        setParamEntity(this, entityList)
        setImportList(this)
        setImportPath(this)
        groupRequest2File(this)
    }.ifEmpty { null }

    private fun setImportList(requests: List<Request>) {
        requests.forEach { request ->
            request.importList = mutableSetOf<Entity>().also { resultSet ->
                request.paramList?.mapNotNull {
                    it.entity
                }?.run { resultSet.addAll(this) }
                request.result?.ref?.run { resultSet.add(this) }
            }
        }
    }

    private fun setImportPath(requests: List<Request>) {
        requests.forEach {
            it.importPath = CodeFileUtil.importPath(it.group, requestFileBasePath)
        }
    }


    /**
     * 设置请求唯一标识
     * @param [requestList] 请求集合
     */
    private fun setRequestId(requestList: List<Request>) {
        requestList.forEachIndexed { index, request ->
            request.id = REQUEST_ID_BEGIN + index
        }
    }


    /**
     * 解析请求原始import集合并设置最终可使用的导入集合
     * @param [requestList] 请求集合
     */
    private fun setParamEntity(requestList: List<Request>, entityList: List<Entity>?) {
        val entityNameOtoEntityMap = entityList?.associate { it.name to it }
        requestList.forEach { request ->
            request.paramList?.map {
                it.entityType = it.originalProperty.type
                it.entity = entityNameOtoEntityMap?.get(it.originalProperty.originalRef)
            }
        }
    }

    /**
     * 分组构建请求文件
     * @param [requestList] 请求集合
     * @return [List<RequestFile>]
     */
    private fun groupRequest2File(requestList: List<Request>): List<RequestCodeFile> {
        return requestList.groupBy { it.group }.map { entry ->
            val (group, groupRequestList) = entry
            RequestCodeFile(
                filePath = CodeFileUtil.filePath(group, requestFileBasePath),
                fileName = CodeFileUtil.fileName(group),
                requestList = groupRequestList,
                importList = importList(groupRequestList),
            )
        }
    }

    private fun importList(requestList: List<Request>): List<Entity> {
        return requestList.flatMap {
            val resultList = it.importList?.toMutableList() ?: mutableListOf()
            it.result?.ref?.run { resultList.add(this) }
            resultList
        }.distinct()
    }
}
