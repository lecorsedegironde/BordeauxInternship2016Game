package fr.internship2016.prototype.movable;

import com.badlogic.gdx.math.Rectangle;

import static fr.internship2016.prototype.utils.Constants.GROUND_HEIGHT;

/**
 * Created by Bastien on 20/04/16.
 * <p>
 * This class concerns the movable elements and all attributes related
 * </p>
 */
public abstract class MovableElement {

    protected float horizontalVelocity;
    protected float verticalVelocity;
    protected float velocityX = 0;
    protected float velocityY = 0;
    protected Rectangle elementRect;
    //Is the element on the ground?
    protected boolean onGround;
    protected boolean rightFacing;

    public MovableElement(float x, float y, float width, float height, float velocityX, float velocityY) {

        this.elementRect = new Rectangle();

        elementRect.x = x;
        elementRect.y = y;
        elementRect.width = width;
        elementRect.height = height;

        this.onGround = y <= GROUND_HEIGHT;

        horizontalVelocity = velocityX;
        verticalVelocity = velocityY;
    }

    public abstract void update();

    public void moveRight() {
        velocityX = horizontalVelocity;
        rightFacing = true;
    }

    public void moveLeft() {
        velocityX = -horizontalVelocity;
        rightFacing = false;
    }

    public void jump() {
        if (onGround) {
            velocityY = verticalVelocity;
            onGround = false;
        }
    }

    public void stopMovement() {
        if (onGround)
            velocityX = 0;
    }

    public Rectangle getRectangle() {
        return elementRect;
    }

    public float getX() {
        return elementRect.x;
    }

    public float getY() {
        return elementRect.y;
    }

    public void setPosition(float x, float y) {
        elementRect.x = x;
        elementRect.y = y;
    }

    public float getW() {
        return elementRect.width;
    }

    public float getH() {
        return elementRect.height;
    }

    public void setSize(float w, float h) {
        elementRect.width = w;
        elementRect.height = h;
    }

    public float getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }

    public boolean isRightFacing() {
        return rightFacing;
    }
}