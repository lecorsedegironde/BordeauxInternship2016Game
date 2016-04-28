package fr.internship2016.prototype.weapon.rotate;

import fr.internship2016.prototype.movable.MovableElement;
import fr.internship2016.prototype.weapon.Weapon;

/**
 * Created by bastien on 28/04/16.
 * All weapon that rotate
 */
public abstract class RotatingWeapon extends Weapon{

    protected int maxRotateValue;
    protected int rotateAngleValue;

    public RotatingWeapon(MovableElement owner, float width, float height) {
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
                maxRotate = -maxRotateValue;
                rotateAngle = -rotateAngleValue;
            } else {
                maxRotate = maxRotateValue;
                rotateAngle = rotateAngleValue;
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
        attack = false;
        attackOver = true;
        elementPolygon.setRotation(0);
    }
}
