package com.dasoops.common.dao

import com.dasoops.common.entity.dbo.base.BaseMongoDo
import org.bson.types.ObjectId

/**
 * mongoMapper基类
 * @title: BaseMongoMapper
 * @classPath com.herdsman.wgts.dao.BaseMongoMapper
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/27
 * @version 1.0.0
 * @see [IMongoMapper]
 */
interface IMongoMapper<T : BaseMongoDo> {
    companion object {
        val ID = "_id"
    }
}