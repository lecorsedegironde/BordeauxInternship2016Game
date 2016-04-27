package fr.internship2016.prototype.movable;

import fr.internship2016.prototype.utils.WeaponStyles;
import fr.internship2016.prototype.weapon.Club;
import fr.internship2016.prototype.weapon.Weapon;

import static fr.internship2016.prototype.utils.Constants.*;

/**
 * Created by pacaud on 16/04/25.
 * Troll
 */
public class Troll extends MovableElement {
    private Weapon weapon = null;

    private double numberHitLeft;

    public Troll(float x, float y, float width, float height, float velocityX, float velocityY) {
        super(x, y, width, height, velocityX, velocityY);
        setWeapon(WeaponStyles.CLUB);
        weapon = new Club(this, CLUB_WIDTH, CLUB_HEIGHT);

        rightFacing = false;
        numberHitLeft = HIT_TROLL;
    }

    @Override
    public void update() {
        super.update();
        weapon.update();

        if (elementRect.getX() <= 0) {
            moveRight();
        } else if (elementRect.getX() >= WORLD_WIDTH - getW()) {
            moveLeft();
        }
    }

    public void hitWeapon() {
        numberHitLeft--;
    }

    public void hitSpell(double dmg) {
        numberHitLeft -= dmg;
    }

    public double getNumberHitLeft() {
        return numberHitLeft;
    }

    public Weapon getWeapon() {
        return weapon;
    }


    private void setWeapon(WeaponStyles w) {
        switch (w) {
            case CLUB:
                weapon = new Club(this, CLUB_WIDTH, CLUB_HEIGHT);
                break;
            default:
                weapon = null;
                break;
        }
    }

    public void attack() {
        weapon.attack();
    }
}



