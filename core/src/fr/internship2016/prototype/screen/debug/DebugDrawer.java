package fr.internship2016.prototype.screen.debug;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import fr.internship2016.prototype.gameState.levels.Level;
import fr.internship2016.prototype.gameState.movable.MovableElement;
import fr.internship2016.prototype.gameState.movable.Player;

/**
 * Created by bastien on 15/05/16.
 */
public class DebugDrawer {

    public static void drawMovable(ShapeRenderer shapeRenderer, MovableElement m) {
        if (m instanceof Player) {
            shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
            if (((Player) m).isInvisible()) {
                //TODO
            }
            shapeRenderer.setColor(Color.BLUE);
            shapeRenderer.rect(m.getX(), m.getY(), m.getW(), m.getH());
            shapeRenderer.set(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.GREEN);
            shapeRenderer.rect(m.getX(), m.getY(), m.getW(), m.getH());
            //TODO WEAPON
        }
        //TODO Enemies and Spells
    }

    public static void drawLevel(Sprite sprite, SpriteBatch batch, ShapeRenderer shapeRenderer, Level l) {
        sprite.draw(batch);
        shapeRenderer.set(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.line(0, l.getLevelGroundHeight(), l.getLevelWidth(), l.getLevelGroundHeight());
    }
}
