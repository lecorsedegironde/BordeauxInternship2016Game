package fr.internship2016.prototype.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import fr.internship2016.prototype.movable.armed.ArmedElement;
import fr.internship2016.prototype.movable.armed.Player;
import fr.internship2016.prototype.movable.armed.Troll;
import fr.internship2016.prototype.movable.spells.FireSpell;
import fr.internship2016.prototype.movable.spells.Spell;
import fr.internship2016.prototype.utils.CollisionDetector;
import fr.internship2016.prototype.utils.EnemiesAI;

import static fr.internship2016.prototype.utils.Constants.*;

/**
 * Created by bastien on 21/04/16.
 * The GameScreen
 */
public class GameScreen implements Screen {

    //Camera & viewport
    private OrthographicCamera camera;
    private Viewport viewport;

    //Draw
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private Sprite sprite;

    //Player and reusable vector
    private Player player;
    private Vector2 playerPos;

    //Reusable spell container for adding spells to array
    private Spell addSpell;

    //Arrays of enemies and spells
    private Array<ArmedElement> enemies;
    private Array<Spell> spells;

    public GameScreen() {
        //Player position
        playerPos = new Vector2();

        //Rendering
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        sprite = new Sprite(new Texture(Gdx.files.internal("laser.jpg")));
        sprite.setSize(WORLD_WIDTH, WORLD_HEIGHT);

        //Camera
        float aspectRatio = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();
        camera = new OrthographicCamera();
        viewport = new FillViewport(WORLD_WIDTH * aspectRatio, WORLD_HEIGHT, camera);
        viewport.apply();
        camera.position.set(0, WORLD_HEIGHT / 2f, 0);
        camera.update();

        //Player, enemies & spells
        enemies = new Array<>();
        spells = new Array<>();

        player = new Player(0.5f, GROUND_HEIGHT, WIDTH_PLAYER, HEIGHT_PLAYER,
                VELOCITY_X_PLAYER, VELOCITY_Y_PLAYER, true);

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

        //Update camera pos
        playerPos.set(player.getX(), player.getY());
        viewport.project(playerPos);

        if (playerPos.x > Gdx.graphics.getWidth() * 0.75f) {
            camera.translate(VELOCITY_X_PLAYER, 0);
        } else if (playerPos.x < Gdx.graphics.getWidth() * 0.25f) {
            camera.translate(-VELOCITY_X_PLAYER, 0);
        }

        if (camera.position.x > WORLD_WIDTH - WORLD_WIDTH / 12f)
            camera.position.x = WORLD_WIDTH - WORLD_WIDTH / 12f;
        else if (camera.position.x < WORLD_WIDTH / 12f)
            camera.position.x = WORLD_WIDTH / 12f;

        camera.update();

        //Player update
        player.update();
        player.setCanStopMovement(true);

        //Spells update
        for (Spell s : spells) {
            //Update spell
            s.update(viewport);
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

        //Draw background
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        sprite.draw(batch);
        batch.end();

        //Draw bodies blocks (Filled)
        shapeRenderer.setProjectionMatrix(camera.combined);
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
            if (s instanceof FireSpell){
                shapeRenderer.setColor(Color.MAGENTA);
            }
            else{
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

        //Inputs events
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
                spells.add(addSpell);
            }
        }
        //Can the player be stopped?
        if (player.canStopMovement()) {
            player.stopMovement();
        }
    }

    @Override
    public void show() {
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
    }
}
