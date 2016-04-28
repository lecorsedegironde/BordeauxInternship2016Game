package fr.internship2016.prototype.weapon.rotate;

import fr.internship2016.prototype.movable.MovableElement;

import static fr.internship2016.prototype.utils.Constants.CLUB_MAX_ROTATE;
import static fr.internship2016.prototype.utils.Constants.CLUB_ROTATE_STEP;

/**
 * Created by pacaud on 16/04/26.
 * A troll weapon
 */
public class Club extends RotatingWeapon {

    public Club(MovableElement owner, float width, float height) {
        super(owner, width, height);
        maxRotateValue = CLUB_MAX_ROTATE;
        rotateAngleValue = CLUB_ROTATE_STEP;
    }
}


