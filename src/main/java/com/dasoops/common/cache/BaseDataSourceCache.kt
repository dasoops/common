package com.dasoops.common.cache

import com.dasoops.common.entity.dbo.base.BaseDo
import com.dasoops.common.entity.enums.cache.ICacheKeyEnum

/**
 * 有对应数据源的缓存
 * @title: BaseDataSourceCache
 * @classPath com.dasoops.common.cache.BaseDataSourceCache
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/24
 * @version 1.0.0
 * @see [BaseDataSourceCache]
 */
abstract class BaseDataSourceCache<M : BaseDo, E : ICacheKeyEnum> : BaseCache<E>()