package fr.internship2016.prototype.screen.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Created by bastien on 11/05/16.
 * This is the game UI
 */
public class GameUI {

    //The Stage
    private Stage stage;

    //The skin
    private Skin skin;

    //Table
    private Table table;

    //UI components
    private final TextButton inventoryButton;
    private final ProgressBar lifeBar;
    private final ProgressBar manaBar;

    public GameUI() {
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        table = new Table();
        table.debug();
        table.setFillParent(true);

        inventoryButton = new TextButton("Inventory", skin, "default");
        inventoryButton.setWidth(150);
        inventoryButton.setHeight(200);
        inventoryButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                openWindow();
            }
        });

        lifeBar = new ProgressBar(0, 100, 1, false, skin, "default-horizontal");
        lifeBar.setWidth(100);
        lifeBar.setHeight(5);
        lifeBar.setColor(new Color(0x689F38FF));

        manaBar = new ProgressBar(0, 100, 0.25f, false, skin, "default-horizontal");
        manaBar.setWidth(100);
        manaBar.setHeight(5);
        manaBar.setColor(new Color(0x448AFFFF));

        VerticalGroup verticalGroup = new VerticalGroup();
        verticalGroup.addActor(lifeBar);
        verticalGroup.addActor(manaBar);

        table.add(verticalGroup);
        table.add(inventoryButton).width(inventoryButton.getWidth()).padLeft(10);
        table.row();

        table.left().bottom();

        stage.addActor(table);
    }

    public void update(float delta, double playerLP, double playerMP) {
        //Set stage viewport
        stage.getViewport().apply();

        //Update UI
        lifeBar.setValue((float) playerLP);
        manaBar.setValue((float) playerMP);

        //Draw UI
        stage.act(delta);
        stage.draw();
    }

    private void openWindow() {
        final Dialog dialog = new Dialog("Inventory", skin, "dialog");
        final TextButton swordButton = new TextButton("Sword", skin, "default");
        swordButton.setWidth(150);
        swordButton.setHeight(75);
        final TextButton spearButton = new TextButton("Spear", skin, "default");
        spearButton.setWidth(150);
        spearButton.setHeight(75);
        final TextButton closeButton = new TextButton("Close", skin, "default");
        closeButton.setWidth(150);
        closeButton.setHeight(75);

        closeButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dialog.hide();
            }
        });

        Table dialogTable = new Table();
        dialogTable.setWidth(stage.getWidth());
        dialogTable.align(Align.center | Align.bottom);
        dialogTable.setPosition(0, Gdx.graphics.getHeight());
        dialogTable.add(swordButton);
        dialogTable.add(spearButton);
        dialogTable.row();
        dialogTable.add(closeButton);
        dialog.add(dialogTable);
        dialog.show(stage);
    }
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    public void dispose() {
        stage.dispose();
    }

    public Stage getStage() {
        return stage;
    }
}
