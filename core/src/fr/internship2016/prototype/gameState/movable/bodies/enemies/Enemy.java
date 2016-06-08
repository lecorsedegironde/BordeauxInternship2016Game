package fr.internship2016.prototype.gameState.movable.bodies.enemies;

import fr.internship2016.prototype.gameState.levels.Level;
import fr.internship2016.prototype.gameState.movable.bodies.BodyElement;
import fr.internship2016.prototype.gameState.movable.interfaces.Armed;
import fr.internship2016.prototype.gameState.weapons.Weapon;

/**
 * Created by bastien on 13/05/16.
 */
public abstract class Enemy extends BodyElement implements Armed {

    //Life
    protected double life;

    public Enemy(float x, float y, float width, float height, float footHeight, float velocityX, float velocityY, float gravity) {
        super(x, y, width, height, footHeight, velocityX, velocityY, gravity);
    }

    protected abstract void enemyIA(Level level);

    @Override
    public void update(Level level) {
        super.update(level);
        enemyIA(level);
    }

    @Override
    public boolean hasWeapon() {
        return false;
    }

    @Override
    public Weapon getWeapon() {
        return null;
    }

    @Override
    public boolean isAttacking() {
        return false;
    }

    //region Life & Hit
    @Override
    public void hit(double dmg) {
        life -= dmg;
        if (life < 0) life = 0;
    }

    public double getLife() {
        return life;
    }
    //endregion
}
