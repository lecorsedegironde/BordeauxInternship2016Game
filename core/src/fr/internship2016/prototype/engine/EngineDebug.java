package fr.internship2016.prototype.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Array;
import fr.internship2016.prototype.engine.input.Action;
import fr.internship2016.prototype.engine.input.ITLInput;
import fr.internship2016.prototype.gameState.GameState;
import fr.internship2016.prototype.screen.ITLDebugRenderer;
import fr.internship2016.prototype.screen.ui.GameUiDebug;

/**
 * Created by bastien on 12/05/16.
 */
public class EngineDebug implements Screen {

    //GameState
    private GameState gameState;
    private boolean pause;

    //Renderer
    private ITLDebugRenderer renderer;

    //Ui
    private GameUiDebug uiDebug;

    //Input
    private ITLInput input;
    private Array<Action> actions;

    public EngineDebug() {
        //Declare GameState
        gameState = new GameState();
        Gdx.app.log("GAME", "GameState setup");
        renderer = new ITLDebugRenderer(gameState);
        Gdx.app.log("GAME", "Debug renderer set up");

        //No pause
        pause = false;
        Gdx.app.log("GAME", "Pause set on false");

        //Ui
        uiDebug = new GameUiDebug();

        //Input management
        input = new ITLInput(false);
        actions = new Array<>();

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(uiDebug.getStage());
        multiplexer.addProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {

                Action key = input.getActionFromKey(keycode);
                if (!actions.contains(key, false) && key != Action.NO_ACTION
                        && (!pause || key == Action.PAUSE)) {
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
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render(float delta) {
        //Update GameState if not paused
        if (!pause && !uiDebug.isUiOpened()) {
            gameState.update(delta);
        }

        //Update ui
        uiDebug.update(delta, gameState);

        //Render GameState
        renderer.render(gameState, uiDebug, pause);

        //Process actions
        //Prevent player from moving if there no key pressed
        gameState.stopMovement();
        for (Action a : actions) {
            //COMPLETE actions
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
                    gameState.attack();
                    Gdx.app.log("GAME", "Attack with weapon");
                    actions.removeValue(a, false);
                    break;
                case SPELL_ONE:
                    gameState.fireSpell(Action.SPELL_ONE);
                    Gdx.app.log("GAME", "Fire spell 1");
                    break;
                case SPELL_TWO:
                    gameState.fireSpell(Action.SPELL_TWO);
                    Gdx.app.log("GAME", "Fire spell 2");
                    break;
                case SPELL_THREE:
                    gameState.fireSpell(Action.SPELL_THREE);
                    Gdx.app.log("GAME", "Fire spell 3");
                    break;
                case INVENTORY:
                    //UI does this
                    break;
                case SWITCH_WEAPON_UP:
                    //LATER
                    break;
                case SWITCH_WEAPON_DOWN:
                    //LATER
                    break;
                case PAUSE:
                    pauseResumeGame();
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

    private void pauseResumeGame() {
        pause = !pause;
        Gdx.app.log("GAME", "Pause set on" + (pause ? "true":"false"));
    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
        uiDebug.resize(width, height);
        Gdx.app.log("SCREEN", "Resize renderer");
    }

    @Override
    public void dispose() {
        uiDebug.dispose();
        renderer.dispose();
        Gdx.app.log("GAME", "Dispose renderer");
    }

    //region unused libGDX methods
    @Override
    public void show() {
    }

    @Override
    public void pause() {
        pauseResumeGame();
    }

    @Override
    public void resume() {
        pauseResumeGame();
    }

    @Override
    public void hide() {
    }
    //endregion
}
