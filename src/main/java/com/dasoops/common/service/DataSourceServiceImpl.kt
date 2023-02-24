package com.dasoops.common.service

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.dasoops.common.cache.BaseDataSourceCache
import com.dasoops.common.entity.dbo.base.BaseDo

/**
 * 数据源service实现类
 * @title: DataSourceServiceImpl
 * @classPath com.dasoops.common.service.DataSourceServiceImpl
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/24
 * @version 1.0.0
 * @see [DataSourceServiceImpl]
 */
abstract class DataSourceServiceImpl<M : BaseMapper<T>, T : BaseDo> : IDataSourceService<T> {
    /**
     * 缓存
     */
    //@Resource
    lateinit var cache: BaseDataSourceCache<T, *>

    /**
     * 简单sql
     */
    //@Resource
    lateinit var simpleSql: ServiceImpl<M, T>
}