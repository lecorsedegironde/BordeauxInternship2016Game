package fr.internship2016.prototype.weapon;

import com.badlogic.gdx.utils.TimeUtils;
import fr.internship2016.prototype.movable.MovableElement;
import fr.internship2016.prototype.utils.Constants;

/**
 * Created by pacaud on 16/04/26.
 */
public class Club extends Weapon {
    public Club(MovableElement owner, float width, float height) {
        super(owner, width, height);
    }

    @Override
    public void update() {
        updateWeaponPos();
        setPosition(baseX, baseY);
        if (attack) {
            int maxRotate, rotateAngle;
            if (owner.isRightFacing()) {
                maxRotate = -100;
                rotateAngle = -5;
            } else {
                maxRotate = 100;
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


    }


