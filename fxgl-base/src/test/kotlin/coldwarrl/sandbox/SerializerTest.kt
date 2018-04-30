/*
 * FXGL - JavaFX Game Library. The MIT License (MIT).
 * Copyright (c) AlmasB (almaslvl@gmail.com).
 * See LICENSE for details.
 */

package coldwarrl.sandbox

import com.almasb.fxgl.app.FXGL
import com.almasb.fxgl.app.FXGLMock
import com.almasb.fxgl.entity.Entities
import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.entity.GameWorld
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream

class SerializerTest {

    companion object {
        @BeforeAll
        @JvmStatic fun before() {
            FXGLMock.mock()
        }
    }

    private enum class EntityType {
        TEST1
    }

    @Test
    fun `smokeTestEntityTest`() {
        val e = Entities.builder()
                .at(100.0, 100.0)
                .type(EntityType.TEST1)
                .build()

        val outputStream = Serializer.serializeToMemory(e)
        val e2 = Serializer.deserializeFromMemory(ByteArrayInputStream(outputStream.toByteArray())) as Entity

        Assertions.assertEquals(3, e2.components.size())
    }

    @Test
    fun `smokeGameWorldTest`() {
        val outputStream = Serializer.serializeToMemory(GameWorld())
        val gameWorld = Serializer.deserializeFromMemory(ByteArrayInputStream(outputStream.toByteArray()))
    }

}