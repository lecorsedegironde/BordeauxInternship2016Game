package fr.internship2016.prototype.box2d;

import com.badlogic.gdx.math.Vector2;
import fr.internship2016.prototype.enums.UserDataType;
import fr.internship2016.prototype.utils.Constants;

/**
 * Created by bastien on 21/04/16.
 */
public class PlayerUserData extends UserData {

    private Vector2 walkingRightLinearImpulse;
    private Vector2 walkingLeftLinearImpulse;
    private Vector2 jumpingLinearImpulse;

    public PlayerUserData() {
        super();

        walkingRightLinearImpulse = Constants.PLAYER_WALKING_RIGHT_LINEAR_IMPULSE;
        walkingLeftLinearImpulse = Constants.PLAYER_WALKING_LEFT_LINEAR_IMPULSE;
        jumpingLinearImpulse = Constants.PLAYER_JUMPING_LINEAR_IMPULSE;

        userDataType = UserDataType.PLAYER;
    }

    public Vector2 getWalkingRightLinearImpulse() {
        return walkingRightLinearImpulse;
    }

    public void setWalkingRightLinearImpulse(Vector2 walkingRightLinearImpulse) {
        this.walkingRightLinearImpulse = walkingRightLinearImpulse;
    }

    public Vector2 getWalkingLeftLinearImpulse() {
        return walkingLeftLinearImpulse;
    }

    public void setWalkingLeftLinearImpulse(Vector2 walkingLeftLinearImpulse) {
        this.walkingLeftLinearImpulse = walkingLeftLinearImpulse;
    }

    public Vector2 getJumpingLinearImpulse() {
        return jumpingLinearImpulse;
    }

    public void setJumpingLinearImpulse(Vector2 jumpingLinearImpulse) {
        this.jumpingLinearImpulse = jumpingLinearImpulse;
    }
}
