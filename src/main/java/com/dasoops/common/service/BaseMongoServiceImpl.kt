package com.dasoops.common.service

import com.dasoops.common.dao.MongoMapper
import com.dasoops.common.dao.SimpleMongoMapperImpl
import com.dasoops.common.entity.dbo.base.BaseMongoDo
import org.springframework.beans.factory.annotation.Autowired

/**
 * mongoService实现类基类
 * @title: BaseMongoServiceImpl
 * @classPath com.dasoops.common.service.BaseMongoServiceImpl
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/27
 * @version 1.0.0
 * @see [BaseMongoServiceImpl]
 */
abstract class BaseMongoServiceImpl<T : BaseMongoDo> : IService {
    /**
     * 简单sql
     * 泛型注入只能AutoWired
     */
    @Autowired
    protected lateinit var simpleSql: SimpleMongoMapperImpl<T>

}