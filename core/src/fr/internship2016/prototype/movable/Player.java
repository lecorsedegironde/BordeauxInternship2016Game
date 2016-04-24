package fr.internship2016.prototype.movable;

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

    public Player(float x, float y, float width, float height, float velocityX, float velocityY, boolean createWeapon) {
        super(x, y, width, height, velocityX, velocityY);
        canStopMovement = true;

        if (createWeapon)
            setWeapon(WeaponStyles.SWORD);
    }

    @Override
    public void update() {
        weapon.setX(weapon.getX() + velocityX);
        weapon.setY(weapon.getY() + velocityY);
        super.update();

        //On the ground
        if (onGround) {
            weapon.setY(baseYWeapon);
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
                baseXWeapon = getX() + getW() / 1.5f;
                baseYWeapon = getY() + getH() / 1.5f;
                weapon = new Sword(baseXWeapon, baseYWeapon,
                        SWORD_WIDTH, SWORD_HEIGHT, SWORD_ANGULAR_VELOCITY);
                break;
            default:
                weapon = null;
                break;
        }
    }

    public boolean hasWeapon() {
        return weapon != null ? true : false;
    }
}