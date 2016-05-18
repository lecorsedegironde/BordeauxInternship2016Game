package fr.internship2016.prototype.screen.interfaces;

import fr.internship2016.prototype.gameState.GameState;
import fr.internship2016.prototype.screen.ui.GameUiDebug;

/**
 * Created by bastien on 13/05/16.
 */
public interface Render {
    void render(GameState gameState, GameUiDebug uiDebug);
    void resize(int width, int height);
    void dispose();
}
