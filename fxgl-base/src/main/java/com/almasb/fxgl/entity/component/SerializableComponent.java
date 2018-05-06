/*
 * FXGL - JavaFX Game Library. The MIT License (MIT).
 * Copyright (c) AlmasB (almaslvl@gmail.com).
 * See LICENSE for details.
 */

package com.almasb.fxgl.entity.component;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.io.serialization.JavaSerializableType;
import com.almasb.fxgl.io.serialization.SerializableType;

import java.io.IOException;

/**
 * Marks a component as serializable.
 *
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public interface SerializableComponent extends JavaSerializableType, SerializableType {

    default void writeObject(Component component, java.io.ObjectOutputStream stream)
            throws IOException {
        stream.writeObject(component.entity);
        stream.writeBoolean(component.isPaused());
    }

    default void readObject(Component component, java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException {
        component.setEntity((Entity) stream.readObject());
        if (stream.readBoolean())
            component.pause();
        else
            component.resume();
    }
}
