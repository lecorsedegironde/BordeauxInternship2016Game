package fr.internship2016.prototype.gameState.weapons.translating;

import fr.internship2016.prototype.gameState.movable.bodies.BodyElement;
import fr.internship2016.prototype.gameState.utils.Direction;
import fr.internship2016.prototype.gameState.weapons.Weapon;
import fr.internship2016.prototype.gameState.weapons.WeaponType;

/**
 * Created by bastien on 18/05/16.
 */
public abstract class TranslatingWeapon extends Weapon {

    protected float defaultTranslateValue;
    protected float translate;
    protected float maxTranslateValue;

    public TranslatingWeapon(BodyElement owner, WeaponType type) {
        super(owner, type);
    }

    @Override
    public void update() {
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
            //Get max translation
            int translateModifier = 0;
            if (owner.getFacing() == Direction.RIGHT) {
                translateModifier = 1;
            } else if (owner.getFacing() == Direction.LEFT) {
                translateModifier = -1;
            }

            //First attack phase
            if (!attackOver) {
                if (updateCpt <= numberOfUpdates) {
                    //Divide value by the number of updates dedicated to the animation
                    float maxTranslateValueUpdated = maxTranslateValue * translateModifier;
                    translate = (maxTranslateValueUpdated / numberOfUpdates) * updateCpt;
//                    elementPolygon.translate(translate, 0);
                    updateCpt++;
                } else {
                    //First phase is over
                    attackOver = true;
                }
            } else {
                //Second phase -> back to original position
                if (updateCpt >= 0) {
                    float maxTranslateValueUpdated = maxTranslateValue * translateModifier;
                    translate = (maxTranslateValueUpdated / numberOfUpdates) * updateCpt;
//                    elementPolygon.translate(translate, 0);
                    updateCpt--;
                } else {
                    hasHit = false;
                    attack = false;
                }
            }
        }
    }

    @Override
    protected void updatePos() {
        xWeapon = owner.getX() + (owner.getW() / 2f);
        yWeapon = owner.getY() + (owner.getH() / 2f);

        if (attack) {
            xWeapon += translate;
        }

        setPosition(xWeapon, yWeapon);

        float tempWidth = widthWeapon;

        if (owner.getFacing() == Direction.RIGHT) {
            tempWidth = widthWeapon;
        } else if (owner.getFacing() == Direction.LEFT) {
            tempWidth = -widthWeapon;
        }

        elementPolygon.setVertices(new float[]{
                0, 0,
                tempWidth, 0,
                tempWidth, heightWeapon,
                0, heightWeapon
        });
    }
}
