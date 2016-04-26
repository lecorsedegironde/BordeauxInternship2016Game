package fr.internship2016.prototype;

import com.badlogic.gdx.Game;
import fr.internship2016.prototype.screen.GameScreen;

public class PrototypeGame extends Game {

    @Override
    public void create() {
        setScreen(new GameScreen());
    }

}
