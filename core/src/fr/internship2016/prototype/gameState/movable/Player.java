package fr.internship2016.prototype.gameState.movable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import fr.internship2016.prototype.gameState.levels.Level;
import fr.internship2016.prototype.gameState.movable.interfaces.*;
import fr.internship2016.prototype.gameState.utils.Direction;

/**
 * Created by bastien on 13/05/16.
 */
public class Player extends BodyElement implements Armed, Spelled, Inventory, Invisibility {

    //region Constants
    //Size
    public static final float WIDTH_PLAYER = 1f;
    public static final float HEIGHT_PLAYER = 2f;
    //Position
    public static final float PLAYER_START = 0.5f;
    //Moving forces
    public static final float VELOCITY_X_PLAYER = 0.15f;
    public static final float VELOCITY_Y_PLAYER = 0.45f;
    //Utils
    private static final int INVISIBILITY_DURATION = 2000;
    private static final int INVISIBILITY_REFILL = 5000;
    private static final int DEFAULT_LIFE = 100;
    private static final int DEFAULT_MANA = 100;
    private static final double MAGIC_REFILL = 0.25;
    //endregion

    //region Fields
    //Inventory
    private Array<Object> inventory;

    //Invisibility
    private boolean invisible;
    private boolean canBeInvisible;
    private long invisibilityTime;

    //Life and Mana
    private float life;
    private float mana;
    //endregion

    public Player(float x, float y, float width, float height, float velocityX,
                  float velocityY, float gravity) {
        super(x, y, width, height, velocityX, velocityY, gravity);

        //Inventory
        inventory = new Array<>();
        //TODO Weapon

        //Invisibility
        invisible = false;
        canBeInvisible = true;

        //Life and mana
        life = DEFAULT_LIFE;
        mana = DEFAULT_MANA;
    }

    @Override
    public void update(Level level) {
        //TODO Add management of different level ground height
        //Do jumping first to prevent conflicts
        if (jumping && onGround) {
            translate(0, getY() + velocityY);
        }

        //Update using super
        super.update(level);

        //Regain magic points if loosed
        autoMagicPointRegain();

        //Invisibility
        if (invisible && TimeUtils.timeSinceMillis(invisibilityTime) > INVISIBILITY_DURATION) {
            invisible = false;
            invisibilityTime = TimeUtils.millis();
        }
        if (TimeUtils.timeSinceMillis(invisibilityTime) > INVISIBILITY_REFILL) {
            canBeInvisible = true;
        }

        //Logs
        Gdx.app.log("Player", "Position: (" + getX() + ", " + getY() + ")");
        Gdx.app.log("Player", "Speed: (" + getVelocityX() + ", " + getVelocityY() + ")");
        Gdx.app.log("Player", "Direction: " + direction);
        Gdx.app.log("Player", "Facing: " + direction);
        Gdx.app.log("Player", "Jump: " + jumping + " On ground: " + onGround);
        Gdx.app.log("Player", "Invisibility: " + invisible);
        Gdx.app.log("Player", "Can be invisible: " + canBeInvisible);
        Gdx.app.log("Player", "Life: " + life + " Mana: " + mana);

    }

    public void reset() {
        stopMovement();
        setPosition(Player.PLAYER_START, 1f);
        setFacing(Direction.RIGHT);
        invisible = false;
        canBeInvisible = true;
        setChanged();
        notifyObservers();
    }

    //region Movement
    @Override
    public void moveRight() {
        super.moveRight();
        facing = Direction.RIGHT;
    }

    @Override
    public void moveLeft() {
        super.moveLeft();
        facing = Direction.LEFT;
    }
    //endregion

    //region Hit
    @Override
    public void hit(float dmg) {
        life -= dmg;
    }
    //endregion

    //region Invisibility
    public boolean isInvisible() {
        return invisible;
    }

    public boolean canBeInvisible() {
        return canBeInvisible;
    }

    public void startInvisibility() {
        if (!invisible && canBeInvisible) {
            invisible = true;
            canBeInvisible = false;
            invisibilityTime = TimeUtils.millis();
        }
    }
    //endregion

    //region Modifiers life and mana
    public void gainLife(double life) {
        if (life > 0) {
            this.life += life;
        }
    }

    public void looseLife(double life) {
        if (life > 0) {
            this.life -= life;
            //No negative life
            if (this.life < 0) this.life = 0;
        }
    }

    public float getLife() {
        return life;
    }

    public void setLife(float life) {
        if (life >= 0) this.life = life;
    }

    private void autoMagicPointRegain() {
        if (mana <= DEFAULT_MANA - MAGIC_REFILL) {
            mana += MAGIC_REFILL;
        } else if (mana > DEFAULT_MANA - MAGIC_REFILL
                && mana < DEFAULT_MANA) {
            mana = DEFAULT_MANA;
        }
    }

    public float getMana() {
        return mana;
    }

    public void setMana(float mana) {
        if (mana >= 0) this.mana = mana;
    }
    //endregion

    //region Inventory
    @Override
    public boolean isInInventory(Object o) {
        return inventory.contains(o, false);
    }

    @Override
    public void addToInventory(Object o) {
        inventory.add(o);
    }

    @Override
    public Array<Object> getInventory() {
        return inventory;
    }

    @Override
    public boolean isInventoryEmpty() {
        return inventory.size == 0;
    }
//endregion

    //region Knock-back
    @Override
    public void knockBack() {
        //TODO
    }
    //endregion
}
