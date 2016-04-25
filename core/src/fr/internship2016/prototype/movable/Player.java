package fr.internship2016.prototype.movable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;
import fr.internship2016.prototype.utils.WeaponStyles;
import fr.internship2016.prototype.weapon.Sword;
import fr.internship2016.prototype.weapon.Weapon;

import static fr.internship2016.prototype.utils.Constants.*;

/**
 * Created by bastien on 20/04/16.
 * <p>
 * This class concerns the player and all attributes related
 * </p>
 */
public class Player extends MovableElement {

    //Player can have a weapon
    private Weapon weapon = null;

    private boolean canStopMovement;
    private float baseXWeapon = 0f;
    private float baseYWeapon = 0f;

    private boolean invisible;
    private boolean canBeInvisible;
    private long invisibilityTime;

    //TODO: Move in weapon
    private boolean attack;
    private boolean attackOver;
    private long lastAttack;

    public Player(float x, float y, float width, float height, float velocityX, float velocityY, boolean createWeapon) {
        super(x, y, width, height, velocityX, velocityY);
        canStopMovement = true;
        rightFacing = true;
        attack = false;
        attackOver = false;

        if (createWeapon)
            setWeapon(WeaponStyles.SWORD);

        invisible = false;
        canBeInvisible = true;
    }

    @Override
    public void update() {



        super.update();
        updateSwordDefaultPos();
        weapon.setPosition(baseXWeapon, baseYWeapon);
//        Gdx.app.debug("PLAYER POS", "x: " + getX() + " y: " + getY());


        //Attack
        if (attack) {
            int maxRotate, rotateAngle;
            if (rightFacing) {
                maxRotate = -90;
                rotateAngle = -5;
            } else {
                maxRotate = 90;
                rotateAngle = 5;
            }
            if (!attackOver) {
                if ((weapon.getRotation() > maxRotate && rightFacing)
                        || (weapon.getRotation() < maxRotate && !rightFacing)) {
                    weapon.rotate(rotateAngle);
                } else {
                    attackOver = true;
                }
            } else {
                if (weapon.getRotation() == 0)
                    attack = false;
                else
                    weapon.rotate(-rotateAngle);
            }
        }

        //Invisibility
        if (invisible && TimeUtils.timeSinceMillis(invisibilityTime) > INVISIBILITY_DURATION) {
            invisible = false;
            invisibilityTime = TimeUtils.millis();
        }
        if (TimeUtils.timeSinceMillis(invisibilityTime) > INVISIBILITY_REFILL) {
            canBeInvisible = true;
        }
    }

    @Override
    public void moveRight() {
        if (!attack) {
            super.moveRight();
        }
    }

    @Override
    public void moveLeft() {
        if (!attack) {
            super.moveLeft();
        }
    }

    public boolean canStopMovement() {
        return canStopMovement;
    }

    public void setCanStopMovement(boolean canStopMovement) {
        this.canStopMovement = canStopMovement;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(WeaponStyles w) {
        switch (w) {
            case SWORD:
                updateSwordDefaultPos();
                weapon = new Sword(baseXWeapon, baseYWeapon, SWORD_WIDTH, SWORD_HEIGHT);
                break;
            default:
                weapon = null;
                break;
        }
    }

    private void updateSwordDefaultPos() {
        float divideFactor = 12f;
        baseXWeapon = getX();
        baseYWeapon = getY() + (getH() / divideFactor);
//        Gdx.app.debug("SWORD_POS", "x: " + baseXWeapon + " y: " + baseYWeapon);
    }

    public boolean hasWeapon() {
        return weapon != null;
    }

    public void attack() {
        long timeSinceAttack = TimeUtils.timeSinceMillis(lastAttack);
        if (!attack && timeSinceAttack > 750) {
            attack = true;
            attackOver = false;
            lastAttack = TimeUtils.millis();
        }
    }

    public boolean isInvisible() {
        return invisible;
    }

    public void startInvisibility() {
        if (!invisible && canBeInvisible) {
            invisible = true;
            canBeInvisible = false;
            invisibilityTime = TimeUtils.millis();
        }
    }

    public boolean canBeInvisible() {
        return canBeInvisible;
    }
}