package fr.internship2016.prototype.utils;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import fr.internship2016.prototype.movable.MovableElement;
import fr.internship2016.prototype.movable.armed.ArmedElement;
import fr.internship2016.prototype.movable.armed.Player;
import fr.internship2016.prototype.movable.armed.Troll;

import static fr.internship2016.prototype.utils.Constants.CLUB_HEIGHT;

/**
 * Created by pacaud on 16/04/27.
 * AI of enemies
 */
public class EnemiesAI {

    public static Rectangle detectionRectangle;

    public static void enemyReaction(ArmedElement e, Player p) {
        if (e instanceof Troll) {
            detectionRectangle = new Rectangle();
            detectionRectangle.x = p.getX() - CLUB_HEIGHT;
            detectionRectangle.y = p.getY();
            detectionRectangle.width = p.getW() + CLUB_HEIGHT;
            detectionRectangle.height = p.getH();
            if (Intersector.overlaps(e.getRectangle(), detectionRectangle) && !p.isInvisible())
                e.attack();
            else e.stopAttack();
        }
    }

    public static void goToPlayer(MovableElement e, Player p) {
        if (e instanceof Troll && !p.isInvisible()) {
            if (p.getX() > e.getX() + e.getW())
                e.moveRight();
            else if (p.getX() + p.getW() < e.getX())
                e.moveLeft();
        }
    }

    public static  void dontMove(MovableElement e)
    {
        e.setVelocityX(0);
        e.setVelocityY(0);
    }
}

