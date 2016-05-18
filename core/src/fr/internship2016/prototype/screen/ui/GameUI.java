package fr.internship2016.prototype.screen.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import fr.internship2016.prototype.movable.armed.Player;
import fr.internship2016.prototype.weapon.WeaponStyles;

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

    //Is the ui over the game, i.e. shall be the game paused?
    private boolean uiOver;

    //Related to inventory
    private Player player;

    public GameUI(Player player) {
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        table = new Table();
        table.debug();
        table.setFillParent(true);

        inventoryButton = new TextButton("Inventory", skin, "default");
        inventoryButton.setWidth(150);
        inventoryButton.setHeight(200);
        inventoryButton.addListener(new ClickListener() {
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

        //Player inventory
        this.player = player;

        //Ui over
        uiOver = false;
    }

    public void update(float delta, Player player) {
        //Set stage viewport
        stage.getViewport().apply();

        //Update UI
        this.player = player;
        lifeBar.setValue((float) player.getLife());
        manaBar.setValue((float) player.getMagicPoints());

        //Draw UI
        stage.act(delta);
        stage.draw();
    }

    private void openWindow() {
        uiOver = true;

        final Dialog dialog = new Dialog("Inventory", skin, "dialog");
        final Table dialogTable = new Table();

        dialogTable.setWidth(stage.getWidth());
        dialogTable.align(Align.center | Align.bottom);
        dialogTable.setPosition(0, Gdx.graphics.getHeight());


        for (WeaponStyles w : player.getInventory()) {
            TextButton tb = new TextButton(w.getName(), skin, "default");
            tb.setWidth(150);
            tb.setHeight(75);
            tb.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    player.setWeapon(w);
                }
            });
            dialogTable.add(tb);
        }

        final TextButton closeButton = new TextButton("Close", skin, "default");
        closeButton.setWidth(150);
        closeButton.setHeight(75);

        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dialog.hide();
                uiOver = false;
            }
        });

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

    public boolean isUiOver() {
        return uiOver;
    }
}
