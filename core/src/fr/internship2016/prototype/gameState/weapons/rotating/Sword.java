package fr.internship2016.prototype.gameState.weapons.rotating;

import fr.internship2016.prototype.gameState.movable.bodies.BodyElement;
import fr.internship2016.prototype.gameState.weapons.WeaponType;

/**
 * Created by bastien on 17/05/16.
 */
public class Sword extends RotatingWeapon {

    //region Constants
    private static final int MAX_ROTATE = 90;
    private static final int WANTED_ROTATE_ANGLE = 5;
    //endregion

    public Sword(BodyElement owner, WeaponType type) {
        super(owner, type);

        defaultRotation = 0;
        maxRotateValue = MAX_ROTATE;
        updateCpt = 0;
        numberOfUpdates = MAX_ROTATE / WANTED_ROTATE_ANGLE;
    }
}
