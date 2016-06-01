package fr.internship2016.prototype.screen.animations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.utils.ArrayMap;
import fr.internship2016.prototype.gameState.movable.bodies.BodiesStates;
import fr.internship2016.prototype.gameState.movable.bodies.Player;

/**
 * Created by bastien on 30/05/16.
 */
public class PlayerAnimation {

    private BodiesStates playerStates;

    private ArrayMap<BodiesStates, Animation> anims;

    private TextureAtlas textureAtlas;
    private Animation currentAnimation;
    private float elapsedTime = 0f;

    //For getting sprite
    private Sprite sprite;

    public PlayerAnimation(BodiesStates playerStates) {
        this.playerStates = playerStates;
        anims = new ArrayMap<>();
        createAnimationFromState();
        updateAnimation(playerStates);
    }

    private void createAnimationFromState() {
        String textureAtlasToLoad;

        for (BodiesStates b : BodiesStates.values()) {
            textureAtlasToLoad = "";
            switch (b) {
                case IDLE:
                    textureAtlasToLoad = "textures/spritesheet/idle/idle.atlas";
                    break;
                case RUN:
                    textureAtlasToLoad = "textures/spritesheet/run/run.atlas";
                    break;
                case JUMP:
                    textureAtlasToLoad = "textures/spritesheet/jump/jump.atlas";
                    break;
                case ATTACK:
                    textureAtlasToLoad = "textures/spritesheet/attack/attack.atlas";
                    break;
                case FIRE_SPELL:
                    textureAtlasToLoad = "textures/spritesheet/spell/spell.atlas";
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
        sprite = new Sprite(getKeyFrame(delta, loop));
        float spriteWidth = 1.75f;
        sprite.setSize(spriteWidth, spriteWidth * 1.3f);

        switch (player.getFacing()) {
            case RIGHT:
                sprite.setPosition(player.getX() - 0.2f, player.getY());
                break;
            case LEFT:
                sprite.setPosition(player.getX(), player.getY());
                sprite.flip(true, false);
                break;
        }

        return sprite;
    }

    public void dispose() {
        textureAtlas.dispose();
    }
}
