package com.dasoops.common.config

import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletResponse

/**
 * @title: BaseCorsConfiguration
 * @classPath com.dasoops.common.config.BaseCorsConfiguration
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/19
 * @version 1.0.0
 * @description: cors配置类
 * @see [BaseCorsConfiguration]
 */
abstract class BaseCorsConfiguration {
    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry
                    .addMapping("/**")
                    .allowedMethods(*RequestMethod.values().map { it.name }.toTypedArray())
                    .allowedHeaders("Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With")
            }
        }
    }
}