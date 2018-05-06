/*
 * FXGL - JavaFX Game Library. The MIT License (MIT).
 * Copyright (c) AlmasB (almaslvl@gmail.com).
 * See LICENSE for details.
 */

package coldwarrl.sandbox

import com.almasb.fxgl.app.FXGLMock
import com.almasb.fxgl.entity.Entities
import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.entity.GameWorld
import com.almasb.fxgl.entity.components.IDComponent
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
        val e = getEntity()

        val outputStream = Serializer.serializeToMemory(e)
        val e2 = Serializer.deserializeFromMemory(ByteArrayInputStream(outputStream.toByteArray())) as Entity

        Assertions.assertEquals(6, e2.components.size())
    }

    private fun getEntity(): Entity {
        return Entities.builder()
                .at(100.0, 100.0)
                .type(EntityType.TEST1)
                .with(IDComponent("test", 2))
                .build()
    }

    @Test
    fun `smokeGameWorldTest`() {
        var gameWorld = GameWorld()
        gameWorld.addEntity(getEntity())

        val outputStream = Serializer.serializeToMemory(gameWorld)
        gameWorld = Serializer.deserializeFromMemory(ByteArrayInputStream(outputStream.toByteArray())) as GameWorld
        val entity = gameWorld.getEntitiesByType(EntityType.TEST1).first()
        Assertions.assertSame(2, entity.id)
    }

}