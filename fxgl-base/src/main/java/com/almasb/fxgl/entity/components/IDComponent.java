/*
 * FXGL - JavaFX Game Library. The MIT License (MIT).
 * Copyright (c) AlmasB (almaslvl@gmail.com).
 * See LICENSE for details.
 */

package com.almasb.fxgl.entity.components;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.component.SerializableComponent;
import com.almasb.fxgl.io.serialization.Bundle;
import javafx.beans.property.SimpleDoubleProperty;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Adds ID to an entity, so it can be uniquely identified.
 *
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class IDComponent extends Component implements SerializableComponent {

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;
    private int id;

    /**
     * Constructs ID component with given entity name and id.
     * The combination of name and id must be unique.
     *
     * @param name string representation of entity name
     * @param id   numeric id that uniquely identifies the entity with given name
     */
    public IDComponent(String name, int id) {
        this.name = name;
        this.id = id;
    }

    /**
     * @return entity name / string representation
     */
    public final String getName() {
        return name;
    }

    /**
     * @return numeric id
     */
    public final int getID() {
        return id;
    }

    /**
     * @return full id, this must be unique
     */
    public final String getFullID() {
        return name + ":" + id;
    }

    @Override
    public int hashCode() {
        return getFullID().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        // just assume it's IDComponent
        if (obj instanceof IDComponent)
            return ((IDComponent) obj).getFullID().equals(getFullID());
        else return false;
    }

    @Override
    public String toString() {
        return getFullID();
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
    public void write(@NotNull Bundle bundle) {
        bundle.put("name", name);
        bundle.put("id", id);
    }

    @Override
    public void read(@NotNull Bundle bundle) {
        name = bundle.get("name");
        id = bundle.get("id");
    }
}
