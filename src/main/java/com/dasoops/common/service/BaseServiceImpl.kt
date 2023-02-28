package com.dasoops.common.service

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.dasoops.common.entity.dbo.base.BaseDo
import org.springframework.beans.factory.annotation.Autowired

/**
 * 数据源service实现类
 * @title: DataSourceServiceImpl
 * @classPath com.dasoops.common.service.DataSourceServiceImpl
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/24
 * @version 1.0.0
 * @see [BaseServiceImpl]
 */
abstract class BaseServiceImpl<T : BaseDo, M : BaseMapper<T>> : IService {
    /**
     * 简单sql
     * 泛型注入只能AutoWired
     */
    @Autowired
    protected lateinit var simpleSql: ServiceImpl<M, T>

}