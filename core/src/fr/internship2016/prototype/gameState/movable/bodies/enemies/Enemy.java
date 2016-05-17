package fr.internship2016.prototype.gameState.movable.bodies.enemies;

import fr.internship2016.prototype.gameState.levels.Level;
import fr.internship2016.prototype.gameState.movable.bodies.BodyElement;
import fr.internship2016.prototype.gameState.movable.interfaces.Armed;
import fr.internship2016.prototype.gameState.weapons.Weapon;

/**
 * Created by bastien on 13/05/16.
 */
public abstract class Enemy extends BodyElement implements Armed {

    public Enemy(float x, float y, float width, float height, float velocityX, float velocityY, float gravity) {
        super(x, y, width, height, velocityX, velocityY, gravity);
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
}
