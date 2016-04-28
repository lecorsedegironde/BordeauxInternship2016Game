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
    public static final int DEFAULT_LIFE = 100;
    public static final int DEFAULT_MAGIC = 100;
    public static final double MAGIC_REFILL = 0.25;


    //Troll
    public static final float WIDTH_TROLL = 1.5f;
    public static final float HEIGHT_TROLL = 3f;
    public static final int HIT_TROLL = 3;

    //Spell
    public static final float SIDE_SPELL = 0.2f;
    public static final float VELOCITY_X_SPELL_FIRE = 0.30f;
    public static final float FALL_SPELL = -3f;
    public static final int SPELL_REFILL = 250;
    public static final int SPELL_COST = 30;

    public static final double FIRE_SPELL_DMG = 0.25;


    //Weapons
    public static final float SWORD_WIDTH = 0.1f;
    public static final float SWORD_HEIGHT = 1.75f;
    public static final float SWORD_REFILL_TIME = 750;
    public static final int SWORD_MAX_ROTATE = 90;
    public static final int SWORD_ROTATE_STEP = 5;

    public static final float CLUB_WIDTH = 0.2f;
    public static final float CLUB_HEIGHT = 2.5f;
    public static final float CLUB_REFILL_TIME = 1000;
    public static final int CLUB_MAX_ROTATE = 120;
    public static final int CLUB_ROTATE_STEP = 2;

    //Forces
    public static final float GRAVITY = -0.047f;
    public static final float VELOCITY_X_PLAYER = 0.15f;
    public static final float VELOCITY_Y_PLAYER = 0.45f;
    public static final float VELOCITY_X_TROLL = 0.05f;
    public static final float VELOCITY_Y_TROLL = 0.0f;

    //Controls
    public static final int LEFT = Input.Keys.Q;
    public static final int RIGHT = Input.Keys.D;
    public static final int JUMP = Input.Keys.Z;
    public static final int ATTACK = Input.Keys.SPACE;
    public static final int INVISIBILITY = Input.Keys.SHIFT_LEFT;
    public static final int FIRE_SPELL_1 = Input.Keys.C;
}
