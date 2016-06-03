package fr.internship2016.prototype.screen.animations;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.ArrayMap;

/**
 * Created by bastien on 02/06/16.
 */
public abstract class ITLAnimation {

    protected ArrayMap<Object, Animation> anims;

    protected TextureAtlas textureAtlas;
    protected Animation currentAnimation;
    protected float elapsedTime = 0f;

    //For getting sprite
    protected Sprite sprite;

    public ITLAnimation() {
        anims = new ArrayMap<>();
    }

    protected abstract void createAnimationFromState();

    public void dispose() {
        textureAtlas.dispose();
    }
}
