package fr.internship2016.prototype.movable.armed;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import fr.internship2016.prototype.weapon.WeaponStyles;
import fr.internship2016.prototype.weapon.rotate.Club;

import static fr.internship2016.prototype.utils.Constants.*;

/**
 * Created by pacaud on 16/04/25.
 * Troll
 */
public class Troll extends ArmedElement {

    private double numberHitLeft;

    public Troll(float x, float y, float width, float height, float velocityX, float velocityY) {
        super(x, y, width, height, velocityX, velocityY);
        setWeapon(WeaponStyles.CLUB);

        rightFacing = false;
        numberHitLeft = HIT_TROLL;
    }

    @Override
    public void update() {
        super.update();

        if (elementRect.getX() <= 0) {
            moveRight();
        } else if (elementRect.getX() >= WORLD_WIDTH - getW()) {
            moveLeft();
        }
    }

    @Override
    public void setWeapon(WeaponStyles w) {
        switch (w) {
            case CLUB:
                weapon = new Club(this, CLUB_WIDTH, CLUB_HEIGHT);
                break;
            default:
                weapon = null;
                break;
        }
    }

    @Override
    public void hitWeapon() {
        numberHitLeft--;
    }

    @Override
    public void hitSpell(double spellDmg) {
        numberHitLeft -= spellDmg;
    }

    @Override
    public double getLife() {
        return numberHitLeft;
    }

    @Override
    public void knockBack(boolean rightKnockBack) {
        if (rightKnockBack) {
            velocityX = KNOCKBACK_X_TROLL;
            velocityY = KNOCKBACK_Y_TROLL;
            stopAttack();
        } else {
            velocityX = -KNOCKBACK_X_TROLL;
            velocityY = KNOCKBACK_Y_TROLL;
            stopAttack();
        }
    }

    @Override
    public void draw(ShapeRenderer s) {
        s.set(ShapeRenderer.ShapeType.Filled);
        s.setColor(Color.SCARLET);
        s.rect(getX(), getY(), getW(), getH());

        s.set(ShapeRenderer.ShapeType.Line);
        s.setColor(Color.BLACK);
        s.polygon(getWeapon().getTransformedVertices());
    }
}



