package fr.internship2016.prototype.screen.debug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import fr.internship2016.prototype.gameState.GameState;
import fr.internship2016.prototype.gameState.movable.MovableElement;
import fr.internship2016.prototype.gameState.movable.spells.Spell;
import fr.internship2016.prototype.screen.camera.ITLCamera;
import fr.internship2016.prototype.screen.interfaces.Render;
import fr.internship2016.prototype.screen.ui.GameUiDebug;

/**
 * Created by bastien on 13/05/16.
 */
public class ITLDebugRenderer implements Render {

    //Camera
    private ITLCamera camera;

    //Draw
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private Sprite sprite;

    public ITLDebugRenderer(GameState gameState) {

        //Rendering
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        sprite = new Sprite(new Texture(Gdx.files.internal(gameState.getLevel().getBackground())));
        sprite.setSize(gameState.getLevel().getLevelWidth(), gameState.getLevel().getLevelHeight());

        //Camera
        camera = new ITLCamera(0f, 0f, gameState.getLevel().getLevelWidth(), 0.002,
                gameState.getLevel().getLevelWidth(), gameState.getLevel().getLevelHeight());
        gameState.getPlayer().addObserver(camera);
    }

    @Override
    public void render(GameState gameState, GameUiDebug uiDebug) {
        //First update cam observers
        updateCam(gameState.getMovableElements());

        //Clear screen
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Apply Game camera viewport
        camera.getViewport().apply();
        //Prepare SpriteBatch and ShapeRenderer
        batch.setProjectionMatrix(camera.getCameraCombined());
        batch.begin();

        shapeRenderer.setProjectionMatrix(camera.getCameraCombined());
        shapeRenderer.setAutoShapeType(true);
        shapeRenderer.begin();

        //First draw level
        DebugDrawer.drawLevel(sprite, batch, shapeRenderer, gameState.getLevel());
        //End batch here to avoid drawing problems as the only sprite is the background
        batch.end();

        //Draw the other things on top
        for (MovableElement m : gameState.getMovableElements()) {
            DebugDrawer.drawMovable(shapeRenderer, m);
        }
        //End SpriteBatch and ShapeRenderer
        shapeRenderer.end();

        //DO NOT FORGET TO APPLY SPECIFIC VIEWPORT
        uiDebug.draw();
    }

    public ITLCamera getCamera() {
        return camera;
    }

    private void updateCam(Array<MovableElement> movableElements) {
        for (MovableElement m : movableElements) {
            if (m instanceof Spell) {
                if (m.countObservers() == 0) {
                    m.addObserver(camera);
                }
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        camera.resize(height);
    }

    @Override
    public void dispose() {
        sprite.getTexture().dispose();
        batch.dispose();
        shapeRenderer.dispose();
    }
}
