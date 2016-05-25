package fr.internship2016.prototype.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import fr.internship2016.prototype.PrototypeGame;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Into the Legend - Prototype";
        config.width = PrototypeGame.SCREEN_WIDTH;
        config.height = PrototypeGame.SCREEN_HEIGHT;
        new LwjglApplication(new PrototypeGame(), config);
    }
}
