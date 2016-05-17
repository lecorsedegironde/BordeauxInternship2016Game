package fr.internship2016.prototype.gameState.movable.spells;

import fr.internship2016.prototype.gameState.levels.Level;
import fr.internship2016.prototype.gameState.movable.MovableElement;
import fr.internship2016.prototype.gameState.movable.bodies.BodyElement;

/**
 * Created by bastien on 16/05/16.
 */
public class Spell extends MovableElement {

    //region Constants
    private static final float SIDE_SPELL = 0.2f;
    //endregion

    //region Fields
    private boolean disappear;
    private SpellType type;
    //endregion

    public Spell(BodyElement fireMe, SpellType type) {
        super(fireMe.getX() + fireMe.getW() / 2, fireMe.getY() + fireMe.getH() / 2, SIDE_SPELL, SIDE_SPELL,
                type.getVelocityX(fireMe.getFacing()), 0f, 0f);
        this.type = type;
    }

    //region Update
    @Override
    public void update(Level level) {
        //Direction is fixed
        translate(velocityX, 0);

        //Check if the spell is out of world
        checkBounds(level);

        setChanged();
        notifyObservers();
    }

    private void checkBounds(Level level) {
        if (getX() <= 0 || getX() >= level.getLevelWidth() - getW()) {
            disappear = true;
        }
    }
    //endregion

    //region Type
    public SpellType getType() {
        return type;
    }

    public void setType(SpellType type) {
        this.type = type;
    }
    //endregion

    //region Dmg
    public double getDmg() {
        return type.getDmg();
    }

    public void setDmg(double dmg) {
        type.setDmg(dmg);
    }
    //endregion

    //region Disappearing
    public boolean isDisappear() {
        return disappear;
    }

    public void hasHit() {
        disappear = true;
    }

    public void setDisappear() {
        disappear = true;
    }
    //endregion
}
