package fr.internship2016.prototype.gameState.movable.bodies;

import fr.internship2016.prototype.gameState.levels.Level;
import fr.internship2016.prototype.gameState.movable.MovableElement;
import fr.internship2016.prototype.gameState.movable.interfaces.Facing;
import fr.internship2016.prototype.gameState.movable.interfaces.Hit;
import fr.internship2016.prototype.gameState.movable.interfaces.Jump;
import fr.internship2016.prototype.gameState.movable.interfaces.KnockBack;
import fr.internship2016.prototype.gameState.utils.Direction;


/**
 * Created by bastien on 13/05/16.
 */
public abstract class BodyElement extends MovableElement implements Facing, Hit, KnockBack, Jump {

    //Jump & ground management
    protected boolean onGround;
    protected boolean jumping;
    protected float jumpingVelocity;

    //Knockback Management
    protected boolean knockback;
    protected Direction knockbackDirection;
    protected float knockbackXVelocity;
    protected float knockbackYVelocity;
    protected float horizontalVelocity;

    protected Direction facing;


    public BodyElement(float x, float y, float width, float height, float velocityX, float velocityY, float gravity) {
        super(x, y, width, height, velocityX, velocityY, gravity);
        facing = (velocityX > 0) ? Direction.RIGHT : Direction.LEFT;
        jumpingVelocity = 0f;
        horizontalVelocity = 0f;
        knockbackDirection = Direction.NONE;
        knockbackXVelocity = 0f;
        knockbackYVelocity = 0f;
    }

    @Override
    public void update(Level level) {
        float moveX = 0, moveY;
        switch (direction) {
            case RIGHT:
                moveX = velocityX;
                setFacing(Direction.RIGHT);
                break;
            case LEFT:
                moveX = -velocityX;
                setFacing(Direction.LEFT);
                break;
        }

        if (jumping && onGround) {
            jumpingVelocity = velocityY;
        }

        if (!onGround) {
            jumpingVelocity += gravity;
        }

        if (knockback) {
            jumpingVelocity = knockbackYVelocity;
            int directionModifier = 0;
            switch (knockbackDirection) {
                case LEFT:
                    directionModifier = -1;
                    break;
                case RIGHT:
                    directionModifier = 1;
                    break;
            }


            horizontalVelocity = knockbackXVelocity * directionModifier;
            onGround = false;
            knockback = false;
        }

        if ((moveX > 0f && horizontalVelocity > 0f) || (moveX < 0f && horizontalVelocity < 0f)) {
            horizontalVelocity -= moveX;
        }

        moveX += horizontalVelocity;
        moveY = jumpingVelocity;

        translate(moveX, moveY);
        //Check if Body is on the ground
        checkOnGround(level);

        //Update horizontalVelocity
        if (Math.abs(horizontalVelocity) >= velocityX) {
            if (horizontalVelocity >= velocityX) {
                horizontalVelocity -= velocityX;
            } else if (horizontalVelocity <= -velocityX) {
                horizontalVelocity += velocityX;
            }
        } else {
            horizontalVelocity = 0f;
        }

        //Check if the body is out of bounds
        checkBounds(level);

        setChanged();
        notifyObservers();
    }

    //region Movement
    protected void checkBounds(Level level) {
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

    public void stopMovement() {
        if (onGround) direction = Direction.NONE;
    }
    //endregion

    //region Jump & Ground
    protected void checkOnGround(Level level) {
        onGround = getY() <= level.getLevelGroundHeight();

        if (onGround) {
            setPosition(getX(), level.getLevelGroundHeight());
            jumping = false;
            jumpingVelocity = 0f;
        }
    }

    public boolean isOnGround() {
        return onGround;
    }

    public boolean isJumping() {
        return jumping;
    }

    @Override
    public void jump() {
        if (onGround) {
            jumping = true;
        }
    }
    //endregion

    //region Facing
    @Override
    public void setFacing(Direction d) {
        facing = d;
    }

    @Override
    public Direction getFacing() {
        return facing;
    }
    //endregion

    //region Hit
    @Override
    public void hit(double dmg) {
        //Do nothing
    }
    //endregion

    //region Knock-back
    @Override
    public void knockBack(Direction knockbackDirection) {
        knockback = true;
        this.knockbackDirection = knockbackDirection;
    }

    public boolean isKnockback() {
        return knockback;
    }
    //endregion
}
