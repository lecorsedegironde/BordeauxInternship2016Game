package fr.internship2016.prototype.gameState.utils;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import fr.internship2016.prototype.gameState.movable.MovableElement;
import fr.internship2016.prototype.gameState.weapons.Weapon;

/**
 * Created by bastien on 18/05/16.
 */
public class CollisionDetector {

    public static boolean isCollision(MovableElement m1, MovableElement m2) {
        return Intersector.overlaps(m1.getElementRect(), m2.getElementRect());
    }

    public static boolean isCollision(Weapon w, MovableElement e) {
        boolean collision = false;
        //Check if the weapon has already hit
        if (!w.hasHit()) {
            //Create a polygon from the MovableElement
            Polygon mvEltPolygon = new Polygon(new float[]{
                    0, 0,
                    e.getW(), 0,
                    e.getW(), e.getH(),
                    0, e.getH()
            });
            mvEltPolygon.setPosition(e.getX(), e.getY());
            if (Intersector.overlapConvexPolygons(mvEltPolygon, w.getElementPolygon())) {
                collision = true;
                w.hit();
            }
        }
        return collision;
    }
}
