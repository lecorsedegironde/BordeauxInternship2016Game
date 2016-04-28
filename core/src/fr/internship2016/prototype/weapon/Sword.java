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
    public void update() {
        updateWeaponPos();
        setPosition(baseX, baseY);

        //Attack
        if (attack) {
            int maxRotate, rotateAngle;
            if (owner.isRightFacing()) {
                maxRotate = -90;
                rotateAngle = -5;
            } else {
                maxRotate = 90;
                rotateAngle = 5;
            }
            if (!attackOver) {
                if ((getRotation() > maxRotate && owner.isRightFacing())
                        || (getRotation() < maxRotate && !owner.isRightFacing())) {
                    rotate(rotateAngle);
                } else {
                    attackOver = true;
                }
            } else {
                if (getRotation() == 0) {
                    attack = false;
                    hasHit = false;
                } else
                    rotate(-rotateAngle);
            }
        }
    }

    @Override
    protected void updateWeaponPos() {
        float divideFactor = 1.5f;
        float divide = owner.getW() / divideFactor;
        if (!owner.isRightFacing()) {
            divide /= 2f;
            divide -= baseWidth;
        }
        baseX = owner.getX() + divide;
        baseY = owner.getY() + (owner.getH() / divideFactor);
    }

    @Override
    public void attackForceStop() {
        //TODO Unimplemented method
    }


}
