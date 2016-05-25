package fr.internship2016.prototype;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import fr.internship2016.prototype.engine.EngineDebug;

/**
 * Main game class
 */
public class PrototypeGame extends Game {

    public static final int SCREEN_WIDTH = 840;
    public static final int SCREEN_HEIGHT = 480;

    /**
     *
     */
    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        setScreen(new EngineDebug());
        Gdx.app.log("APP", "EngineDebug set");
    }
}
