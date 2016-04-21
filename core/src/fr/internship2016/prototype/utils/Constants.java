package fr.internship2016.prototype.utils;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by bastien on 21/04/16.
 */
public class Constants {

    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 480;

    public static final Vector2 WORLD_GRAVITY = new Vector2(0, -10);

    //Ground
    public static final float GROUND_X = 0;
    public static final float GROUND_Y = 0;
    public static final float GROUND_WIDTH = 500f;
    public static final float GROUND_HEIGHT = 2f;
    public static final float GROUND_DENSITY = 0f;

    //Player
    //May be used to make the player fall faster
    public static final float PLAYER_GRAVITY_SCALE = 3f;
    public static final float PLAYER_X = 2;
    public static final float PLAYER_Y = GROUND_Y + GROUND_HEIGHT;
    public static final float PLAYER_WIDTH = 1f;
    public static final float PLAYER_HEIGHT = 2f;
    public static float PLAYER_DENSITY = 0.5f;
    public static float PLAYER_MAX_VELOCITY = 5f;
    public static final Vector2 PLAYER_JUMPING_LINEAR_IMPULSE = new Vector2(0, 13f);
    public static final Vector2 PLAYER_WALKING_RIGHT_LINEAR_IMPULSE = new Vector2(5f, 0f);
    public static final Vector2 PLAYER_WALKING_LEFT_LINEAR_IMPULSE = new Vector2(-5f, 0f);
}
