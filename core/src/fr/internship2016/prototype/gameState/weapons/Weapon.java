package fr.internship2016.prototype.gameState.weapons;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.TimeUtils;
import fr.internship2016.prototype.gameState.movable.bodies.BodyElement;

/**
 * Created by bastien on 17/05/16.
 */
public abstract class Weapon {

    //Base elements
    protected Polygon elementPolygon;
    protected float xWeapon, yWeapon, widthWeapon, heightWeapon;

    //Anim & stuff
    protected int updateCpt;
    protected int numberOfUpdates;

    //Owner & desc
    protected BodyElement owner;
    private WeaponType type;

    //Attack
    protected boolean attack;
    protected boolean hasHit;
    protected boolean attackOver;
    private long lastAttack;
    private float refillTime;

    public Weapon(BodyElement owner, WeaponType type) {
        //Owner
        this.owner = owner;
        this.type = type;

        //Positions
        widthWeapon = type.getWidth();
        heightWeapon = type.getHeight();

        this.elementPolygon = new Polygon(new float[]{
                0, 0,
                widthWeapon, 0,
                widthWeapon, heightWeapon,
                0, heightWeapon
        });
        //Update pos after declaring the polygon
        updatePos();
        //Modify value for needed weapons
        this.elementPolygon.setOrigin(widthWeapon / 2, 0.1f);

        //Attack
        refillTime = type.getRefillTime();
        attack = false;
        attackOver = false;
        hasHit = false;
    }

    //region Update
    public abstract void update();

    protected abstract void updatePos();
    //endregion

    //region Attack
    public boolean isAttack() {
        return attack;
    }

    public void attack() {
        long timeSinceAttack = TimeUtils.timeSinceMillis(lastAttack);
        if (!attack && timeSinceAttack > refillTime) {
            attack = true;
            attackOver = false;
            hasHit = false;
            lastAttack = TimeUtils.millis();
        }
    }

    public void stopAttack() {
        attackOver = true;
    }

    public boolean hasHit() {
        return hasHit;
    }

    public void hit() {
        hasHit = true;
    }
    //endregion

    //region Modifiers
    public float getX() {
        return elementPolygon.getX();
    }

    public float getY() {
        return elementPolygon.getY();
    }

    protected void setPosition(float x, float y) {
        elementPolygon.setPosition(x, y);
    }

    public float getWidth() {
        return widthWeapon;
    }

    public float getHeight() {
        return heightWeapon;
    }

    public BodyElement getOwner() {
        return owner;
    }

    public WeaponType getType() {
        return type;
    }

    //For drawing & collision detection
    public Polygon getElementPolygon() {
        return elementPolygon;
    }
    //endregion
}
