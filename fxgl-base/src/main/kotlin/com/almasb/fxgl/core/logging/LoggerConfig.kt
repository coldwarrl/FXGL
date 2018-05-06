package com.almasb.fxgl.core.logging

import java.io.IOException
import java.io.Serializable
import java.time.format.DateTimeFormatter

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
class LoggerConfig: Serializable {

    var dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS")
    var messageFormatter = DefaultMessageFormatter()

    fun copy(): LoggerConfig {
        val copy = LoggerConfig()
        copy.dateTimeFormatter = dateTimeFormatter
        copy.messageFormatter = messageFormatter
        return copy
    }

    @Throws(IOException::class, ClassNotFoundException::class)
    private fun readObject(stream: java.io.ObjectInputStream) {
        dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS")
        messageFormatter = DefaultMessageFormatter()
    }

}