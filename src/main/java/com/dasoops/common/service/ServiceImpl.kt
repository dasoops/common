package com.dasoops.common.service

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.dasoops.common.entity.dbo.base.BaseDo

abstract class ServiceImpl<M : BaseMapper<T>, T : BaseDo> : IService {

    override fun init() {
        //Nothing
    }

}