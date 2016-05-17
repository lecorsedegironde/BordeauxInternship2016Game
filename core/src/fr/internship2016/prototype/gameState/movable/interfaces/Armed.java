package fr.internship2016.prototype.gameState.movable.interfaces;

import fr.internship2016.prototype.gameState.weapon.Weapon;
import fr.internship2016.prototype.gameState.weapon.WeaponType;

/**
 * Created by bastien on 13/05/16.
 */
public interface Armed {

    boolean hasWeapon();
    Weapon getWeapon();
    void setWeapon(WeaponType type);
    boolean isAttacking();
    void attack();
    void stopAttack();
}
