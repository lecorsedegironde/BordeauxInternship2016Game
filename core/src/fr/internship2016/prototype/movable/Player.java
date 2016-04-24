package fr.internship2016.prototype.movable;

/**
 * Created by bastien on 20/04/16.
 * <p>
 * This class concerns the player and all attributes related
 * </p>
 */
public class Player extends MovableElement {

    private boolean canStopMovement;

    public Player(float x, float y, float width, float height, float velocityX, float velocityY) {
        super(x, y, width, height, velocityX, velocityY);
        canStopMovement = true;
    }

    public boolean canStopMovement() {
        return canStopMovement;
    }

    public void setCanStopMovement(boolean canStopMovement) {
        this.canStopMovement = canStopMovement;
    }
}