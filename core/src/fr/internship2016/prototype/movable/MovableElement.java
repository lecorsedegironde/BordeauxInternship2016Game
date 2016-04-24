package fr.internship2016.prototype.movable;

import com.badlogic.gdx.math.Rectangle;

import static fr.internship2016.prototype.utils.Constants.*;

/**
 * Created by Bastien on 20/04/16.
 * <p>
 * This class concerns the movable elements and all attributes related
 * </p>
 */
public abstract class MovableElement {

    private static float horizontalVelocity;
    private static float verticalVelocity;

    private float velocityX = 0;
    private float velocityY = 0;

    private Rectangle elementRect;
    //Is the element on the ground?
    private boolean onGround;

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

    public void update() {
        elementRect.x += velocityX;
        elementRect.y += velocityY;
        velocityY += GRAVITY;

        //On the ground
        if (elementRect.y < GROUND_HEIGHT) {
            velocityY = 0.0f;
            onGround = true;
            elementRect.y = GROUND_HEIGHT;
        }

        //Left and Right
        if (elementRect.getX() <= 0) {
            stopMovement();
            elementRect.setX(0);
        } else if (elementRect.getX() >= WORLD_WIDTH) {
            stopMovement();
            elementRect.setX(WORLD_WIDTH - WIDTH_PLAYER);
        }
    }

    public void moveRight() {
        velocityX = horizontalVelocity;

        if (elementRect.getX() >= WORLD_WIDTH - WIDTH_PLAYER) {
            stopMovement();
            elementRect.setX(WORLD_WIDTH - WIDTH_PLAYER);
        }
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
        if (onGround)
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