package com.dasoops.common.db.jpa

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*

/**
 * 基本Do
 * @author DasoopsNicole@Gmail.com
 * @date 2023-04-25
 */
@MappedSuperclass
@EntityListeners(value = [AuditingEntityListener::class])
open class BaseDo {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "row_id", nullable = false, updatable = false)
    open var rowId: Long? = null

    /**
     * 逻辑删除(true为删除)
     */
    @Column(name = "is_delete", nullable = false, updatable = true)
    open var isDelete: IsDelete? = IsDelete.NO

    /**
     * 创建用户(通常为Qid)
     */
    @CreatedBy
    @Column(name = "create_user", nullable = false, updatable = false)
    open var createUser: Long? = null

    /**
     * 创建时间
     */
    @CreationTimestamp
    @Column(name = "create_time", nullable = false, updatable = false)
    open var createTime: Date? = null

    /**
     * 更新用户(通常为Qid)
     */
    @LastModifiedBy
    @Column(name = "update_user", nullable = false, updatable = true)
    open var updateUser: Long? = null

    /**
     * 更新时间
     */
    @UpdateTimestamp
    @Column(name = "update_time", nullable = false, updatable = true)
    open var updateTime: Date? = null
}
