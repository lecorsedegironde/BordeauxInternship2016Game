package fr.internship2016.prototype.utils;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import fr.internship2016.prototype.movable.MovableElement;
import fr.internship2016.prototype.movable.Troll;

import static fr.internship2016.prototype.utils.Constants.CLUB_HEIGHT;

/**
 * Created by pacaud on 16/04/27.
 */
public class EnemiesIA {

    public static Rectangle detectionRectangle;

    public static void enemyReaction(MovableElement e, MovableElement p) {


        if (e instanceof Troll) {

            detectionRectangle = new Rectangle();
            detectionRectangle.x = p.getX() - CLUB_HEIGHT;
            detectionRectangle.y = p.getY();
            detectionRectangle.width = p.getW() + CLUB_HEIGHT;
            detectionRectangle.height = p.getH();
            if (Intersector.overlaps(e.getRectangle(), detectionRectangle))
                e.attack();

        }
    }

    public static void goToPlayer(MovableElement e, MovableElement p) {
        if (e instanceof Troll) {
            if (p.getX() > e.getX() + e.getW())
                e.moveRight();
            else if (p.getX() + p.getW() < e.getX())
                e.moveLeft();
        }
    }
}

