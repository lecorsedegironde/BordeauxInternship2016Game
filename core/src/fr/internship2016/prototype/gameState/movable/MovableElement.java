package fr.internship2016.prototype.gameState.movable;

import com.badlogic.gdx.math.Rectangle;
import fr.internship2016.prototype.gameState.levels.Level;
import fr.internship2016.prototype.gameState.utils.Direction;

import java.util.Observable;

/**
 * Created by bastien on 13/05/16.
 */
public abstract class MovableElement extends Observable {
    //region fields
    //Body
    protected Rectangle elementRect;

    //Moving
    protected Direction direction;
    protected float velocityX;
    protected float velocityY;
    protected float gravity;
    //endregion

    public MovableElement(float x, float y, float width, float height, float velocityX, float velocityY,
                          float gravity) {

        this.elementRect = new Rectangle();

        elementRect.x = x;
        elementRect.y = y;
        elementRect.width = width;
        elementRect.height = height;

        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.gravity = gravity;

        direction = Direction.NONE;
    }

    public abstract void update(Level level);

    //region setters/getters x, y, w, h, velocity and gravity
    public float getX() {
        return elementRect.getX();
    }

    public float getY() {
        return elementRect.getY();
    }

    public void setPosition(final float x, final float y) {
        elementRect.setPosition(x, y);
    }

    protected void translate(final float x, final float y) {
        elementRect.setX(elementRect.getX() + x);
        elementRect.setY(elementRect.getY() + y);
    }

    public float getW() {
        return elementRect.getWidth();
    }

    public float getH() {
        return elementRect.getHeight();
    }

    public void setSize(final float w, final float h) {
        elementRect.setSize(w, h);
    }

    public float getVelocityX() {
        return velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public void setVelocity(final float velocityX, final float velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public float getGravity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }
    //endregion

    public Rectangle getElementRect() {
        return elementRect;
    }

    //region direction
    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    //endregion
}
