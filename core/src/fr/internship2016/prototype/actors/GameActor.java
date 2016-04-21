package fr.internship2016.prototype.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import fr.internship2016.prototype.box2d.UserData;

/**
 * Created by bastien on 21/04/16.
 */
public abstract class GameActor extends Actor {

    protected Body body;
    protected UserData userData;

    public GameActor(Body body) {
        this.body = body;
        this.userData = (UserData) body.getUserData();
    }

    public abstract UserData getUserData();
}
