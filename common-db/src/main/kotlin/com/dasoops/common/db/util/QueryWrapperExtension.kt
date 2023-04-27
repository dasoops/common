package com.dasoops.common.db.util

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper
import com.baomidou.mybatisplus.extension.conditions.AbstractChainWrapper

fun <T, R, Children : AbstractChainWrapper<T, R, Children, Param>, Param : AbstractWrapper<T, R, Param>> AbstractChainWrapper<T, R, Children, Param>.limit(count: Int): Children {
    return last("LIMIT $count")
}