package fr.internship2016.prototype.gameState;

import com.badlogic.gdx.utils.Array;
import fr.internship2016.prototype.engine.Action;
import fr.internship2016.prototype.gameState.levels.Level;
import fr.internship2016.prototype.gameState.movable.MovableElement;
import fr.internship2016.prototype.gameState.movable.bodies.Player;
import fr.internship2016.prototype.gameState.movable.spells.Spell;
import fr.internship2016.prototype.gameState.movable.spells.SpellType;

/**
 * Created by bastien on 13/05/16.
 * //TODO Review this is a testing class need to be reviewed
 */
public class GameState {

    //Store player just to provide access
    private Player player;
    private Array<MovableElement> movableElements;
    private Level level;

    //Reusable spell object
    private Spell spell;

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
        for (MovableElement m : movableElements) {
            m.update(level);
            //Check disappearing
            if (m instanceof Spell && ((Spell) m).isDisappear()) {
                movableElements.removeValue(m, false);
            }
        }
    }

    //region Getters

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

    public void fireSpell(Action spellAction) {
        SpellType spellType = SpellType.NO_SPELL;
        switch (spellAction) {
            case SPELL_ONE:
                spellType = player.fireSpell1();
                break;
            case SPELL_TWO:
                spellType = player.fireSpell2();
                break;
            case SPELL_THREE:
                spellType = player.fireSpell3();
                break;
        }

        if (spellType != SpellType.NO_SPELL) {
            spell = new Spell(player, spellType);
            movableElements.add(spell);
        }
    }

    public void reset() {
        player.reset();
    }
    //endregion
}
