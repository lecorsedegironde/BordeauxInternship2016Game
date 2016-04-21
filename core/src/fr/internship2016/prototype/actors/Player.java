package fr.internship2016.prototype.actors;

import com.badlogic.gdx.physics.box2d.Body;
import fr.internship2016.prototype.box2d.PlayerUserData;
import fr.internship2016.prototype.box2d.UserData;
import fr.internship2016.prototype.utils.Constants;

/**
 * Created by bastien on 21/04/16.
 */
public class Player extends GameActor {

    private boolean walkRight;
    private boolean walkLeft;
    private boolean jump;

    private boolean jumping;

    public Player(Body body) {
        super(body);
    }

    @Override
    public UserData getUserData() {
        return userData;
    }

    public void walkRight() {
        if (body.getLinearVelocity().x < Constants.PLAYER_MAX_VELOCITY) {
            body.applyLinearImpulse(((PlayerUserData) getUserData()).getWalkingRightLinearImpulse(),
                    body.getWorldCenter(), true);
        }
    }

    public void walkLeft() {
        if (body.getLinearVelocity().x > -Constants.PLAYER_MAX_VELOCITY) {
            body.applyLinearImpulse(((PlayerUserData) getUserData()).getWalkingLeftLinearImpulse(),
                    body.getWorldCenter(), true);
        }
    }

    public void stopMovement() {
        if (body.getLinearVelocity().y == 0f) {
            body.setLinearVelocity(0f, 0f);
        }
    }

    public void jump() {

        if (!jumping) {
            body.applyLinearImpulse(((PlayerUserData) getUserData()).getJumpingLinearImpulse(),
                    body.getWorldCenter(), true);
            jumping = true;
            jump = false;
        }

    }

    public void landed() {
        jumping = false;
    }

    public boolean isJump() {
        return jump;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public boolean isWalkRight() {
        return walkRight;
    }

    public void setWalkRight(boolean walkRight) {
        this.walkRight = walkRight;
    }

    public boolean isWalkLeft() {
        return walkLeft;
    }

    public void setWalkLeft(boolean walkLeft) {
        this.walkLeft = walkLeft;
    }
}
