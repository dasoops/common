package com.dasoops.common.entity.param.base

import com.dasoops.common.entity.dbo.base.BaseDo

/**
 * @title BaseEditParam
 * @classPath com.dasoops.common.entity.param.base.BaseEditParam
 * @author DasoopsNicole@Gmail.com
 * @date 2023/01/12
 * @version 1.0.0
 * @description 编辑param基类
 * @see BaseFastBuildParam
 */
abstract class BaseEditParam<T : BaseDo>(rowId: Long) : BaseEditAndDeleteParam<T>(rowId), IBuildDo<T>