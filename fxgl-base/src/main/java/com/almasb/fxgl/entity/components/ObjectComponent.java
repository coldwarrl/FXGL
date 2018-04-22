/*
 * FXGL - JavaFX Game Library. The MIT License (MIT).
 * Copyright (c) AlmasB (almaslvl@gmail.com).
 * See LICENSE for details.
 */

package com.almasb.fxgl.entity.components;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.component.SerializableComponent;
import com.almasb.fxgl.io.serialization.Bundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @author Almas Baimagambetov (AlmasB) (almaslvl@gmail.com)
 */
public abstract class ObjectComponent<T> extends Component implements SerializableComponent {
    private ObjectProperty<T> property;

    /**
     * Constructs an object value component with given
     * initial value.
     *
     * @param initialValue the initial value
     */
    public ObjectComponent(T initialValue) {
        property = new SimpleObjectProperty<>(initialValue);
    }

    /**
     * @return value property
     */
    public final ObjectProperty<T> valueProperty() {
        return property;
    }

    /**
     * @return value held by this component
     */
    public final T getValue() {
        return property.get();
    }

    /**
     * Set value to this component.
     *
     * @param value new value
     */
    public final void setValue(T value) {
        property.set(value);
    }

    private void writeObject(java.io.ObjectOutputStream stream)
            throws IOException {
        stream.writeObject(getValue());
    }

    private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        property = new SimpleObjectProperty<>();
        setValue((T) stream.readObject());
    }

    @Override
    public void write(@NotNull Bundle bundle) {

    }

    @Override
    public void read(@NotNull Bundle bundle) {

    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[value=" + getValue() + "]";
    }
}
