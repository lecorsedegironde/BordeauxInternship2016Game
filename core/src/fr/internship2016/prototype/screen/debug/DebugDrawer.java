package fr.internship2016.prototype.screen.debug;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import fr.internship2016.prototype.gameState.levels.Level;
import fr.internship2016.prototype.gameState.movable.MovableElement;
import fr.internship2016.prototype.gameState.movable.bodies.Player;
import fr.internship2016.prototype.gameState.movable.spells.Spell;

/**
 * Created by bastien on 15/05/16.
 */
public class DebugDrawer {

    public static void drawMovable(ShapeRenderer shapeRenderer, MovableElement m) {
        if (m instanceof Player) {
            shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
            if (!((Player) m).isInvisible()) {
                if (((Player) m).canBeInvisible()) {
                    shapeRenderer.setColor(Color.BLUE);
                } else {
                    shapeRenderer.setColor(Color.CYAN);
                }
                shapeRenderer.rect(m.getX(), m.getY(), m.getW(), m.getH());
            }
            shapeRenderer.set(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.GREEN);
            shapeRenderer.rect(m.getX(), m.getY(), m.getW(), m.getH());
            if (((Player) m).hasWeapon()) {
                shapeRenderer.setColor(Color.BLACK);
                shapeRenderer.polygon(((Player) m).getWeapon().getElementPolygon().getTransformedVertices());
            }
        } else if (m instanceof Spell) {
            shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(((Spell) m).getType().getColor());
            shapeRenderer.rect(m.getX(), m.getY(), m.getW(), m.getH());
        }
        //TODO Enemies
    }

    public static void drawLevel(Sprite sprite, SpriteBatch batch, ShapeRenderer shapeRenderer, Level l) {
        sprite.draw(batch);
        shapeRenderer.set(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.line(0, l.getLevelGroundHeight(), l.getLevelWidth(), l.getLevelGroundHeight());
    }
}
