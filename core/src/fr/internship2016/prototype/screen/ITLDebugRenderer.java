package fr.internship2016.prototype.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import fr.internship2016.prototype.gameState.GameState;
import fr.internship2016.prototype.gameState.movable.MovableElement;
import fr.internship2016.prototype.gameState.movable.bodies.enemies.Troll;
import fr.internship2016.prototype.gameState.movable.spells.Spell;
import fr.internship2016.prototype.screen.animations.*;
import fr.internship2016.prototype.screen.camera.ITLCamera;
import fr.internship2016.prototype.screen.drawer.DebugDrawer;
import fr.internship2016.prototype.screen.drawer.SpriteDrawer;
import fr.internship2016.prototype.screen.interfaces.Render;
import fr.internship2016.prototype.screen.ui.GameUiDebug;

/**
 * Created by bastien on 13/05/16.
 */
public class ITLDebugRenderer implements Render {

    //Camera
    private ITLCamera camera;

    //Draw
    private SpriteDrawer spriteDrawer;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private Sprite background;
    private Sprite player;
    private Sprite sword;

    //ITLAnimation tests
    private PlayerAnimation playerAnimation;
    private SwordAnimation swordAnimation;
    private ObjectMap<String, Array<ITLAnimation>> trollAnimations;
    private SpellAnimation spellAnimation;

    public ITLDebugRenderer(GameState gameState) {

        //Camera
        camera = new ITLCamera(0f, 0f, gameState.getLevel().getLevelWidth(), 0.002,
                gameState.getLevel().getLevelWidth(), gameState.getLevel().getLevelHeight());
        gameState.getPlayer().addObserver(camera);

        //Rendering
        batch = new SpriteBatch();
        spriteDrawer = new SpriteDrawer();

        shapeRenderer = new ShapeRenderer();

        //Sprites & anims
        createSprites(gameState);
    }

    @Override
    public void render(GameState gameState, GameUiDebug uiDebug, boolean pause) {
        //First update cam observers
        updateCam(gameState.getMovableElements());

        //Clear screen
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        //Apply Game camera viewport
        camera.getViewport().apply();

        //Update animations
        float spellDelta, trollDelta;
        if (!pause) {
            playerAnimation.updateAnimation(gameState.getPlayer().getBodyState());
            swordAnimation.updateAnimation(gameState.getPlayer().getBodyState());
            player = playerAnimation.getSprite(Gdx.graphics.getDeltaTime(), true, gameState.getPlayer());
            sword = swordAnimation.getSprite(Gdx.graphics.getDeltaTime(), true, gameState.getPlayer());
            spellDelta = Gdx.graphics.getDeltaTime();
            trollDelta = Gdx.graphics.getDeltaTime();
            for (MovableElement m : gameState.getMovableElements()) {
                if (m instanceof Troll) {
                    ((TrollAnimation) trollAnimations.get(m.toString()).get(0))
                            .updateAnimation(((Troll) m).getBodyState());
                    ((ClubAnimation) trollAnimations.get(m.toString()).get(1))
                            .updateAnimation(((Troll) m).getBodyState());
                }
            }
        } else {
            spellDelta = 0;
            trollDelta = 0;
        }

        spriteDrawer.add(background, 0, SpriteDrawer.Level.REPLACE);

        for (MovableElement m : gameState.getMovableElements()) {
            if (m instanceof Spell) {
                spriteDrawer.add(spellAnimation.getSprite(spellDelta, true, (Spell) m), 4,
                        SpriteDrawer.Level.FIRST_AVAILABLE);
            } else if (m instanceof Troll) {
                //TODO Change to insert and 1
                spriteDrawer.add(((ClubAnimation) trollAnimations.get(m.toString()).get(1))
                        .getSprite(trollDelta, true, (Troll) m), 1, SpriteDrawer.Level.FIRST_AVAILABLE);
                spriteDrawer.add(((TrollAnimation) trollAnimations.get(m.toString()).get(0))
                        .getSprite(trollDelta, true, (Troll) m), 2, SpriteDrawer.Level.FIRST_AVAILABLE);

            }
        }

        spriteDrawer.add(sword, 1, SpriteDrawer.Level.FIRST_AVAILABLE);
        spriteDrawer.add(player, 2, SpriteDrawer.Level.FIRST_AVAILABLE);

        //Prepare ShapeRenderer
        shapeRenderer.setProjectionMatrix(camera.getCameraCombined());
        shapeRenderer.setAutoShapeType(true);
        shapeRenderer.begin();

        //Draw sprites
        batch.setProjectionMatrix(camera.getCameraCombined());
        spriteDrawer.draw(batch);

        //First draw level
        DebugDrawer.drawLevel(shapeRenderer, gameState.getLevel());

        //Draw the other things on top
//        for (MovableElement m : gameState.getMovableElements()) {
//            DebugDrawer.drawMovable(shapeRenderer, m);
//        }


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

    private void createSprites(GameState gameState) {
        background = new Sprite(new Texture(Gdx.files.internal("textures/" + gameState.getLevel().getBackground())));
        background.setSize(gameState.getLevel().getLevelWidth(), gameState.getLevel().getLevelHeight());

        playerAnimation = new PlayerAnimation(gameState.getPlayer().getBodyState());
        swordAnimation = new SwordAnimation(gameState.getPlayer().getBodyState());
        spellAnimation = new SpellAnimation();
        trollAnimations = new ObjectMap<>();

        for (MovableElement m : gameState.getMovableElements()) {
            if (m instanceof Troll) {
                TrollAnimation t = new TrollAnimation(((Troll) m).getBodyState());
                ClubAnimation c = new ClubAnimation(((Troll) m).getBodyState());
                Array<ITLAnimation> array = new Array<>();
                array.add(t);
                array.add(c);
                trollAnimations.put(m.toString(), array);
            }
        }
    }

    public void reset(GameState gameState) {
        createSprites(gameState);
    }

    @Override
    public void resize(int width, int height) {
        camera.resize(height);
    }

    @Override
    public void dispose() {
        background.getTexture().dispose();
        player.getTexture().dispose();
        batch.dispose();
        shapeRenderer.dispose();
        playerAnimation.dispose();
        swordAnimation.dispose();
    }
}
