package fr.internship2016.prototype.weapon;

import fr.internship2016.prototype.movable.MovableElement;

/**
 * Created by bastien on 24/04/16.
 * Implementation of weapon
 */
public class Sword extends Weapon {

    public Sword(MovableElement owner, float width, float height) {
        super(owner, width, height);
    }

    @Override
    void update(boolean rightFacing) {
        updateWeaponPos();
        setPosition(baseX, baseY);

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
                if ((getRotation() > maxRotate && rightFacing)
                        || (getRotation() < maxRotate && !rightFacing)) {
                    rotate(rotateAngle);
                } else {
                    attackOver = true;
                }
            } else {
                if (getRotation() == 0)
                    attack = false;
                else
                    rotate(-rotateAngle);
            }
        }
    }

    @Override
    protected void updateWeaponPos() {
        float divideFactor = 12f;
        baseX = owner.getX();
        baseY = owner.getY() + (owner.getH() / divideFactor);
    }


}
