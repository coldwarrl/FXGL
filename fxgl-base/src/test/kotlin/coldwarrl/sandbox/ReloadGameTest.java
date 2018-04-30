/*
 * FXGL - JavaFX Game Library. The MIT License (MIT).
 * Copyright (c) AlmasB (almaslvl@gmail.com).
 * See LICENSE for details.
 */

package coldwarrl.sandbox;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.GameWorld;
import com.almasb.fxgl.entity.components.IDComponent;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.settings.GameSettings;
import javafx.scene.input.KeyCode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Shows how to use input service and bind actions to triggers.
 */
public class ReloadGameTest extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(800);
        settings.setHeight(600);
        settings.setTitle("InputSample");
        settings.setVersion("0.1");
    }

    @Override
    protected void initInput() {
        // 1. get input service
        Input input = getInput();

        // 2. add key/mouse bound actions
        // when app is running press F to see output to console
        input.addAction(new UserAction("Print Line") {
            @Override
            protected void onActionBegin() {
                reloadGame();
            }

            @Override
            protected void onAction() {
                System.out.println("On Action");
            }

            @Override
            protected void onActionEnd() {
                System.out.println("Action End");
            }
        }, KeyCode.F);

    }

    private void reloadGame() {
        Entity entity = Entities.builder()
                .with(new IDComponent("test", 1))
                .buildAndAttach();

        ByteArrayOutputStream stream = Serializer.INSTANCE.serializeToMemory(getGameWorld());
        GameWorld gameWorldClone = (GameWorld) Serializer.INSTANCE.deserializeFromMemory((new ByteArrayInputStream(stream.toByteArray())));

        reloadGame(gameWorldClone);
        Entity entity2 = gameWorldClone.getEntities().get(0);
        System.out.println(entity2.getComponent(IDComponent.class).getID());

    }

    public static void main(String[] args) {
        launch(args);
    }
}
