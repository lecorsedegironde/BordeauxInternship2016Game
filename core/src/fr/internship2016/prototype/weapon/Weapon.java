package fr.internship2016.prototype.weapon;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.TimeUtils;
import fr.internship2016.prototype.movable.MovableElement;
import fr.internship2016.prototype.utils.Constants;

/**
 * Created by bastien on 24/04/16.
 * Base weapon class
 */
public abstract class Weapon {

    //Base elements
    protected Polygon elementPolygon;
    protected float baseX, baseY, baseWidth, baseHeight;

    //Owner
    protected MovableElement owner;

    //Attack
    protected boolean attack;
    protected boolean hasHit;
    protected boolean attackOver;
    protected long lastAttack;


    public Weapon(MovableElement owner, float width, float height) {
        //Owner
        this.owner = owner;

        //Positions
        this.baseWidth = width;
        this.baseHeight = height;
        updateWeaponPos();
        this.elementPolygon = new Polygon(new float[]{
                0, 0,
                baseWidth, 0,
                baseWidth, baseHeight,
                0, baseHeight
        });
        this.elementPolygon.setOrigin(baseWidth / 2, 0.1f);

        //Attack part
        attack = false;
        attackOver = true;
        hasHit = false;
    }

    public abstract void update();

    protected abstract void updateWeaponPos();

    public boolean isAttack() {
        return attack;
    }

    public void attack() {
        long timeSinceAttack = TimeUtils.timeSinceMillis(lastAttack);
        if (!attack && timeSinceAttack > Constants.SWORD_REFILL_TIME) {
            attack = true;
            attackOver = false;
            hasHit = false;
            lastAttack = TimeUtils.millis();
        }
    }

    public abstract void attackForceStop();

    public boolean hasHit() {
        return hasHit;
    }

    public void hit() {
        hasHit = true;
    }

    protected void rotate(float degrees) {
        elementPolygon.rotate(degrees);
    }

    protected float getRotation() {
        return elementPolygon.getRotation();
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

    public float[] getTransformedVertices() {
        return elementPolygon.getTransformedVertices();
    }

    public MovableElement getOwner() {
        return owner;
    }

    //For collision detection
    public Polygon getElementPolygon() {
        return elementPolygon;
    }
}
