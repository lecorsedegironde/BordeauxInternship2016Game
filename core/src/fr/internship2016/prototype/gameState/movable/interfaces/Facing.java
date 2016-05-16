package fr.internship2016.prototype.gameState.movable.interfaces;

import fr.internship2016.prototype.gameState.utils.Direction;

/**
 * Created by bastien on 13/05/16.
 */
public interface Facing {
    void setFacing(Direction d);
    Direction getFacing();
}
