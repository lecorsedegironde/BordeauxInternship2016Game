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

    protected Polygon elementPolygon;
    protected float baseX, baseY, baseWidth, baseHeight;

    //Owner
    protected MovableElement owner;

    //Attack
    protected boolean attack;
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
                baseX, baseY,
                baseX + baseWidth, baseY,
                baseX + baseWidth, baseY + baseHeight,
                baseX, baseY + baseHeight
        });

        this.elementPolygon.setOrigin(baseX + baseWidth / 2, baseY);
        this.elementPolygon.setPosition(baseX,baseY);


        //Attack part
        attack = false;
        attackOver = false;
    }

    abstract void update(boolean rightFacing);

    protected abstract void updateWeaponPos();

    public boolean isAttack() {
        return attack;
    }

    public void attack() {
        long timeSinceAttack = TimeUtils.timeSinceMillis(lastAttack);
        if (!attack && timeSinceAttack > Constants.SWORD_REFILL_TIME) {
            attack = true;
            attackOver = false;
            lastAttack = TimeUtils.millis();
        }
    }

    public float getX() {
        return elementPolygon.getX();
    }

    public float getY() {
        return elementPolygon.getY();
    }

    public float getWidth() {
        return baseWidth;
    }

    public float getHeight() {
        return baseHeight;
    }

    public void setPosition(float x, float y) {
        elementPolygon.setPosition(x, y);
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

    public MovableElement getOwner() {
        return owner;
    }
}
