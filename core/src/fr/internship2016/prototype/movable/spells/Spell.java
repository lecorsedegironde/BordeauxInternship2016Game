package fr.internship2016.prototype.movable.spells;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import fr.internship2016.prototype.movable.MovableElement;

import static fr.internship2016.prototype.utils.Constants.*;

/**
 * Created by bastien on 27/04/16.
 * Spell management
 */
public abstract class Spell extends MovableElement {

    private Vector2 posSpell;
    private boolean disappear;
    private double dmg;

    public Spell(float x, float y, float width, float height, float velocityX, float velocityY,double dmg) {
        super(x, y, width, height, velocityX, velocityY);
        disappear = false;
        posSpell = new Vector2();
        this.dmg = dmg;
    }

    @Override
    public void update() {
        elementRect.x += horizontalVelocity;
        elementRect.y += verticalVelocity;
        velocityY += FALL_SPELL;

        if (elementRect.y <= GROUND_HEIGHT) {
            disappear = true;
        }

        //Left and Right
        if (elementRect.getX() <= 0 || elementRect.getX() >= WORLD_WIDTH - getW()) {
            disappear = true;
        }
    }

    public void update(Viewport v) {
        update();

        //On screen
        posSpell.set(getX(), getY());
        v.project(posSpell);
        if (posSpell.x < 0 || posSpell.x > Gdx.graphics.getWidth()) {
            disappear = true;
        }
    }

    public boolean isDisappear() {
        return disappear;
    }

    public void hasHit() {
        disappear = true;
    }

    public double getDmg() {
        return dmg;
    }

    public void setDmg(double dmg) {
        this.dmg = dmg;
    }
}
