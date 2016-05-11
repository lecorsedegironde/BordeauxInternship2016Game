package fr.internship2016.prototype.movable.spells;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import fr.internship2016.prototype.movable.MovableElement;

import static fr.internship2016.prototype.utils.Constants.*;

/**
 * Created by bastien on 27/04/16.
 * Fire spell
 */
public class FireSpell extends Spell {
    public FireSpell(MovableElement fireMe) {
        super(fireMe.getX() + fireMe.getW() / 2, fireMe.getY() + fireMe.getH() / 2,
                SIDE_SPELL, SIDE_SPELL, fireMe.isRightFacing() ? VELOCITY_X_SPELL_FIRE : -VELOCITY_X_SPELL_FIRE,
                0f, FIRE_SPELL_DMG);

    }

    @Override
    public void draw(ShapeRenderer s) {
        s.setColor(Color.MAGENTA);
        super.draw(s);
    }
}
