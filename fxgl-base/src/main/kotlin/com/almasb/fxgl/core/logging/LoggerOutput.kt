package com.almasb.fxgl.core.logging

import java.io.Serializable

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
interface LoggerOutput: Serializable {

    fun append(message: String)

    fun close()
}