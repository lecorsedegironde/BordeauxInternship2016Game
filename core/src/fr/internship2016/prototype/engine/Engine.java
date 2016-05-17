package fr.internship2016.prototype.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Array;
import fr.internship2016.prototype.engine.input.ITLInput;
import fr.internship2016.prototype.gameState.GameState;
import fr.internship2016.prototype.screen.debug.ITLDebugRenderer;

/**
 * Created by bastien on 12/05/16.
 */
public class Engine implements Screen {

    //GameState
    private GameState gameState;
    private boolean pause;

    //Renderer
    private ITLDebugRenderer renderer;

    //Input
    private ITLInput input;
    private Array<Action> actions;

    public Engine() {
        //Declare GameState
        gameState = new GameState();
        Gdx.app.log("GAME", "GameState setup");
        renderer = new ITLDebugRenderer(gameState);
        Gdx.app.log("GAME", "Debug renderer set up");

        //No pause
        pause = false;
        Gdx.app.log("GAME", "Pause set on false");

        //Input management
        input = new ITLInput(false);
        actions = new Array<>();

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {

                Action key = input.getActionFromKey(keycode);
                if (!actions.contains(key, false) && key != Action.NO_ACTION) {
                    actions.add(key);
                }
                return true;
            }

            @Override
            public boolean keyUp(int keycode) {
                actions.removeValue(input.getActionFromKey(keycode), false);
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        //Update GameState if not paused
        if (!pause) {
            gameState.update(delta);
            Gdx.app.log("GAME", "Update GameState");
        } else {
            Gdx.app.log("GAME", "Game paused");
        }
        //Render GameState
        renderer.render(gameState);
        Gdx.app.log("GAME", "Render GameState");

        //Process actions
        //Prevent player from moving if there no key pressed
        gameState.stopMovement();
        for (Action a : actions) {
            //TODO Complete function

            switch (a) {
                case RIGHT:
                    gameState.moveRight();
                    Gdx.app.log("GAME", "Move Right");
                    break;
                case LEFT:
                    gameState.moveLeft();
                    Gdx.app.log("GAME", "Move Left");
                    break;
                case JUMP:
                    gameState.jump();
                    Gdx.app.log("GAME", "Jump");
                    actions.removeValue(a, false);
                    break;
                case INVISIBILITY:
                    gameState.invisibility();
                    Gdx.app.log("GAME", "Invisibility");
                    break;
                case ATTACK:
                    break;
                case SPELL_ONE:
                    gameState.fireSpell(Action.SPELL_ONE);
                    Gdx.app.log("GAME", "Fire spell 1");
                    actions.removeValue(a, false);
                    break;
                case SPELL_TWO:
                    gameState.fireSpell(Action.SPELL_TWO);
                    Gdx.app.log("GAME", "Fire spell 2");
                    actions.removeValue(a, false);
                    break;
                case SPELL_THREE:
                    gameState.fireSpell(Action.SPELL_THREE);
                    Gdx.app.log("GAME", "Fire spell 3");
                    actions.removeValue(a, false);
                    break;
                case INVENTORY:
                    break;
                case PAUSE:
                    pause = !pause;
                    Gdx.app.log("GAME", "Pause set on" + (pause ? "true":"false"));
                    actions.removeValue(a, false);
                    break;
                case RESET:
                    gameState.reset();
                    Gdx.app.log("GAME", "Reset");
                    actions.removeValue(a, false);
                    break;
                case QUIT:
                    Gdx.app.exit();
                    Gdx.app.log("APP", "Quit");
                    break;
                case NO_ACTION:
                    //Do nothing
                    break;
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        //TODO DO not forget
        renderer.resize(width, height);
        Gdx.app.log("SCREEN", "Resize renderer");
    }

    @Override
    public void dispose() {
        renderer.dispose();
        Gdx.app.log("GAME", "Dispose renderer");
    }

    //region unused libGDX methods
    @Override
    public void show() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
    //endregion
}
