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

        updateSwordDefaultPos();

        weapon.setX(weapon.getX() + velocityX);
        weapon.setY(weapon.getY() + velocityY);

        super.update();

        //On the ground
        if (onGround) {
            weapon.setY(baseYWeapon);
        }

        //Left and Right
        if (elementRect.getX() <= 0) {
            updateSwordDefaultPos();
            weapon.setX(baseXWeapon);
            weapon.setY(baseYWeapon);
        } else if (elementRect.getX() >= WORLD_WIDTH - getW()) {
            updateSwordDefaultPos();
            weapon.setX(baseXWeapon);
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
                updateSwordDefaultPos();
                weapon = new Sword(baseXWeapon, baseYWeapon,
                        SWORD_WIDTH, SWORD_HEIGHT, SWORD_ANGULAR_VELOCITY);
                break;
            default:
                weapon = null;
                break;
        }
    }

    private void updateSwordDefaultPos() {
        baseXWeapon = getX() + getW() / 1.5f;
        baseYWeapon = getY() + getH() / 1.5f;
    }

    public boolean hasWeapon() {
        return weapon != null ? true : false;
    }

    public void attack() {
        float saveX = weapon.getX();
        float saveW = weapon.getWidth();
        weapon.setX(weapon.getY());
        weapon.setY(saveX);
        weapon.setWidth(weapon.getHeight());
        weapon.setHeight(saveW);
    }
}