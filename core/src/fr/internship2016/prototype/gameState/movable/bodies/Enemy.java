package fr.internship2016.prototype.gameState.movable.bodies;

/**
 * Created by bastien on 13/05/16.
 */
public abstract class Enemy extends BodyElement {

    public Enemy(float x, float y, float width, float height, float velocityX, float velocityY, float gravity) {
        super(x, y, width, height, velocityX, velocityY, gravity);
    }
    //COMPLETE
}
