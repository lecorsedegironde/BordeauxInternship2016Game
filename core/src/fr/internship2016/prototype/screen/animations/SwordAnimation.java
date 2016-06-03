package fr.internship2016.prototype.screen.animations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import fr.internship2016.prototype.gameState.movable.bodies.BodiesStates;
import fr.internship2016.prototype.gameState.movable.bodies.Player;

/**
 * Created by bastien on 30/05/16.
 */
public class SwordAnimation extends ITLAnimation {

    private BodiesStates playerStates;

    public SwordAnimation(BodiesStates playerStates) {
        super();
        this.playerStates = playerStates;
        createAnimationFromState();
        updateAnimation(playerStates);
    }

    @Override
    protected void createAnimationFromState() {
        String textureAtlasToLoad;

        for (BodiesStates b : BodiesStates.values()) {
            switch (b) {
                case IDLE:
                    textureAtlasToLoad = "textures/spritesheet/idleWeapon/idle.atlas";
                    break;
                case RUN:
                    textureAtlasToLoad = "textures/spritesheet/runWeapon/run.atlas";
                    break;
                default:
                    textureAtlasToLoad = "textures/spritesheet/idleWeapon/idle.atlas";
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
        if (bodyState != playerStates || currentAnimation == null) {
            playerStates = bodyState;
            currentAnimation = anims.get(bodyState);
            elapsedTime = 0f;
        }
    }

    private TextureRegion getKeyFrame(float delta, boolean loop) {
        elapsedTime += delta;
        return currentAnimation.getKeyFrame(elapsedTime, loop);
    }

    public Sprite getSprite(float delta, boolean loop, Player player) {
        if (playerStates == BodiesStates.ATTACK || playerStates == BodiesStates.JUMP) {
            delta = 0;
        }
        sprite = new Sprite(getKeyFrame(delta, loop));
        float spriteWidth = 0.88f;
        sprite.setSize(spriteWidth, spriteWidth * 1.9f);

        sprite.setRotation(player.getWeapon().getElementPolygon().getRotation());

        switch (player.getFacing()) {
            case RIGHT:
                sprite.setPosition(player.getWeapon().getX() - 0.25f,
                        player.getWeapon().getY() + 0.07f);
                sprite.setFlip(false, false);
                sprite.setOrigin(0.27f, 0.19f);
                break;
            case LEFT:
                sprite.setPosition(player.getWeapon().getX() - 0.45f,
                        player.getWeapon().getY() + 0.07f);
                sprite.setFlip(true, false);
                sprite.setOrigin(0.61f, 0.19f);
                break;
        }

        if (player.isInvisible()) {
            sprite.setAlpha(0.5f);
        }

        return sprite;
    }
}
