package fr.internship2016.prototype.gameState;

import com.badlogic.gdx.utils.Array;
import fr.internship2016.prototype.gameState.levels.Level;
import fr.internship2016.prototype.gameState.movable.MovableElement;
import fr.internship2016.prototype.gameState.movable.Player;
import fr.internship2016.prototype.gameState.utils.Direction;

/**
 * Created by bastien on 13/05/16.
 * //TODO Review this is a testing class need to be reviewed
 */
public class GameState {

    //Store player just to provide access
    private Player player;
    private Array<MovableElement> movableElements;
    private Level level;

    public GameState() {

        //Declare movableElements array
        movableElements = new Array<>();

        //Declare level
        level = new Level(100f, 10f, 1f, Level.DEFAULT_GRAVITY);
        //Declare player
        player = new Player(Player.PLAYER_START, level.getLevelGroundHeight(), Player.WIDTH_PLAYER,
                Player.HEIGHT_PLAYER, Player.VELOCITY_X_PLAYER, Player.VELOCITY_Y_PLAYER, level.getLevelGravity());
        movableElements.add(player);
    }

    public void update(float delta) {
        //TODO This is the update function
        player.update(level);
    }

    //region getters

    public Player getPlayer() {
        return player;
    }

    public Level getLevel() {
        return level;
    }

    public Array<MovableElement> getMovableElements() {
        return movableElements;
    }
    //endregion

    //region Inputs
    public void moveRight() {
        player.moveRight();
    }

    public void moveLeft() {
        player.moveLeft();
    }

    public void stopMovement() {
        player.stopMovement();
    }

    public void invisibility() {
        player.startInvisibility();
    }

    public void jump() {
        player.jump();
    }

    public void reset() {
        player.reset();
    }
    //endregion
}
