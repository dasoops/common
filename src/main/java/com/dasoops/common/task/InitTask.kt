package com.dasoops.common.task

import com.dasoops.common.cache.ICache
import com.dasoops.common.service.IService
import kotlinx.coroutines.*
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import javax.annotation.PostConstruct

/**
 * 初始化任务
 * @title: BaseTask
 * @classPath com.dasoops.common.task.BaseTask
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/23
 * @version 1.0.0
 * @see [InitTask]
 */
open class InitTask : ITask, ApplicationContextAware {

    private var cacheSet: HashSet<ICache> = HashSet()
    private var serviceSet: HashSet<IService> = HashSet()
    private var otherSet: HashSet<AutoInit> = HashSet()

    /**
     * 设置应用程序上下文
     * @param [applicationContext] 应用程序上下文
     */
    override fun setApplicationContext(applicationContext: ApplicationContext) {
        val autoInitMap = applicationContext.getBeansOfType(AutoInit::class.java)

        autoInitMap.values.forEach {
            when (it) {
                is ICache -> cacheSet.add(it)
                is IService -> serviceSet.add(it)
                else -> otherSet.add(it)
            }
        }
    }

    /**
     * 初始化
     */
    @PostConstruct
    fun init() {
        //缓存初始化
        init(cacheSet)
        //服务层初始化
        init(serviceSet)
        //其余初始化
        init(otherSet)
    }

    fun init(needInitSet: Set<AutoInit>) = runBlocking {
        needInitSet
            //异步调用初始化方法
            .map { launch { it.init() } }
            //等待
            .joinAll()
    }
}