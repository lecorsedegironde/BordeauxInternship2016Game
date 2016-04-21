package fr.internship2016.prototype.utils;

import com.badlogic.gdx.physics.box2d.Body;
import fr.internship2016.prototype.box2d.UserData;
import fr.internship2016.prototype.enums.UserDataType;

/**
 * Created by bastien on 21/04/16.
 */
public class BodyUtils {

    public static boolean bodyIsPlayer(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.PLAYER;
    }

    public static boolean bodyIsGround(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.GROUND;
    }

}
