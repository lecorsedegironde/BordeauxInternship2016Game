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
public class PlayerAnimation {

    private BodiesStates playerStates;

    private TextureAtlas textureAtlas;
    private Animation animation;
    private float elapsedTime = 0f;

    //For getting sprite
    private Sprite sprite;

    public PlayerAnimation(BodiesStates playerStates) {
        this.playerStates = playerStates;
        createAnimationFromState(playerStates);
    }

    private void createAnimationFromState(BodiesStates playerStates) {
        String textureAtlasToLoad = "";

        //Provisory
        textureAtlasToLoad = "textures/spritesheet/idle/spritesheet_idle.atlas";


        switch (playerStates) {
            case IDLE:
                textureAtlasToLoad = "textures/spritesheet/idle/spritesheet_idle.atlas";
                break;
            case WALK:
                //No need for player
                break;
            case RUN:
                textureAtlasToLoad = "textures/spritesheet/run/spritesheet_run.atlas";
                break;
            case JUMP:
                break;
            case ATTACK:
                break;
            case JUMP_ATTACK:
                break;
            case FIRE_SPELL:
                break;
        }

        textureAtlas = new TextureAtlas(Gdx.files.internal(textureAtlasToLoad));
        animation = new Animation(1f/24f, textureAtlas.getRegions());
    }

    public void updateAnimation(BodiesStates bodyState) {
        if (bodyState != playerStates) {
            createAnimationFromState(bodyState);
            playerStates = bodyState;
        }
    }

    private TextureRegion getKeyFrame(float delta, boolean loop) {
        elapsedTime += delta;
        return animation.getKeyFrame(elapsedTime, loop);
    }

    public Sprite getSprite(float delta, boolean loop, Player player) {
        sprite = new Sprite(getKeyFrame(delta, loop));
        float spriteWidth = 1.5f;
        sprite.setSize(spriteWidth, spriteWidth * 1.73f);
        sprite.setPosition(player.getX(), player.getY());

        switch (player.getFacing()) {
            case LEFT:
                sprite.flip(true, false);
                break;
        }

        return sprite;
    }

    public void dispose(){
        textureAtlas.dispose();
    }
}
