package com.dasoops.common.util

import com.baomidou.mybatisplus.core.toolkit.LambdaUtils
import com.baomidou.mybatisplus.core.toolkit.StringPool
import com.baomidou.mybatisplus.extension.kotlin.AbstractKtWrapper
import com.dasoops.common.entity.dbo.base.BaseDo
import com.dasoops.common.exception.UnexpectedException
import com.google.common.base.CaseFormat
import kotlin.reflect.KProperty

class SqlSelectBuilder {
    companion object {
        inline fun <reified T : BaseDo> mpBuild(vararg funcArray: KProperty<*>): String {
            return Util.funcToString(T::class.java, *funcArray)
        }

        fun buildOriginal(vararg funcArray: KProperty<*>): String {
            return funcArray.joinToString(StringPool.COMMA) { it.name }
        }

        fun build(vararg funcArray: KProperty<*>): String {
            return funcArray.joinToString(StringPool.COMMA) { CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, it.name) }
        }

        /**
         * 调用mp写的
         * @author DasoopsNicole@Gmail.com
         * @date 2023/03/01
         */
        object Util : AbstractKtWrapper<BaseDo, Util>() {
            override fun instance(): Util {
                throw UnexpectedException
            }

            fun <T : BaseDo> funcToString(clazz: Class<T>, vararg columns: KProperty<*>): String {
                columnMap = LambdaUtils.getColumnMap(clazz)
                return super.columnsToString(*columns)
            }
        }
    }
}