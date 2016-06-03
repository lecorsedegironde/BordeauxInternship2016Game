package fr.internship2016.prototype.screen.animations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import fr.internship2016.prototype.gameState.movable.spells.Spell;
import fr.internship2016.prototype.gameState.movable.spells.SpellType;

/**
 * Created by bastien on 02/06/16.
 */
public class SpellAnimation extends ITLAnimation {

    public SpellAnimation() {
        super();
        createAnimationFromState();
    }

    @Override
    protected void createAnimationFromState() {
        String textureAtlasToLoad = "";

        for (SpellType s : SpellType.values()) {
            switch (s) {
                case FIRE_SPELL:
                    textureAtlasToLoad = "textures/spritesheet/flame/flame.atlas";
                    break;
                case NO_SPELL:
                    textureAtlasToLoad = "";
                    break;
            }

            if (!textureAtlasToLoad.isEmpty()) {
                textureAtlas = new TextureAtlas(Gdx.files.internal(textureAtlasToLoad));
                Animation anim = new Animation(1f / 24f, textureAtlas.getRegions());
                anims.put(s, anim);
            }
        }
    }

    private TextureRegion getKeyFrame(float delta, boolean loop) {
        elapsedTime += delta;
        return currentAnimation.getKeyFrame(elapsedTime, loop);
    }

    public Sprite getSprite(float delta, boolean loop, Spell s) {

        currentAnimation = anims.get(s.getType());

        sprite = new Sprite(getKeyFrame(delta, loop));
        float spriteSize = 0.5f;
        sprite.setSize(spriteSize, spriteSize);
        sprite.setPosition(s.getX() - 0.15f, s.getY() - 0.15f);

        return sprite;
    }
}
