/*
 * FXGL - JavaFX Game Library. The MIT License (MIT).
 * Copyright (c) AlmasB (almaslvl@gmail.com).
 * See LICENSE for details.
 */

package coldwarrl.sandbox;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.GameWorld;
import com.almasb.fxgl.entity.components.IDComponent;
import com.almasb.fxgl.entity.view.EntityView;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.io.serialization.Bundle;
import com.almasb.fxgl.saving.DataFile;
import com.almasb.fxgl.settings.GameSettings;
import com.almasb.fxgl.settings.MenuItem;
import com.almasb.fxgl.util.Optional;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import manual.RangeTest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.EnumSet;

/**
 * Shows how to use input service and bind actions to triggers.
 */
public class ReloadGameTest extends GameApplication {


    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(800);
        settings.setHeight(600);
        settings.setTitle("EntityComponentSample");
        settings.setVersion("0.1");
    }

    @Override
    protected void initGame() {
        if (getGameWorld().isReloaded()) return;
        Entities.builder()
                .at(400, 300)
                .with(new IDComponent("test", 2332))
                .viewFromNode(new Rectangle(40, 40))
                // 3. add a new instance of component to entity
                .buildAndAttach(getGameWorld());

        Entities.builder()
                .at(200, 300)
                .with(new IDComponent("test2", 23323))
                .viewFromNode(new Rectangle(40, 40))
                .markAsNotSerializable()
                // 3. add a new instance of component to entity
                .buildAndAttach(getGameWorld());
    }

    @Override
    protected void initInput() {
        if (getGameWorld().isReloaded()) return;
        Input input = getInput();


        input.addAction(new UserAction("Move Down") {
            @Override
            protected void onAction() {
               reloadGame();
            }
        }, KeyCode.S);

    }

    @Override
    protected void onGameReloaded() {
        Entity entity = FXGL.getApp().getGameWorld().getEntityByID("test", 2332).get();
        entity.getView().addNode((new Rectangle(40, 40)));

        Optional<Entity> entity2 = FXGL.getApp().getGameWorld().getEntityByID("test2", 23323);
        assert(!entity2.isPresent());
k
    }

    private void reloadGame() {
        Serializer.INSTANCE.serializeToFile(FXGL.getApp().getGameWorld(), "reload_game_test.sav");
        reloadGame("reload_game_test.sav");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
