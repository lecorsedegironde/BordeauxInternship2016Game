package fr.internship2016.prototype;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

public class PrototypeGame extends ApplicationAdapter {
    private static final int WIDTH_PLAYER = 25;
    private static final int HEIGHT_PLAYER = 100;
    private static final int LEFT = Input.Keys.Q;
    private static final int RIGHT = Input.Keys.D;
    private ShapeRenderer renderer;
    private OrthographicCamera camera;
    private Player player;


    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        renderer = new ShapeRenderer();
        player = new Player(10, 10, WIDTH_PLAYER, HEIGHT_PLAYER);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(0, 0, 0, 1);
        renderer.rect(player.getX(), player.getY(), player.getW(), player.getH());
        renderer.end();

        //Movement
        float dist = Player.PLAYER_SPEED * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(LEFT)) {
            player.moveLeft(dist);
        }

        if (Gdx.input.isKeyPressed(RIGHT)) {
            player.moveRight(dist);
        }
    }
}
