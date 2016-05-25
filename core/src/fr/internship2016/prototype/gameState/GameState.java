package fr.internship2016.prototype.gameState;

import com.badlogic.gdx.utils.Array;
import fr.internship2016.prototype.engine.input.Action;
import fr.internship2016.prototype.gameState.levels.Level;
import fr.internship2016.prototype.gameState.movable.MovableElement;
import fr.internship2016.prototype.gameState.movable.bodies.BodyElement;
import fr.internship2016.prototype.gameState.movable.bodies.Player;
import fr.internship2016.prototype.gameState.movable.bodies.enemies.Enemy;
import fr.internship2016.prototype.gameState.movable.spells.Spell;
import fr.internship2016.prototype.gameState.movable.spells.SpellType;
import fr.internship2016.prototype.gameState.utils.CollisionDetector;

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
    //Spell collection for collisions
    private Array<Spell> spells;

    public GameState() {
        //Declare movableElements & spells array
        movableElements = new Array<>();
        spells = new Array<>();

        //Declare level
        level = new Level(100f, 10f, 1f, Level.DEFAULT_GRAVITY, movableElements);
        //Declare player
        player = new Player(Player.PLAYER_START, level.getLevelGroundHeight(), Player.WIDTH_PLAYER,
                Player.HEIGHT_PLAYER, Player.VELOCITY_X_PLAYER, Player.VELOCITY_Y_PLAYER, level.getLevelGravity());
        movableElements.add(player);
    }

    public void update(float delta) {
        for (MovableElement m : movableElements) {
            m.update(level);
            //Check collisions
            //Spells & every BodyElement
            if (!(m instanceof Spell) && m instanceof BodyElement) {
                for (Spell s : spells) {
                    if (m != s.getFire() && CollisionDetector.isCollision(s, m)) {
                        ((BodyElement) m).hit(s.getDmg());
                        ((BodyElement) m).knockBack(s.getDirection());
                        s.hasHit();
                    }
                }
            }

            //Player weapon & every enemy
            //If enemy has weapon and player
            //TODO Add range detection
            if (m instanceof Enemy) {
                //If player has weapon
                if (player.hasWeapon() && player.isAttacking()) {
                    if (CollisionDetector.isCollision(player.getWeapon(), m)) {
                        ((Enemy) m).hit(player.getWeapon().getType().getDmg());
                        ((Enemy) m).knockBack(player.getFacing());
                    }
                }

                if (((Enemy) m).hasWeapon() && ((Enemy) m).isAttacking()) {
                    if (CollisionDetector.isCollision(((Enemy) m).getWeapon(), player)) {
                        player.hit(((Enemy) m).getWeapon().getType().getDmg());
                    }
                }
            }

            //Check disappearing
            if (m instanceof Spell && ((Spell) m).isDisappear()) {
                movableElements.removeValue(m, false);
                spells.removeValue((Spell) m, false);
            } else if (m instanceof Enemy && ((Enemy) m).getLife() == 0) {
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

    public void attack() {
        player.attack();
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
            spells.add(spell);
        }
    }

    public void useInventory(String objectToString) {
        player.useObject(objectToString);
    }

    public void reset() {
        level.reset(movableElements);
        player.reset(level);
        movableElements.add(player);
    }
    //endregion
}
