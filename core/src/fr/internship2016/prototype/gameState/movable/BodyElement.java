package fr.internship2016.prototype.gameState.movable;

import fr.internship2016.prototype.gameState.levels.Level;
import fr.internship2016.prototype.gameState.movable.interfaces.Facing;
import fr.internship2016.prototype.gameState.movable.interfaces.Hit;
import fr.internship2016.prototype.gameState.movable.interfaces.KnockBack;
import fr.internship2016.prototype.gameState.utils.Direction;


/**
 * Created by bastien on 13/05/16.
 */
public abstract class BodyElement extends MovableElement implements Facing, Hit, KnockBack {

    protected boolean onGround;
    protected Direction facing;

    public BodyElement(float x, float y, float width, float height, float velocityX,
                       float velocityY, float gravity) {
        super(x, y, width, height, velocityX, velocityY, gravity);
        facing = (velocityX > 0) ? Direction.RIGHT : Direction.LEFT;

    }

    @Override
    public void update(Level level) {
        float moveX = 0, moveY = 0;
        switch (direction) {
            case RIGHT:
                moveX = velocityX;
                break;
            case LEFT:
                moveX = -velocityX;
                break;
        }

        if (!onGround) {
            moveY += gravity;
        }

        translate(moveX, moveY);

        //Check if Body is on the ground
        checkOnGround(level);

        //Check if the body is out of bounds
        checkBounds(level);

        setChanged();
        notifyObservers();
    }

    private void checkBounds(Level level) {
        //Left and Right
        if (getX() <= 0) {
            stopMovement();
            setPosition(0, getY());
        } else if (elementRect.getX() >= level.getLevelWidth() - getW()) {
            stopMovement();
            setPosition(level.getLevelWidth() - getW(), getY());
        }
    }

    public void moveRight() {
        direction = Direction.RIGHT;
    }

    public void moveLeft() {
        direction = Direction.LEFT;
    }

    private void stopMovement() {
        if (onGround) direction = Direction.NONE;
    }

    private void checkOnGround(Level level) {
        onGround = getY() <= level.getLevelGroundHeight();

        if (onGround) {
            setPosition(getX(), level.getLevelGroundHeight());
        }
    }

    public boolean isOnGround() {
        return onGround;
    }

    @Override
    public void setFacing(Direction d) {
        facing = d;
    }

    @Override
    public Direction getFacing() {
        return facing;
    }
}
