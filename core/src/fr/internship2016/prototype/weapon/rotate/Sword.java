package fr.internship2016.prototype.weapon.rotate;

import fr.internship2016.prototype.movable.MovableElement;

import static fr.internship2016.prototype.utils.Constants.SWORD_MAX_ROTATE;
import static fr.internship2016.prototype.utils.Constants.SWORD_ROTATE_STEP;

/**
 * Created by bastien on 24/04/16.
 * Implementation of weapon
 */
public class Sword extends RotatingWeapon {

    public Sword(MovableElement owner, float width, float height) {
        super(owner, width, height);
        maxRotateValue = SWORD_MAX_ROTATE;
        rotateAngleValue = SWORD_ROTATE_STEP;
    }
}
