package com.dasoops.common.entity.dbo.base

import cn.hutool.core.date.DateUtil
import com.baomidou.mybatisplus.annotation.FieldFill
import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.dasoops.common.entity.enums.database.DbBooleanEnum
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Field
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
    @Field("_id")
    open var rowId: ObjectId? = null,

    /**
     * 逻辑删除(true为删除)
     */
    @Field("IS_DELETE")
    open var isDelete: DbBooleanEnum = DbBooleanEnum.FALSE,

    /**
     * 创建时间
     */
    @Field("CREATE_TIME")
    open var createTime: Date? = null,

    /**
     * 创建用户(通常为Qid)
     */
    @Field("CREATE_USER")
    open var createUser: Long? = null,

    /**
     * 更新时间
     */
    @Field("UPDATE_TIME")
    open var updateTime: Date? = null,

    /**
     * 更新用户(通常为Qid)
     */
    @Field("UPDATE_USER")
    open var updateUser: Long? = null,
) : IDo