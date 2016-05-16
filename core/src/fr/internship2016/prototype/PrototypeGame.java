package fr.internship2016.prototype;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import fr.internship2016.prototype.engine.Engine;

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
        setScreen(new Engine());
        Gdx.app.log("APP", "Engine set");
    }
}
