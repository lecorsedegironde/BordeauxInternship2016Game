package fr.internship2016.prototype.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import fr.internship2016.prototype.PrototypeGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Into the Legend - Prototype";
		config.width = 800;
		config.height = 480;
		new LwjglApplication(new PrototypeGame(), config);
	}
}
