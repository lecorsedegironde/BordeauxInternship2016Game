package fr.internship2016.prototype.weapon;

import com.badlogic.gdx.math.Polygon;

/**
 * Created by bastien on 24/04/16.
 * Base weapon class
 */
public abstract class Weapon {

    private Polygon elementPolygon;
    private float baseWidth, baseHeight;


    public Weapon(float x, float y, float width, float height) {
        this.baseWidth = width;
        this.baseHeight = height;
        this.elementPolygon = new Polygon(new float[]{
                x, y,
                x + width, y,
                x + width, y + height,
                x, y + height
        });

        this.elementPolygon.setOrigin(x + width / 2, y);
        this.elementPolygon.setPosition(x,y);
    }

    public float getX() {
        return elementPolygon.getX();
    }

    public float getY() {
        return elementPolygon.getY();
    }

    public void setPosition(float x, float y) {
        elementPolygon.setPosition(x, y);
    }

    public float getWidth() {
        return baseWidth;
    }

    public float getHeight() {
        return baseHeight;
    }

    public void rotate(float degrees) {
        elementPolygon.rotate(degrees);
    }

    public float getRotation() {
        return elementPolygon.getRotation();
    }

    public float[] getTransformedVertices() {
        return elementPolygon.getTransformedVertices();
    }
}
