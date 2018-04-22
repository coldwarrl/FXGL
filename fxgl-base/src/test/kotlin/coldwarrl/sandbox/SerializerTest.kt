/*
 * FXGL - JavaFX Game Library. The MIT License (MIT).
 * Copyright (c) AlmasB (almaslvl@gmail.com).
 * See LICENSE for details.
 */

package coldwarrl.sandbox

import com.almasb.fxgl.app.FXGLMock
import com.almasb.fxgl.entity.Entities
import com.almasb.fxgl.entity.EntitiesTest
import com.almasb.fxgl.entity.Entity
import com.almasb.fxgl.entity.component.Component
import com.almasb.fxgl.physics.BoundingShape
import com.almasb.fxgl.physics.HitBox
import javafx.geometry.Point2D
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

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
    fun `smokeTest`() {
        val e = Entities.builder()
                .at(100.0, 100.0)
                .type(EntityType.TEST1)
                .build()

        Serializer.serializeToFile(e, "test.bin")

        val e2 = Serializer.deserializeFromFile("test.bin") as Entity

        Assertions.assertEquals(3, e2.components.size())

    }

}