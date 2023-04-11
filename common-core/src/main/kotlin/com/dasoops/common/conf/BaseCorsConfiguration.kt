package com.dasoops.common.conf

import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * cors配置类
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/19
 * @see [BaseCorsConfiguration]
 */
abstract class BaseCorsConfiguration {
    @Bean
    open fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry
                    .addMapping("/**")
                    .allowedOrigins("*")
                    .allowedMethods(*RequestMethod.values().map { it.name }.toTypedArray())
                    .allowedHeaders("Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With")
            }
        }
    }
}