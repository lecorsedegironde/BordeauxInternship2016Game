package fr.internship2016.prototype.weapon;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by bastien on 24/04/16.
 * Base weapon class
 */
public abstract class Weapon {

    private Rectangle elementRect = new Rectangle();

    public Weapon(float x, float y, float width, float height) {
        this.elementRect.setX(x);
        this.elementRect.setY(y);
        this.elementRect.setWidth(width);
        this.elementRect.setHeight(height);
    }

    public float getX() {
        return elementRect.getX();
    }

    public void setX(float x) {
        elementRect.setX(x);
    }
    public float getY() {
        return elementRect.getY();
    }

    public void setY(float y) {
        elementRect.setY(y);
    }
    public float getWidth() {
        return elementRect.getWidth();
    }

    public void setWidth(float width) {
        elementRect.setWidth(width);
    }
    public float getHeight() {
        return elementRect.getHeight();
    }

    public void setHeight(float height) {
        elementRect.setHeight(height);
    }
}
