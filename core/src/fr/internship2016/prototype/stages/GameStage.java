package fr.internship2016.prototype.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import fr.internship2016.prototype.actors.Ground;
import fr.internship2016.prototype.actors.Player;
import fr.internship2016.prototype.utils.BodyUtils;
import fr.internship2016.prototype.utils.WorldUtils;

/**
 * Created by bastien on 21/04/16.
 */
public class GameStage extends Stage implements ContactListener {

    // This will be our viewport measurements while working with the debug renderer
    private static final int VIEWPORT_WIDTH = 20;
    private static final int VIEWPORT_HEIGHT = 13;

    private final float TIME_STEP = 1 / 300f;
    private float accumulator = 0f;

    //Keys typed
    //WARNING: AZERTY Keyboard
    private static final int RIGHT = Input.Keys.D;
    private static final int LEFT = Input.Keys.Q;
    private static final int JUMP = Input.Keys.Z;

    private OrthographicCamera camera;
    private Box2DDebugRenderer renderer;

    private World world;
    private Ground ground;
    private Player player;

    public GameStage() {
        setUpWorld();
        setUpCamera();
        setUpControl();
        renderer = new Box2DDebugRenderer();
    }

    private void setUpControl() {
        Gdx.input.setInputProcessor(this);
    }

    private void setUpWorld() {
        world = WorldUtils.createWorld();
        world.setContactListener(this);
        setUpGround();
        setUpPlayer();
    }

    private void setUpGround() {
        ground = new Ground(WorldUtils.createGround(world));
        addActor(ground);
    }

    private void setUpPlayer() {
        player = new Player(WorldUtils.createPlayer(world));
        addActor(player);
    }

    private void setUpCamera() {
        camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        //Fixed timestep
        accumulator += delta;

        while (accumulator >= delta) {
            world.step(TIME_STEP, 6, 2);
            accumulator -= TIME_STEP;
        }

        if (player.isWalkLeft())
            player.walkLeft();
        if (player.isWalkRight())
            player.walkRight();
        if (!player.isWalkRight() && !player.isWalkLeft())
            player.stopMovement();
        if (player.isJump())
            player.jump();


    }

    @Override
    public void draw() {
        super.draw();
        renderer.render(world, camera.combined);
    }

    @Override
    public boolean keyDown(int keyCode) {

        if (keyCode == RIGHT) {
            player.setWalkRight(true);
            player.setWalkLeft(false);
        }
        else if (keyCode == LEFT) {
            player.setWalkLeft(true);
            player.setWalkRight(false);
        }

        if (keyCode == JUMP)
            player.setJump(true);

        return true;
    }

    @Override
    public boolean keyUp(int keyCode) {

        if (keyCode == RIGHT)
            player.setWalkRight(false);
        if (keyCode == LEFT)
            player.setWalkLeft(false);

        return true;
    }

    /*
         * Contact Listener methods
         */
    @Override
    public void beginContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        if ((BodyUtils.bodyIsPlayer(a) && BodyUtils.bodyIsGround(b)) ||
                (BodyUtils.bodyIsGround(a) && BodyUtils.bodyIsPlayer(b))) {
            player.landed();
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
