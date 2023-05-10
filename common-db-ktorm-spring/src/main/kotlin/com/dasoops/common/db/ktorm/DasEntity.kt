package com.dasoops.common.db.ktorm

import cn.hutool.core.date.LocalDateTimeUtil
import cn.hutool.extra.spring.SpringUtil
import com.dasoops.common.core.IDo
import org.ktorm.entity.Entity
import org.ktorm.schema.TypeReference
import java.time.LocalDateTime
import kotlin.reflect.jvm.jvmErasure

/**
 * 实体类基类
 * 和DasTable保证统一风格
 * @author DasoopsNicole@Gmail.com
 * @date 2023-05-04
 */
interface DasEntity<E : DasEntity<E>> : Entity<E>, IDo {
    /**
     * 主键id
     */
    val rowId: Long

    /**
     * 逻辑删除
     */
    var isDelete: Boolean

    /**
     * 创建时间
     */
    var createTime: LocalDateTime

    /**
     * 创建用户
     */
    var createUser: Long

    /**
     * 更新时间
     */
    var updateTime: LocalDateTime

    /**
     * 更新用户
     */
    var updateUser: Long

    abstract class Factory<E : DasEntity<E>> : TypeReference<E>() {
        @Suppress("UNCHECKED_CAST")
        operator fun invoke(create: Boolean): E {
            val entity = Entity.create(referencedKotlinType.jvmErasure) as E
            val userId = SpringUtil.getBean(AutoFill::class.java).getUserId()
            entity.isDelete = false
            entity.updateTime = LocalDateTimeUtil.now()
            entity.updateUser = userId

            if (create){
                entity.createTime = LocalDateTimeUtil.now()
                entity.createUser = userId
            }
            return entity
        }

        inline operator fun invoke(create: Boolean = true, init: E.() -> Unit): E {
            return invoke(create).apply(init)
        }
    }

    override fun delete(): Int {
        this.isDelete = true
        return this.flushChanges()
    }
}