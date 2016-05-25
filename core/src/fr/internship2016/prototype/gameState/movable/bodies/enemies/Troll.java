package fr.internship2016.prototype.gameState.movable.bodies.enemies;

import fr.internship2016.prototype.gameState.levels.Level;
import fr.internship2016.prototype.gameState.utils.Direction;
import fr.internship2016.prototype.gameState.weapons.Weapon;
import fr.internship2016.prototype.gameState.weapons.WeaponFactory;

/**
 * Created by bastien on 17/05/16.
 */
public class Troll extends Enemy {

    //region Constants
    //Size
    public static final float WIDTH_TROLL = 1.5f;
    public static final float HEIGHT_TROLL = 3f;
    //Moving forces
    public static final float VELOCITY_X_TROLL = 0.05f;
    public static final float VELOCITY_Y_TROLL = 0f;

    //Life
    private static final int DEFAULT_HIT = 3;
    //endregion

    //region Fields
    //Weapon
    private WeaponFactory weaponFactory;
    private Weapon weapon = null;
    //endregion

    public Troll(float x, float y, float gravity) {
        super(x, y, WIDTH_TROLL, HEIGHT_TROLL, VELOCITY_X_TROLL, VELOCITY_Y_TROLL, gravity);
//        setWeapon(WeaponType.CLUB);
        facing = Direction.LEFT;

        //TEMP
        life = DEFAULT_HIT;

        weaponFactory = new WeaponFactory(this);
        manageWeapon();
        moveLeft();
    }

    @Override
    public void update(Level level) {
        super.update(level);
        //Do not forget to update weapon too
        if (hasWeapon()) weapon.update();
    }

    @Override
    protected void checkBounds(Level level) {
        if (elementRect.getX() <= 0) {
            moveRight();
        } else if (elementRect.getX() >= level.getLevelWidth() - getW()) {
            moveLeft();
        }
    }

    @Override
    protected void enemyIA(Level level) {
        //COMPLETE
    }

    @Override
    public boolean hasWeapon() {
        return weapon != null;
    }

    @Override
    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public boolean isAttacking() {
        return hasWeapon() && weapon.isAttack();
    }

    @Override
    public void manageWeapon() {
        setWeapon(weaponFactory.getAvailableWeapon().first());
    }

    @Override
    public void setWeapon(String weaponName) {
        weapon = weaponFactory.getWeapon(this, weaponName);
    }

    @Override
    public void attack() {
        if (hasWeapon()) weapon.attack();
    }

    @Override
    public void stopAttack() {
        if (hasWeapon()) weapon.stopAttack();
    }

    @Override
    public void knockBack() {
        //COMPLETE
    }
}
