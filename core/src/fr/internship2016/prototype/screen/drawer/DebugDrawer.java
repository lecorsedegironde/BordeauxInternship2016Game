package fr.internship2016.prototype.screen.drawer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import fr.internship2016.prototype.gameState.levels.Level;
import fr.internship2016.prototype.gameState.movable.MovableElement;
import fr.internship2016.prototype.gameState.movable.bodies.enemies.Enemy;
import fr.internship2016.prototype.gameState.movable.bodies.enemies.Troll;
import fr.internship2016.prototype.gameState.movable.spells.Spell;

/**
 * Created by bastien on 15/05/16.
 */
public class DebugDrawer {

    public static void drawMovable(ShapeRenderer shapeRenderer, MovableElement m) {
        if (m instanceof Spell) {
//            shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
//            shapeRenderer.setColor(((Spell) m).getType().getColor());
//            shapeRenderer.rect(m.getX(), m.getY(), m.getW(), m.getH());
        } else if (m instanceof Enemy) {
            if (m instanceof Troll) {
                shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(Color.SCARLET);
                shapeRenderer.rect(m.getX(), m.getY(), m.getW(), m.getH());

                shapeRenderer.set(ShapeRenderer.ShapeType.Line);
                shapeRenderer.setColor(Color.GREEN);
                shapeRenderer.rect(m.getX(), m.getY(), m.getW(), m.getH());

                if (((Troll) m).hasWeapon()) {
                    shapeRenderer.setColor(Color.BLACK);
                    shapeRenderer.polygon(((Troll) m).getWeapon().getElementPolygon().getTransformedVertices());
                }
            }
        }
    }

    public static void drawLevel(ShapeRenderer shapeRenderer, Level l) {
        shapeRenderer.set(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.line(0, l.getLevelGroundHeight(), l.getLevelWidth(), l.getLevelGroundHeight());
    }
}
