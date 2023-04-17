package com.dasoops.common.db.entity.dbo

import cn.hutool.core.lang.ObjectId
import com.dasoops.common.core.IDo
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
) : IDo