/*
 * FXGL - JavaFX Game Library. The MIT License (MIT).
 * Copyright (c) AlmasB (almaslvl@gmail.com).
 * See LICENSE for details.
 */

package com.almasb.fxgl.entity.components;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.component.SerializableComponent;
import com.almasb.fxgl.io.serialization.Bundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Represents an int value based component.
 * <p>
 * <pre>
 * Example:
 *
 * public class MoneyComponent extends IntegerComponent {
 *      public MoneyComponent(int initialValue) {
 *          super(initialValue);
 *      }
 * }
 *
 * Entity player = ...
 * player.addComponent(new MoneyComponent(5000));
 *
 * int money = player.getComponent(MoneyComponent.class).getValue();
 *
 * </pre>
 *
 * @author Almas Baimagambetov (AlmasB) (almaslvl@gmail.com)
 */
public abstract class IntegerComponent extends Component implements SerializableComponent {
    private IntegerProperty property;

    /**
     * No-arg ctor, initializes the value to 0.
     */
    public IntegerComponent() {
        this(0);
    }

    /**
     * Constructs an int value component with given
     * initial value.
     *
     * @param initialValue the initial value
     */
    public IntegerComponent(int initialValue) {
        property = new SimpleIntegerProperty(initialValue);
    }

    /**
     * @return value property
     */
    public final IntegerProperty valueProperty() {
        return property;
    }

    /**
     * @return value held by this component
     */
    public final int getValue() {
        return property.get();
    }

    /**
     * Set value to this component.
     *
     * @param value new value
     */
    public final void setValue(int value) {
        property.set(value);
    }

    @Override
    public void write(@NotNull Bundle bundle) {
        bundle.put("value", getValue());
    }

    @Override
    public void read(@NotNull Bundle bundle) {
        setValue(bundle.get("value"));
    }

    private void writeObject(java.io.ObjectOutputStream stream)
            throws IOException {
        stream.defaultWriteObject();
        writeObject(this, stream);
    }

    private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException {

        stream.defaultReadObject();
        readObject(this, stream);
    }


    @Override
    public String toString() {
        return getClass().getSimpleName() + "[value=" + getValue() + "]";
    }
}
