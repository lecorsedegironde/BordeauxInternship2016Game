package fr.internship2016.prototype.actors;

import com.badlogic.gdx.physics.box2d.Body;
import fr.internship2016.prototype.box2d.UserData;

/**
 * Created by bastien on 21/04/16.
 */
public class Ground extends GameActor {

    public Ground(Body body) {
        super(body);
    }

    @Override
    public UserData getUserData() {
        return userData;
    }

}
