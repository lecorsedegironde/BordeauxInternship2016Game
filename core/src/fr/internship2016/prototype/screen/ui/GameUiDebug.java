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
import fr.internship2016.prototype.gameState.GameState;

/**
 * Created by bastien on 18/05/16.
 */
public class GameUiDebug {
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
    private Label label;
    //Inventory window components
    private Dialog dialog;


    //String array for inventory
    private Array<String> inventory;
    private String selectedItem = "";

    //Is the ui over the game, i.e. shall be the game paused?
    private boolean uiOpened;

    public GameUiDebug() {
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
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
        inventory = new Array<>();

        lifeBar = new ProgressBar(0, 100, 1, false, skin, "default-horizontal");
        lifeBar.setWidth(100);
        lifeBar.setHeight(5);
        lifeBar.setColor(new Color(0x689F38FF));

        manaBar = new ProgressBar(0, 100, 0.25f, false, skin, "default-horizontal");
        manaBar.setWidth(100);
        manaBar.setHeight(5);
        manaBar.setColor(new Color(0x448AFFFF));

        label = new Label("fps:"+ Gdx.graphics.getFramesPerSecond(), skin, "default");

        VerticalGroup verticalGroup = new VerticalGroup();
        verticalGroup.addActor(lifeBar);
        verticalGroup.addActor(manaBar);

        table.add(verticalGroup);
        table.add(inventoryButton).width(inventoryButton.getWidth()).padLeft(10);
        table.add(label).width(200).padLeft(50);
        table.row();

        table.left().bottom();

        stage.addActor(table);

        //Ui over
        uiOpened = false;
    }

    public void update(float delta, GameState gameState) {
        //Update UI
        lifeBar.setValue((float) gameState.getPlayer().getLife());
        manaBar.setValue((float) gameState.getPlayer().getMana());
        label.setText("fps:"+ Gdx.graphics.getFramesPerSecond());

        Array<String> tempInventory = new Array<>();
        for (Object o : gameState.getPlayer().getInventory()) {
            tempInventory.add(o.toString());
        }

        //Are the two inventory the same?
        if (!tempInventory.equals(inventory)) {
            inventory = tempInventory;
        }

        //Send selected item
        if (!selectedItem.equals("")) {
            gameState.useInventory(selectedItem);
            selectedItem = "";
        }

        //Update UI
        stage.act(delta);
    }

    public void draw() {
        //Set stage viewport
        stage.getViewport().apply();
        stage.draw();
    }

    private void openWindow() {
        uiOpened = true;

        dialog = new Dialog("Inventory", skin, "dialog");
        final Table dialogTable = new Table();

        dialogTable.setWidth(stage.getWidth());
        dialogTable.align(Align.center | Align.bottom);
        dialogTable.setPosition(0, Gdx.graphics.getHeight());

        for (String s : inventory) {
            TextButton tb = new TextButton(s, skin, "default");
            tb.setWidth(150);
            tb.setHeight(75);
            tb.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    selectedItem = s;
                    closeWindow();
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
                closeWindow();
            }
        });

        dialogTable.row();
        dialogTable.add(closeButton);
        dialog.add(dialogTable);
        dialog.show(stage);
    }

    private void closeWindow() {
        dialog.hide();
        uiOpened = false;
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

    public boolean isUiOpened() {
        return uiOpened;
    }
}
