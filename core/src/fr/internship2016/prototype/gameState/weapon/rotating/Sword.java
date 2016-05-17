package fr.internship2016.prototype.gameState.weapon.rotating;

import fr.internship2016.prototype.gameState.movable.bodies.BodyElement;
import fr.internship2016.prototype.gameState.weapon.WeaponType;

/**
 * Created by bastien on 17/05/16.
 */
public class Sword extends RotatingWeapon {

    //region Constants
    private static final int MAX_ROTATE = 90;
    //endregion

    public Sword(BodyElement owner, WeaponType type) {
        super(owner, type);

        defaultRotation = 0;
        maxRotateValue = MAX_ROTATE;
        updateCpt = 0;
        numberOfUpdates = 18;
    }
}
