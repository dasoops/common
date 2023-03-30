package com.dasoops.common.task

import com.dasoops.common.AutoInit
import com.dasoops.common.ICache
import com.dasoops.common.ICacheManager
import com.dasoops.common.IService
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware


/**
 * 初始化任务
 * @title: BaseTask
 * @classPath com.dasoops.common.task.BaseTask
 * @author DasoopsNicole@Gmail.com
 * @date 2023/02/23
 * @version 1.0.0
 * @see [InitTask]
 */
open class InitTask : ITask, ApplicationContextAware, ApplicationRunner {

    private var cacheSet: HashSet<AutoInit> = HashSet()
    private var serviceSet: HashSet<AutoInit> = HashSet()
    private var otherSet: HashSet<AutoInit> = HashSet()
    private var cacheManager: ICacheManager? = null

    /**
     * 设置应用程序上下文
     * @param [applicationContext] 应用程序上下文
     */
    override fun setApplicationContext(applicationContext: ApplicationContext) {
        val autoInitMap = applicationContext.getBeansOfType(AutoInit::class.java)

        autoInitMap.values.forEach {
            when (it) {
                is ICacheManager -> cacheManager = it
                is IService -> serviceSet.add(it)
                is ICache -> cacheSet.add(it)
                else -> otherSet.add(it)
            }
        }
    }

    /**
     * 初始化
     */
    suspend fun init() {
        cacheManager?.run { init(this) }
        //缓存初始化
        init(*cacheSet.toTypedArray())
        //服务层初始化
        init(*serviceSet.toTypedArray())
        //其余初始化
        init(*otherSet.toTypedArray())
        InitGlobal.inInitialize = false
    }

    suspend fun init(vararg autoInit: AutoInit) = coroutineScope {
        autoInit.map { launch { it.init() } }.joinAll()
    }

    override fun run(args: ApplicationArguments?) = runBlocking {
        init()
    }
}