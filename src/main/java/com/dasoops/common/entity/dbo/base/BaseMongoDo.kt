package com.dasoops.common.entity.dbo.base

import cn.hutool.core.date.DateUtil
import com.baomidou.mybatisplus.annotation.FieldFill
import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.dasoops.common.entity.enums.database.DbBooleanEnum
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import java.util.*

/**
 * @Title: BasePo
 * @ClassPath com.dasoops.dasserver.cq.entity.po.BasePo
 * @Author DasoopsNicole@Gmail.com
 * @Date 2022/10/31
 * @Version 1.0.0
 * @Description: mongoDo基类
 */
abstract class BaseMongoDo(
    /**
     * 主键id
     */
    @Id
    open var rowId: ObjectId? = null,

    /**
     * 逻辑删除(true为删除)
     */
    open var isDelete: DbBooleanEnum = DbBooleanEnum.FALSE,

    /**
     * 创建时间
     */
    open var createTime: Date? = null,

    /**
     * 创建用户(通常为Qid)
     */
    open var createUser: Long? = null,

    /**
     * 更新时间
     */
    open var updateTime: Date? = null,

    /**
     * 更新用户(通常为Qid)
     */
    open var updateUser: Long? = null,
) : IDo