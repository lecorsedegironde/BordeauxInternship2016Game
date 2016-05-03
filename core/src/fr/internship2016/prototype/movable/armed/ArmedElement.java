package fr.internship2016.prototype.movable.armed;

import fr.internship2016.prototype.movable.MovableElement;
import fr.internship2016.prototype.weapon.Weapon;
import fr.internship2016.prototype.weapon.WeaponStyles;

import static fr.internship2016.prototype.utils.Constants.*;

/**
 * Created by bastien on 28/04/16.
 * This class concern Element that have a weapon
 */
public abstract class ArmedElement extends MovableElement {
    //Weapon system
    protected Weapon weapon = null;

    public ArmedElement(float x, float y, float width, float height, float velocityX, float velocityY) {
        super(x, y, width, height, velocityX, velocityY);
        weapon = null;
    }

    @Override
    public void update() {
        elementRect.x += velocityX;
        elementRect.y += velocityY;
        velocityY += GRAVITY;

        setChanged();
        notifyObservers();

        //On the ground
        if (elementRect.y <= GROUND_HEIGHT) {
            velocityY = 0.0f;
            onGround = true;
            elementRect.y = GROUND_HEIGHT;
        }

        //Left and Right
        if (elementRect.getX() <= 0) {
            stopMovement();
            elementRect.setX(0);
            setChanged();
            notifyObservers();
        } else if (elementRect.getX() >= WORLD_WIDTH - getW()) {
            stopMovement();
            elementRect.setX(WORLD_WIDTH - getW());
            setChanged();
            notifyObservers();
        }

        weapon.update();
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

    public abstract double getLife();

    public abstract void hitWeapon();

    public abstract void hitSpell(double spellDmg);

    public boolean hasWeapon() {
        return weapon != null;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public abstract void setWeapon(WeaponStyles w);

    public boolean isAttacking() {
        return weapon.isAttack();
    }

    public void attack() {
        weapon.attack();
    }

    public void stopAttack() {
        weapon.attackForceStop();
    }
}
