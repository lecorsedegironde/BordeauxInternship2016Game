package fr.internship2016.prototype.screen.animations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import fr.internship2016.prototype.gameState.movable.bodies.BodiesStates;
import fr.internship2016.prototype.gameState.movable.bodies.Player;
import fr.internship2016.prototype.gameState.movable.bodies.enemies.Troll;

/**
 * Created by bastien on 30/05/16.
 */
public class ClubAnimation extends ITLAnimation {

    private BodiesStates playerStates;

    public ClubAnimation(BodiesStates playerStates) {
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
                case WALK:
                    textureAtlasToLoad = "textures/spritesheet/clubWalk/clubWalk.atlas";
                    break;
                default:
                    textureAtlasToLoad = "textures/spritesheet/clubWalk/clubWalk.atlas";
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

    public Sprite getSprite(float delta, boolean loop, Troll t) {
        if (playerStates == BodiesStates.ATTACK) {
            delta = 0;
        }
        sprite = new Sprite(getKeyFrame(delta, loop));
        float spriteWidth = 1.75f;
        sprite.setSize(spriteWidth, spriteWidth * 1.74f);

        sprite.setRotation(t.getWeapon().getElementPolygon().getRotation());

        switch (t.getFacing()) {
            case RIGHT:
                sprite.setPosition(t.getWeapon().getX() - 0.6f,
                        t.getWeapon().getY());
                sprite.setOrigin(0.68f,0.23f);
                sprite.setFlip(true, false);
                break;
            case LEFT:
                sprite.setPosition(t.getWeapon().getX() - 0.85f,
                        t.getWeapon().getY());
                sprite.setOrigin(1.07f,0.23f);
                sprite.setFlip(false, false);
                break;
        }

        return sprite;
    }
}
