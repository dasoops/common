package com.dasoops.common.db.util

import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.kotlin.KtQueryChainWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.dasoops.common.core.entity.param.PageParam
import com.dasoops.common.core.entity.vo.SimplePageVo

fun <T> PageParam.buildPage(): IPage<T> {
    return Page(current.toLong(), size.toLong())
}

fun <T : Any> SimplePageVo.Companion.`for`(page: IPage<T>) = SimplePageVo(page.total.toInt(), page.records)

fun <T : Any> KtQueryChainWrapper<T>.page(page: PageParam): IPage<T> {
    return this.page(page.buildPage())
}