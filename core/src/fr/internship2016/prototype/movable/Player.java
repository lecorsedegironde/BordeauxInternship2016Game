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

    private boolean invisible;
    private boolean canBeInvisible;
    private long invisibilityTime;

    //TODO: Move in weapon


    public Player(float x, float y, float width, float height, float velocityX, float velocityY, boolean createWeapon) {
        super(x, y, width, height, velocityX, velocityY);
        canStopMovement = true;
        rightFacing = true;

        if (createWeapon)
            setWeapon(WeaponStyles.SWORD);

        invisible = false;
        canBeInvisible = true;
    }

    @Override
    public void update() {

        super.update();
        weapon.update();

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
        if (!weapon.isAttack()) {
            super.moveRight();
        }
    }

    @Override
    public void moveLeft() {
        if (!weapon.isAttack()) {
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
                weapon = new Sword(this, SWORD_WIDTH, SWORD_HEIGHT);
                break;
            default:
                weapon = null;
                break;
        }
    }

    public boolean hasWeapon() {
        return weapon != null;
    }

    public void attack() {
        weapon.attack();
    }

    public boolean isInvisible() {
        return invisible;
    }

    public boolean isAttacking() {
        return weapon.isAttack();
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