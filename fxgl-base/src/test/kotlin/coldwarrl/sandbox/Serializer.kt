package coldwarrl.sandbox

import org.nustaq.serialization.FSTObjectInput
import org.nustaq.serialization.FSTObjectOutput
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

}