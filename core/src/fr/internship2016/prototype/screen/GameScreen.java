package fr.internship2016.prototype.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import fr.internship2016.prototype.movable.armed.ArmedElement;
import fr.internship2016.prototype.movable.armed.Player;
import fr.internship2016.prototype.movable.armed.Troll;
import fr.internship2016.prototype.movable.spells.FireSpell;
import fr.internship2016.prototype.movable.spells.Spell;
import fr.internship2016.prototype.screen.ui.GameUI;
import fr.internship2016.prototype.utils.CollisionDetector;
import fr.internship2016.prototype.utils.EnemiesAI;
import fr.internship2016.prototype.utils.camera.ITLCamera;

import static fr.internship2016.prototype.utils.Constants.*;

/**
 * Created by bastien on 21/04/16.
 * The GameScreen
 */
//TODO
public class GameScreen implements Screen {

    //Camera
    private ITLCamera camera;
    //UI
    private final GameUI gameUI;

    //Draw
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private Sprite sprite;

    //Player
    private Player player;

    //Reusable spell container for adding spells to array
    private Spell addSpell;

    //Arrays of enemies and spells
    private Array<ArmedElement> enemies;
    private Array<Spell> spells;

    public GameScreen() {
        //Rendering
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        sprite = new Sprite(new Texture(Gdx.files.internal("laser.jpg")));
        sprite.setSize(WORLD_WIDTH, WORLD_HEIGHT);

        //Camera
        camera = new ITLCamera(0f, 0f, WORLD_WIDTH, 0.002, WORLD_WIDTH, WORLD_HEIGHT);

        //UI
        gameUI = new GameUI();
        Gdx.input.setInputProcessor(gameUI.getStage());

        //Player, enemies & spells
        enemies = new Array<>();
        spells = new Array<>();

        player = new Player(PLAYER_START, GROUND_HEIGHT, WIDTH_PLAYER, HEIGHT_PLAYER,
                VELOCITY_X_PLAYER, VELOCITY_Y_PLAYER, true);
        //Add player as observer to move camera
        player.addObserver(camera);

        Troll troll = new Troll(12f, GROUND_HEIGHT, WIDTH_TROLL, HEIGHT_TROLL,
                VELOCITY_X_TROLL, VELOCITY_Y_TROLL);
        troll.moveLeft();
        enemies.add(troll);
    }

    @Override
    public void render(float delta) {
        //Clear screen
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Player update
        player.update();
        player.setCanStopMovement(true);

        //Spells update
        for (Spell s : spells) {
            //Update spell
            s.update();
            //Is the spell still active?
            if (s.isDisappear()) {
                spells.removeValue(s, true);
            }
        }

        //TODO: Review
        //Enemies update
        for (ArmedElement e : enemies) {
            //enemies IA
            EnemiesAI.goToPlayer(e, player);
            EnemiesAI.enemyReaction(e, player);
            //Update enemy
            e.update();
            //Does it collide with player weapon
            if (player.isAttacking()) {
                if (CollisionDetector.isCollision(player.getWeapon(), e) && !player.getWeapon().hasHit()) {
                    e.hitWeapon();
                    player.getWeapon().hit();
                }
            }
            //Does it collide with a spell
            if (spells.size > 0) {
                for (Spell s : spells) {
                    if (CollisionDetector.isCollision(s, e)) {
                        e.hitSpell(s.getDmg());
                        s.hasHit();
                    }
                }
            }
            //Is it still alive?
            if (e.getLife() <= 0) {
                enemies.removeValue(e, true);
            }

        }

        //Activate cam viewport
        camera.getViewport().apply();

        //Draw background
        batch.setProjectionMatrix(camera.getCameraCombined());
        batch.begin();
        sprite.draw(batch);
        batch.end();

        //Draw bodies blocks (Filled)
        shapeRenderer.setProjectionMatrix(camera.getCameraCombined());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        //Player
        if (!player.isInvisible()) {
            if (player.canBeInvisible()) {
                shapeRenderer.setColor(Color.BLUE);
            } else {
                shapeRenderer.setColor(Color.CYAN);
            }
            shapeRenderer.rect(player.getX(), player.getY(), player.getW(), player.getH());
        }
        //Enemies
        shapeRenderer.setColor(Color.SCARLET);
        for (ArmedElement e : enemies) {
            shapeRenderer.rect(e.getX(), e.getY(), e.getW(), e.getH());
        }
        //Spells
        for (Spell s : spells) {
            if (s instanceof FireSpell) {
                shapeRenderer.setColor(Color.MAGENTA);
            } else {
                shapeRenderer.setColor(Color.GOLD);
            }
            shapeRenderer.rect(s.getX(), s.getY(), s.getW(), s.getH());
        }
        shapeRenderer.end();

        //Draw weapons and bodies contours
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        //Player
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(player.getX(), player.getY(), player.getW(), player.getH());
        //Player weapon
        if (player.hasWeapon()) {
            shapeRenderer.setColor(Color.BLACK);
            shapeRenderer.polygon(player.getWeapon().getTransformedVertices());
        }
        //Enemies weapon
        for (ArmedElement e : enemies) {
            shapeRenderer.setColor(Color.BLACK);
            shapeRenderer.polygon(e.getWeapon().getTransformedVertices());
        }
        //Ground line
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.line(0, GROUND_HEIGHT, WORLD_WIDTH, GROUND_HEIGHT);
        shapeRenderer.end();

        //Draw UI
        gameUI.update(delta, player.getLife(), player.getMagicPoints());

        //Inputs events
        if (Gdx.input.isKeyPressed(RESET)) {
            restart();
        } else {

            if (Gdx.input.isKeyPressed(RIGHT)) {
                player.moveRight();
                player.setCanStopMovement(false);
            }
            if (Gdx.input.isKeyPressed(LEFT)) {
                player.moveLeft();
                player.setCanStopMovement(false);
            }
            if (Gdx.input.isKeyPressed(JUMP)) {
                player.jump();
                player.setCanStopMovement(false);
            }
            if (Gdx.input.isKeyPressed(ATTACK) && player.hasWeapon()) {
                player.attack();
            }
            if (Gdx.input.isKeyPressed(INVISIBILITY)) {
                player.startInvisibility();
            }
            if (Gdx.input.isKeyPressed(FIRE_SPELL_1)) {
                addSpell = player.fireSpell1();
                if (addSpell != null) {
                    //Add camera as spells observer to be able to destroy when it is no more visible
                    addSpell.addObserver(camera);
                    spells.add(addSpell);
                }
            }
            //Can the player be stopped?
            if (player.canStopMovement()) {
                player.stopMovement();
            }
        }
    }

    private void restart() {
        //Clear arrays
        spells.clear();
        enemies.clear();

        //Regenerate player
        //As we regenerated it we need to renew addObserver too
        player = new Player(PLAYER_START, GROUND_HEIGHT, WIDTH_PLAYER, HEIGHT_PLAYER,
                VELOCITY_X_PLAYER, VELOCITY_Y_PLAYER, true);
        player.addObserver(camera);

        //Regenerate troll
        Troll troll = new Troll(12f, GROUND_HEIGHT, WIDTH_TROLL, HEIGHT_TROLL,
                VELOCITY_X_TROLL, VELOCITY_Y_TROLL);
        troll.moveLeft();
        enemies.add(troll);

        //Reset camera too
        camera.reset();
    }

    @Override
    public void show() {
    }

    @Override
    public void resize(int width, int height) {
        camera.resize(height);
        gameUI.resize(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        sprite.getTexture().dispose();
        batch.dispose();
        shapeRenderer.dispose();
        gameUI.dispose();
    }
}
