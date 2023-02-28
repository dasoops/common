package com.dasoops.common.service

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.dasoops.common.entity.dbo.base.BaseDo

/**
 * Iservice实现类
 * @title: ServiceImpl
 * @classPath com.dasoops.common.service.ServiceImpl
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/24
 * @version 1.0.0
 * @see [ServiceImpl]
 */
abstract class ServiceImpl<M : BaseMapper<T>, T : BaseDo> : IService {

}