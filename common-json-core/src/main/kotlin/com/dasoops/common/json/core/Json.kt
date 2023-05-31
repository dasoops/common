package com.dasoops.common.json.core

import java.util.ServiceLoader

/**
 * json
 * @author DasoopsNicole@Gmail.com
 * @date 2023/05/31
 */
object Json : IJson by ServiceLoader.load(IJson::class.java).first()