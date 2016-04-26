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
import fr.internship2016.prototype.movable.MovableElement;
import fr.internship2016.prototype.movable.Player;
import fr.internship2016.prototype.movable.Troll;
import fr.internship2016.prototype.utils.CollisionDetector;
import fr.internship2016.prototype.utils.EnemiesIA;

import static fr.internship2016.prototype.utils.Constants.*;

/**
 * Created by bastien on 21/04/16.
 * The GameScreen
 */
public class GameScreen implements Screen {

    //Camera used to see the world
    private OrthographicCamera camera;

    //Renderer to render our shapes

    //Tests
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private Sprite sprite;
    private Viewport viewport;

    private Player player;
    private Array<MovableElement> enemies;

    public GameScreen() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        sprite = new Sprite(new Texture(Gdx.files.internal("laser.jpg")));
        sprite.setSize(WORLD_WIDTH, WORLD_HEIGHT);

        float aspectRatio = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();

        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        viewport = new FillViewport(WORLD_WIDTH * aspectRatio, WORLD_HEIGHT, camera);
        viewport.apply();

        camera.position.set(WORLD_WIDTH / 12f, WORLD_HEIGHT / 2f, 0);

        camera.update();

        //Player & enemies
        player = new Player(0.5f, GROUND_HEIGHT, WIDTH_PLAYER, HEIGHT_PLAYER,
                VELOCITY_X_PLAYER, VELOCITY_Y_PLAYER, true);

        enemies = new Array<>();

        Troll troll = new Troll(12f, GROUND_HEIGHT, WIDTH_TROLL, HEIGHT_TROLL,
                VELOCITY_X_TROLL, VELOCITY_Y_TROLL);
        troll.moveLeft();

        enemies.add(troll);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Vector2 playerPos = new Vector2(player.getX(), player.getY());
        viewport.project(playerPos);

        if (playerPos.x > Gdx.graphics.getWidth() * 0.75f) {
            camera.translate(VELOCITY_X_PLAYER, 0);
        } else if (playerPos.x < Gdx.graphics.getHeight() * 0.25f) {
            camera.translate(-VELOCITY_X_PLAYER, 0);
        }

        if (camera.position.x > WORLD_WIDTH - WORLD_WIDTH / 12f)
            camera.position.x = WORLD_WIDTH - WORLD_WIDTH / 12f;
        else if (camera.position.x < WORLD_WIDTH / 12f)
            camera.position.x = WORLD_WIDTH / 12f;

        camera.update();

        player.update();
        player.setCanStopMovement(true);

        for (MovableElement e : enemies) {
            e.update();
        }

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        sprite.draw(batch);
        batch.end();

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
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
        for (MovableElement e : enemies) {
            shapeRenderer.rect(e.getX(), e.getY(), e.getW(), e.getH());
        }
        shapeRenderer.setColor(Color.CORAL);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(player.getX(), player.getY(), player.getW(), player.getH());
        if (player.hasWeapon()) {
            shapeRenderer.setColor(Color.BLACK);
            shapeRenderer.polygon(player.getWeapon().getTransformedVertices());
        }
        for (MovableElement e : enemies) {
            if (e instanceof Troll) {
                shapeRenderer.setColor(Color.BLACK);
                shapeRenderer.polygon(((Troll) e).getWeapon().getTransformedVertices());
            }
        }


        //The ground
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.line(0, GROUND_HEIGHT, WORLD_WIDTH, GROUND_HEIGHT);
        shapeRenderer.end();

        //TODO: Review
        for (MovableElement e : enemies) {
            if (player.isAttacking()) {
                if (e instanceof Troll
                        && CollisionDetector.isCollision(player.getWeapon(), e) && !player.getWeapon().hasHit()) {
                    ((Troll) e).hit();
                    player.getWeapon().hit();
                }
            }
        }

        //Are enemies alive?
        for (MovableElement e : enemies) {
            if (e instanceof Troll) {
                if (((Troll) e).getNumberHitLeft() == 0) {
                    enemies.removeValue(e, true);
                }
            }
        }

        //enemies IA
        for (MovableElement e : enemies) {
            if (e instanceof Troll)
                EnemiesIA.goToPlayer(e, player);
            EnemiesIA.enemyReaction(e, player);
        }

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
