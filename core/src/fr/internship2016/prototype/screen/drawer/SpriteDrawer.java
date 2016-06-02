package fr.internship2016.prototype.screen.drawer;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import fr.internship2016.prototype.screen.camera.ITLCamera;

/**
 * Created by bastien on 01/06/16.
 */
public class SpriteDrawer {

    private SpriteBatch batch;
    private Array<Sprite> sprites;

    public SpriteDrawer(final ITLCamera camera) {
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.getCameraCombined());
        sprites = new Array<>();
    }

    public void add(Sprite s) {
        sprites.add(s);
    }

    public void draw() {
        batch.begin();
        for (Sprite s : sprites) {
            s.draw(batch);
        }
        batch.end();

        //Clear array for next render
        sprites.clear();
    }

    public void dispose() {
        batch.dispose();
    }
}
