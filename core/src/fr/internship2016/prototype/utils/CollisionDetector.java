package fr.internship2016.prototype.utils;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import fr.internship2016.prototype.movable.MovableElement;
import fr.internship2016.prototype.weapon.Weapon;

/**
 * Created by bastien on 26/04/16.
 * This class detects collisions
 */
public class CollisionDetector {

    public static boolean isCollision(Weapon w, MovableElement e) {
        boolean collision = false;
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
        }
        return collision;
    }
}
