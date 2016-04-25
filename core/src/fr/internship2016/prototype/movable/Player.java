package fr.internship2016.prototype.movable;

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

    //TODO: Move in weapon
    private boolean attack;
    private boolean attackOver;
    private long lastAttack;

    public Player(float x, float y, float width, float height, float velocityX, float velocityY, boolean createWeapon) {
        super(x, y, width, height, velocityX, velocityY);
        canStopMovement = true;
        attack = false;
        attackOver = false;

        if (createWeapon)
            setWeapon(WeaponStyles.SWORD);
    }

    @Override
    public void update() {

        updateSwordDefaultPos();

        weapon.setPosition(weapon.getX() + velocityX, weapon.getY() + velocityY);

        super.update();

        //On the ground
        if (onGround) {
            weapon.setPosition(weapon.getX(), baseYWeapon);
        }

        //Left and Right
        if (elementRect.getX() <= 0 || elementRect.getX() >= WORLD_WIDTH - getW()) {
            updateSwordDefaultPos();
            weapon.setPosition(baseXWeapon, baseYWeapon);
        }

        //Attack
        if (attack) {
            if (!attackOver) {
                if (weapon.getRotation() > -90) {
                    weapon.rotate(-5);
                } else {
                    attackOver = true;
                }
            } else {
                if (weapon.getRotation() == 0)
                    attack = false;
                else
                    weapon.rotate(5);
            }
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
        baseXWeapon = getX() + (getW() / 12f);
        baseYWeapon = getY() + (getH() / 12f);
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
}