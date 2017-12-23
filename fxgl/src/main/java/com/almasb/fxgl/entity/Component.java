/*
 * FXGL - JavaFX Game Library. The MIT License (MIT).
 * Copyright (c) AlmasB (almaslvl@gmail.com).
 * See LICENSE for details.
 */

package com.almasb.fxgl.entity;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public abstract class Component extends Module {

    public String id = "";

    public Component() { }


    public Component(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id.isEmpty() ? super.toString(): id;
    }
}
