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
    ITLInput input;
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
        for (Action a : actions) {
            //TODO Complete function
            switch (a) {
                case RIGHT:
                    gameState.moveRight();
                    break;
                case LEFT:
                    gameState.moveLeft();
                    break;
                case JUMP:
                    break;
                case INVISIBILITY:
                    gameState.invisibility();
                    break;
                case ATTACK:
                    break;
                case SPELL_ONE:
                    break;
                case SPELL_TWO:
                    break;
                case SPELL_THREE:
                    break;
                case INVENTORY:
                    break;
                case PAUSE:
                    pause = !pause;
                    Gdx.app.log("GAME", "Pause set on" + (pause ? "true":"false"));
                    break;
                case RESET:
                    break;
                case QUIT:
                    break;
                case NO_ACTION:
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
