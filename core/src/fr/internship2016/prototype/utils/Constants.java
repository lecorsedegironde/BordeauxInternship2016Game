package fr.internship2016.prototype.utils;

import com.badlogic.gdx.Input;

/**
 * Created by bastien on 23/04/16.
 * Constants
 */
public class Constants {

    //Screen
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 480;

    //World constants : Meters
    public static final float WORLD_WIDTH = 100;
    public static final float WORLD_HEIGHT = 10;
    public static final float GROUND_HEIGHT = 1;

    //Player
    public static final float WIDTH_PLAYER = 1f;
    public static final float HEIGHT_PLAYER = 2f;
    public static final int INVISIBILITY_DURATION = 2000;
    public static final int INVISIBILITY_REFILL = 5000;

    //Weapons
    public static final float SWORD_WIDTH = 0.1f;
    public static final float SWORD_HEIGHT = 1.75f;
    public static final float SWORD_ANGULAR_VELOCITY = 0.15f;

    //Forces
    public static final float GRAVITY = -0.047f;
    public static final float VELOCITY_X_PLAYER = 0.15f;
    public static final float VELOCITY_Y_PLAYER = 0.45f;

    //Controls
    public static final int LEFT = Input.Keys.Q;
    public static final int RIGHT = Input.Keys.D;
    public static final int JUMP = Input.Keys.Z;
    public static final int ATTACK = Input.Keys.SPACE;
    public static final int INVISIBILITY = Input.Keys.SHIFT_LEFT;
}
