package com.dasoops.common.entity.qro

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Field

/**
 * Object QueryResultObject
 * @title: ObjectIdQro
 * @classPath com.dasoops.common.entity.qro.ObjectIdQro
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/27
 * @version 1.0.0
 * @see [ObjectIdQro]
 */
data class ObjectIdQro(@Field("id") val id: ObjectId)