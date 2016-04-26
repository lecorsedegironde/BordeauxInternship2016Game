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
    protected void updateWeaponPos() {
        float divideFactor = 12f;
        baseX = owner.getX();
        baseY = owner.getY() + (owner.getH() / divideFactor);
    }


}
