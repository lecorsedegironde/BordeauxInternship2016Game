package fr.internship2016.prototype.screen.animations;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.ObjectMap;

/**
 * Created by bastien on 02/06/16.
 */
public abstract class ITLAnimation {

    protected ObjectMap<Object, Animation> anims;

    protected TextureAtlas textureAtlas;
    protected Animation currentAnimation;
    protected float elapsedTime = 0f;

    //For getting sprite
    protected Sprite sprite;

    public ITLAnimation() {
        anims = new ObjectMap<>();
    }

    protected abstract void createAnimationFromState();

    public void dispose() {
        textureAtlas.dispose();
    }
}
