package fr.internship2016.prototype.gameState.movable.spells;

import com.badlogic.gdx.graphics.Color;
import fr.internship2016.prototype.gameState.utils.Direction;

/**
 * Created by bastien on 16/05/16.
 */
public enum SpellType {
    //TODO Add other spells name
    FIRE_SPELL(0.25, 0.30f, Color.RED),
    NO_SPELL(0,0,null);

    private double dmg;
    private float velocityX;
    private Color color;

    SpellType(double dmg, float velocityX, Color color) {
        this.dmg = dmg;
        this.velocityX = velocityX;
        this.color = color;
    }

    public double getDmg() {
        return dmg;
    }

    public void setDmg(double dmg) {
        this.dmg = dmg;
    }

    public float getVelocityX(Direction facing) {
        if (facing == Direction.RIGHT) {
            return velocityX;
        } else if (facing == Direction.LEFT) {
            return -velocityX;
        } else {
            return 0f;
        }
    }

    public void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }

    public Color getColor() {
        return color;
    }
}
