package fr.internship2016.prototype.box2d;

import fr.internship2016.prototype.enums.UserDataType;

/**
 * Created by bastien on 21/04/16.
 */
public abstract class UserData {

    protected UserDataType userDataType;

    public UserData() {

    }

    public UserDataType getUserDataType() {
        return userDataType;
    }
}
