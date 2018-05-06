package com.almasb.fxgl.util

import org.nustaq.serialization.FSTObjectInput
import org.nustaq.serialization.FSTObjectOutput
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.FileInputStream
import java.io.FileOutputStream

object Serializer {
    fun serializeToFile(myObject: Any, filePath: String) {
        val fileOutputStream = FileOutputStream(filePath)
        val out = FSTObjectOutput(fileOutputStream)
        out.writeObject(myObject, null)
        out.close()
    }


    fun deserializeFromFile(filePath: String): Any {
        val fileInputStream: FileInputStream = FileInputStream(filePath)
        val inStream = FSTObjectInput(fileInputStream)
        val myObject = inStream.readObject()
        inStream.close()

        return myObject
    }

    fun serializeToMemory(myObject: Any): ByteArrayOutputStream {
        val byteStream = ByteArrayOutputStream()
        val out = FSTObjectOutput(byteStream)
        out.writeObject(myObject, null)
        out.close()

        return byteStream
    }

    fun deserializeFromMemory(inputStream: ByteArrayInputStream): Any {
        val inObject = FSTObjectInput(inputStream)
        val myObject = inObject.readObject()
        inObject.close()

        return myObject
    }


}