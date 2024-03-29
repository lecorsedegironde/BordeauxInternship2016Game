package fr.internship2016.prototype.gameState.weapons;

import fr.internship2016.prototype.gameState.movable.bodies.BodyElement;
import fr.internship2016.prototype.gameState.utils.Direction;

/**
 * Created by bastien on 17/05/16.
 */
public class RotatingWeapon extends Weapon {

    private int maxRotateValue;

    RotatingWeapon(BodyElement owner, WeaponType type) {
        super(owner, type);
        maxRotateValue = (int) type.getMaxPos();
        updateCpt = 0;
        numberOfUpdates = (int) (type.getMaxPos() / type.getIncrements());
    }

    @Override
    public void update() {
        //Update weapon position
        updatePos();

        //Attack part
        /*
         * Reminder:
         * if attack = true -> start of attack
         * if attack = false -> end of animation, weapon in original position
         * if attackOver = true -> attack is over (finished or return phase)
         * if attackOver = false -> attack doesn't finish first phase nor have hit something
         */
        if (attack) {
            //Get max rotation
            int rotateModifier = getRotateModifier();

            //First attack phase
            if (!attackOver) {
                if (updateCpt <= numberOfUpdates) {
                    //Divide angle by the number of updates dedicated to the animation
                    int maxRotateValueUpdated = (maxRotateValue) * rotateModifier;
                    int rotation = (maxRotateValueUpdated / numberOfUpdates) * updateCpt;
                    elementPolygon.setRotation(rotation);
                    updateCpt++;
                } else {
                    //First phase is over
                    attackOver = true;
                }
            } else {
                //Second phase -> back to original position
                if (updateCpt >= 0) {
                    int maxRotateValueUpdated = (maxRotateValue) * rotateModifier;
                    int rotation = (maxRotateValueUpdated / numberOfUpdates) * updateCpt;
                    elementPolygon.setRotation(rotation);
                    updateCpt--;
                } else {
                    hasHit = false;
                    attack = false;
                }
            }
        }
    }

    private int getRotateModifier() {
        int rotateModifier = 0;
        if (owner.getFacing() == Direction.RIGHT) {
            rotateModifier = -1;
        } else if (owner.getFacing() == Direction.LEFT) {
            rotateModifier = 1;
        }
        return rotateModifier;
    }

    @Override
    protected void updatePos() {
        float divideFactor = 1.5f;
        float divide = owner.getW() / divideFactor;
        if (owner.getFacing() == Direction.LEFT) {
            divide /= 2f;
            divide -= widthWeapon;
        }
        xWeapon = owner.getX() + divide;
        yWeapon = owner.getY() + (owner.getH() / divideFactor);
        setPosition(xWeapon, yWeapon);
    }
}
