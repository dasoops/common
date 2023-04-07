//package com.dasoops.common.code
//
//import com.dasoops.common.entity.dataenum.BooleanEnum
//import org.springframework.web.bind.annotation.RequestMethod
//
//
///**
// * swagger转换后实体类
// * @author DasoopsNicole@Gmail.com
// * @date 2023/04/04
// * @see [Swagger]
// */
//data class Swagger(
//    val host: String = "127.0.0.1:8080",
//    val basePath: String = "/",
//    val tagList: List<String>?,
//    val requestList: List<Request>?,
//    val entityList: List<Entity>?,
//)
//
//data class Request(
//    val tag: String?,
//    val type: RequestMethod,
//    val description: String = "",
//    val paramList: List<Param>?,
//    val result: Result?,
//    val deprecated: BooleanEnum = BooleanEnum.FALSE,
//)
//
//data class Result(
//    val name: String,
//    val require: BooleanEnum,
//    val type: EntityType,
//)
//

//
//data class Param(
//
//)
//
//data class Entity(
//
//)
