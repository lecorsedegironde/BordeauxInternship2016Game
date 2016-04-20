package fr.internship2016.prototype;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Bastien on 20/04/16.
 * <p>
 * This class concerns the movable elements and all attributes related
 * </p>
 */
public abstract class MovableElement {

    private static float horizontalVelocity = 6.0f;
    private static float verticalVelocity = 4.0f;

    private float velocityX = 0;
    private float velocityY = 0;

    private Rectangle elementRect;
    //Is the element on the ground?
    private boolean onGround;

    public MovableElement(int x, int y, int width, int height, float velocityX, float velocityY) {

        this.elementRect = new Rectangle();

        elementRect.x = x;
        elementRect.y = y;
        elementRect.width = width;
        elementRect.height = height;

        this.onGround = y <= PrototypeGame.GROUND;

        horizontalVelocity = velocityX;
        verticalVelocity = velocityY;
    }

    public void update() {
        elementRect.x += velocityX;
        elementRect.y += velocityY;
        velocityY += PrototypeGame.GRAVITY;

        if (elementRect.y < PrototypeGame.GROUND) {
            velocityY = 0.0f;
            onGround = true;
            elementRect.y = PrototypeGame.GROUND;
        }
    }

    public void moveRight() {
        velocityX = horizontalVelocity;
    }

    public void moveLeft() {
        velocityX = -horizontalVelocity;
    }

    public void jump() {
        if (onGround) {
            velocityY = verticalVelocity;
            onGround = false;
        }
    }

    public void stopMovement() {
        velocityX = 0;
    }

    public float getX() {
        return elementRect.x;
    }

    public void setX(float x) {
        elementRect.x = x;
    }

    public float getY() {
        return elementRect.y;
    }

    public void setY(float y) {
        elementRect.y = y;
    }

    public float getW() {
        return elementRect.width;
    }

    public void setW(float w) {
        elementRect.width = w;
    }

    public float getH() {
        return elementRect.height;
    }

    public void setH(float h) {
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
}
