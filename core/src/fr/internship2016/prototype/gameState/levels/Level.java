package fr.internship2016.prototype.gameState.levels;

import com.badlogic.gdx.utils.Array;
import fr.internship2016.prototype.gameState.movable.MovableElement;
import fr.internship2016.prototype.gameState.movable.bodies.enemies.Troll;

/**
 * Created by bastien on 13/05/16.
 * Very simple level
 * //TODO Review for suitable use
 */
public class Level {

    //region Constants
    public static final float DEFAULT_GRAVITY = -0.047f;
    //endregion

    //region Fields
    private float levelWidth;
    private float levelHeight;
    private float levelGroundHeight;
    private float levelGravity;
    //endregion

    //Background
    private String background;

    public Level(float levelWidth, float levelHeight, float levelGroundHeight, float levelGravity,
                 Array<MovableElement> movableElements) {
        this.levelWidth = levelWidth;
        this.levelHeight = levelHeight;
        this.levelGroundHeight = levelGroundHeight;
        this.levelGravity = levelGravity;
        background = "laser.jpg";

        //Test Troll
        Troll t = new Troll(12f, levelGroundHeight, levelGravity);
        movableElements.add(t);
    }

    //region Modifiers
    public float getLevelWidth() {
        return levelWidth;
    }

    public void setLevelWidth(float levelWidth) {
        this.levelWidth = levelWidth;
    }

    public float getLevelHeight() {
        return levelHeight;
    }

    public void setLevelHeight(float levelHeight) {
        this.levelHeight = levelHeight;
    }

    public float getLevelGroundHeight() {
        return levelGroundHeight;
    }

    public void setLevelGroundHeight(float levelGroundHeight) {
        this.levelGroundHeight = levelGroundHeight;
    }

    public float getLevelGravity() {
        return levelGravity;
    }

    public void setLevelGravity(float levelGravity) {
        this.levelGravity = levelGravity;
    }

    //endregion

    //region Background
    public String getBackground() {
        return background;
    }
    //endregion

    //If level is restarted
    public void reset(Array<MovableElement> movableElements) {
        //COMPLETE
        movableElements.clear();
        Troll t = new Troll(12f, levelGroundHeight, levelGravity);
        movableElements.add(t);
    }
}
