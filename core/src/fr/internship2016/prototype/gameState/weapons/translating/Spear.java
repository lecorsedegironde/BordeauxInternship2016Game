package fr.internship2016.prototype.gameState.weapons.translating;

import fr.internship2016.prototype.gameState.movable.bodies.BodyElement;
import fr.internship2016.prototype.gameState.weapons.WeaponType;

/**
 * Created by bastien on 18/05/16.
 */
public class Spear extends TranslatingWeapon {

    //region Constants
    private static final float MAX_TRANSLATION = 0.5f;
    private static final float INCREMENT_TRANSLATION = 0.05f;
    //endregion

    public Spear(BodyElement owner, WeaponType type) {
        super(owner, type);

        defaultTranslateValue = 0f;
        translate = defaultTranslateValue;
        maxTranslateValue = MAX_TRANSLATION;
        updateCpt = 0;
        numberOfUpdates = (int) (MAX_TRANSLATION / INCREMENT_TRANSLATION);
    }
}
