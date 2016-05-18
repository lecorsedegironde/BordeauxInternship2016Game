package fr.internship2016.prototype.weapon;

import com.badlogic.gdx.math.Polygon;
import fr.internship2016.prototype.movable.MovableElement;

import static fr.internship2016.prototype.utils.Constants.SPEAR_INCREMENT_TRANSLATION;
import static fr.internship2016.prototype.utils.Constants.SPEAR_MAX_TRANSLATION;
import static fr.internship2016.prototype.utils.Constants.SPEAR_REFILL_TIME;

/**
 * Created by bastien on 12/05/16.
 */
public class Spear extends Weapon {

    private float translateValue;

    public Spear(MovableElement owner, float width, float height) {
        super(owner, width, height);
        refillTime = SPEAR_REFILL_TIME;
        translateValue = 0f;
    }

    @Override
    public void update() {
        updateWeaponPos();
        setPosition(baseX, baseY);

        if (attack) {
            float maxTranslate, incrementTranslation;
            if (owner.isRightFacing()) {
                maxTranslate = SPEAR_MAX_TRANSLATION;
                incrementTranslation = SPEAR_INCREMENT_TRANSLATION;
            } else {
                maxTranslate = -SPEAR_MAX_TRANSLATION;
                incrementTranslation = -SPEAR_INCREMENT_TRANSLATION;
            }

            if (!attackOver) {
                translateValue += incrementTranslation;

                if (Math.abs(translateValue) >= Math.abs(maxTranslate)) {
                    attackOver = true;
                }
            } else {
                //Define arbitrary threshold
                float arbitraryThreshold = 0.04f;
                if (translateValue >= arbitraryThreshold || translateValue <= -arbitraryThreshold) {
                    translateValue -= incrementTranslation;
                } else {
                    translateValue = 0f;
                    attack = false;
                    hasHit = false;
                }
            }

        }
    }

    @Override
    protected void updateWeaponPos() {
        baseX = owner.getX() + owner.getW() / 2f;
        baseY = owner.getY() + (owner.getH() / 2f);

        //If attack
        if (attack) {
            baseX += translateValue;
        }

        float tempWidth;

        if (owner.isRightFacing()) {
            tempWidth = baseWidth;
        } else {
            tempWidth = -baseWidth;
        }

        elementPolygon = new Polygon(new float[]{
                0, 0,
                tempWidth, 0,
                tempWidth, baseHeight,
                0, baseHeight
        });
    }

    @Override
    public void attackForceStop() {
        attack = false;
        attackOver = true;
        setPosition(baseX, baseY);
    }
}
