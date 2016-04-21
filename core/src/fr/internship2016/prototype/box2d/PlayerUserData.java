package fr.internship2016.prototype.box2d;

import com.badlogic.gdx.math.Vector2;
import fr.internship2016.prototype.enums.UserDataType;
import fr.internship2016.prototype.utils.Constants;

/**
 * Created by bastien on 21/04/16.
 */
public class PlayerUserData extends UserData {

    private Vector2 walkingRightVelocity;
    private Vector2 walkingLeftVelocity;
    private Vector2 jumpingLinearImpulse;

    public PlayerUserData() {
        super();

        walkingRightVelocity = Constants.PLAYER_WALKING_RIGHT_VELOCITY;
        walkingLeftVelocity = Constants.PLAYER_WALKING_LEFT_VELOCITY;
        jumpingLinearImpulse = Constants.PLAYER_JUMPING_LINEAR_IMPULSE;

        userDataType = UserDataType.PLAYER;
    }

    public Vector2 getWalkingRightVelocity() {
        return walkingRightVelocity;
    }

    public void setWalkingRightVelocity(Vector2 walkingRightVelocity) {
        this.walkingRightVelocity = walkingRightVelocity;
    }

    public Vector2 getWalkingLeftVelocity() {
        return walkingLeftVelocity;
    }

    public void setWalkingLeftVelocity(Vector2 walkingLeftVelocity) {
        this.walkingLeftVelocity = walkingLeftVelocity;
    }

    public Vector2 getJumpingLinearImpulse() {
        return jumpingLinearImpulse;
    }

    public void setJumpingLinearImpulse(Vector2 jumpingLinearImpulse) {
        this.jumpingLinearImpulse = jumpingLinearImpulse;
    }
}
