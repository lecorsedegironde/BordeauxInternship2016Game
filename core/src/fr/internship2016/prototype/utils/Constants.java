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
    public static final float WIDTH_PLAYER = 1f;
    public static final float HEIGHT_PLAYER = 2f;

    //Forces
    public static final float GRAVITY = -0.047f;
    public static final float VELOCITY_X_PLAYER = 0.15f;
    public static final float VELOCITY_Y_PLAYER = 0.45f;

    //Controls
    public static final int LEFT = Input.Keys.Q;
    public static final int RIGHT = Input.Keys.D;
    public static final int JUMP = Input.Keys.Z;

}
