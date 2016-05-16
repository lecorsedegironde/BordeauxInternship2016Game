package fr.internship2016.prototype.screen.interfaces;

import fr.internship2016.prototype.gameState.GameState;

/**
 * Created by bastien on 13/05/16.
 */
public interface Render {
    void render(GameState gameState);
    void resize(int width, int height);
    void dispose();
}
