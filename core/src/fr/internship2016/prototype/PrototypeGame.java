package fr.internship2016.prototype;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import fr.internship2016.prototype.engine.EngineDebug;

/**
 * Main game class
 */
public class PrototypeGame extends Game {

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
