package fr.internship2016.prototype.screen.animations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import fr.internship2016.prototype.gameState.movable.bodies.BodiesStates;
import fr.internship2016.prototype.gameState.movable.bodies.enemies.Troll;

/**
 * Created by Bastien on 08/06/2016.
 */
public class TrollAnimation extends ITLAnimation {

    private BodiesStates trollState;

    public TrollAnimation(BodiesStates trollState) {
        super();
        this.trollState = trollState;
        createAnimationFromState();
        updateAnimation(trollState);
    }

    @Override
    protected void createAnimationFromState() {
        String textureAtlasToLoad;

        for (BodiesStates b : BodiesStates.values()) {
            textureAtlasToLoad = "";
            switch (b) {
                case ATTACK:
                    textureAtlasToLoad = "textures/spritesheet/trollAttack/trollAttack.atlas";
                    break;
                case WALK:
                    textureAtlasToLoad = "textures/spritesheet/trollWalk/trollWalk.atlas";
                    break;
                default:
                    textureAtlasToLoad = "textures/spritesheet/trollWalk/trollWalk.atlas";
                    break;
            }
            if (!textureAtlasToLoad.isEmpty()) {
                textureAtlas = new TextureAtlas(Gdx.files.internal(textureAtlasToLoad));
                Animation anim = new Animation(1f / 24f, textureAtlas.getRegions());
                anims.put(b, anim);
            }
        }
    }

    public void updateAnimation(BodiesStates bodyState) {
        if (bodyState != trollState || currentAnimation == null) {
            trollState = bodyState;
            currentAnimation = anims.get(bodyState);
            elapsedTime = 0f;
        }
    }

    private TextureRegion getKeyFrame(float delta, boolean loop) {
        elapsedTime += delta;
        return currentAnimation.getKeyFrame(elapsedTime, loop);
    }

    public Sprite getSprite(float delta, boolean loop, Troll t) {
        if (trollState != BodiesStates.ATTACK && trollState != BodiesStates.WALK) {
            delta = 0;
        }

        sprite = new Sprite(getKeyFrame(delta, loop));
        float spriteWidth = 2.5f;
        sprite.setSize(spriteWidth, spriteWidth * 1.27f);

        switch (t.getFacing()) {
            case RIGHT:
                sprite.setPosition(t.getX() - 0.7f, t.getY() - 0.2f);
                sprite.flip(true, false);
                break;
            case LEFT:
                sprite.setPosition(t.getX() - 0.3f, t.getY() - 0.2f);
                break;
        }

        return sprite;
    }
}
