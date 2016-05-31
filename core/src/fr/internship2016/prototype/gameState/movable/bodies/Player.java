package fr.internship2016.prototype.gameState.movable.bodies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import fr.internship2016.prototype.gameState.levels.Level;
import fr.internship2016.prototype.gameState.movable.interfaces.Armed;
import fr.internship2016.prototype.gameState.movable.interfaces.Inventory;
import fr.internship2016.prototype.gameState.movable.interfaces.Invisibility;
import fr.internship2016.prototype.gameState.movable.interfaces.Spelled;
import fr.internship2016.prototype.gameState.movable.spells.SpellType;
import fr.internship2016.prototype.gameState.utils.Direction;
import fr.internship2016.prototype.gameState.weapons.Weapon;
import fr.internship2016.prototype.gameState.weapons.WeaponFactory;

/**
 * Created by bastien on 13/05/16.
 */
public class Player extends BodyElement implements Armed, Spelled, Inventory, Invisibility {

    //region Constants
    //Size
    public static final float WIDTH_PLAYER = 1.5f;
    public static final float HEIGHT_PLAYER = 2f;
    //Position
    public static final float PLAYER_START = 0.5f;
    //Moving forces
    public static final float VELOCITY_X_PLAYER = 0.15f;
    public static final float VELOCITY_Y_PLAYER = 0.6f;
    //Invisibility
    private static final int INVISIBILITY_DURATION = 2000;
    private static final int INVISIBILITY_REFILL = 5000;

    //Life, Mana & Utilisation
    private static final int DEFAULT_LIFE = 100;
    private static final int DEFAULT_MANA = 100;
    private static final double MANA_REFILL = 0.5;
    private static final int SPELL_FIRE_REFILL = 250;
    private static final int SPELL_MANA_COST = 30;
    //endregion

    //region Fields
    //Inventory
    private Array<Object> inventory;
    private WeaponFactory weaponFactory;
    private Weapon weapon = null;

    //Invisibility
    private boolean invisible;
    private boolean canBeInvisible;
    private long invisibilityTime;

    //Life, Mana
    private double life;
    private double mana;

    //Spells
    private long lastFireS1;
    private SpellType spell1;
    private long lastFireS2;
    private SpellType spell2;
    private long lastFireS3;
    private SpellType spell3;
    //endregion

    public Player(float x, float y, float width, float height, float velocityX,
                  float velocityY, float gravity) {
        super(x, y, width, height, velocityX, velocityY, gravity);

        //Inventory
        inventory = new Array<>();

        //Invisibility
        invisible = false;
        canBeInvisible = true;

        //Default facing
        facing = Direction.RIGHT;

        //Life and mana
        life = DEFAULT_LIFE;
        mana = DEFAULT_MANA;

        //Spells
        spell1 = SpellType.FIRE_SPELL;
        spell2 = SpellType.NO_SPELL;
        spell3 = SpellType.NO_SPELL;

        //Add weapon (default: Sword & Spear in inventory)
        weaponFactory = new WeaponFactory(this);
        manageWeapon();
    }

    @Override
    public void update(Level level) {
        //TODO Add management of different level ground height
        //Update using super
        super.update(level);

        //Do not forget to update weapon too
        if (hasWeapon()) weapon.update();

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
        //*
        Gdx.app.log("Player", "Position: (" + getX() + ", " + getY() + ")");
        Gdx.app.log("Player", "Speed: (" + getVelocityX() + ", " + getVelocityY() + ")");
        Gdx.app.log("Player", "Direction: " + direction);
        Gdx.app.log("Player", "Facing: " + facing);
        Gdx.app.log("Player", "Weapon: " + (hasWeapon() ? weapon.getType().getName() : "empty"));
        if (hasWeapon()) Gdx.app.log("Player", "Is attacking: " + weapon.isAttack());
        Gdx.app.log("Player", "Jump: " + jumping + " On ground: " + onGround);
        Gdx.app.log("Player", "Invisibility: " + invisible);
        Gdx.app.log("Player", "Can be invisible: " + canBeInvisible);
        Gdx.app.log("Player", "Life: " + life + " Mana: " + mana);
        //*/
    }

    public void reset(Level level) {
        stopMovement();
        setPosition(Player.PLAYER_START, level.getLevelGroundHeight());
        setFacing(Direction.RIGHT);
        setBodyState(BodiesStates.IDLE);
        invisible = false;
        canBeInvisible = true;
        life = DEFAULT_LIFE;
        mana = DEFAULT_MANA;
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
    public void hit(double dmg) {
        life -= dmg;
    }
    //endregion

    //region Invisibility
    @Override
    public boolean isInvisible() {
        return invisible;
    }

    @Override
    public boolean canBeInvisible() {
        return canBeInvisible;
    }

    @Override
    public void startInvisibility() {
        if (!invisible && canBeInvisible) {
            invisible = true;
            canBeInvisible = false;
            invisibilityTime = TimeUtils.millis();
        }
    }
    //endregion

    //region Life, Mana
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

    public double getLife() {
        return life;
    }

    public void setLife(double life) {
        if (life >= 0) this.life = life;
    }

    private void autoMagicPointRegain() {
        if (mana <= DEFAULT_MANA - MANA_REFILL) {
            mana += MANA_REFILL;
        } else if (mana > DEFAULT_MANA - MANA_REFILL
                && mana < DEFAULT_MANA) {
            mana = DEFAULT_MANA;
        }
    }

    public double getMana() {
        return mana;
    }

    public void setMana(double mana) {
        if (mana >= 0) this.mana = mana;
    }
    //endregion

    //region Spells
    public SpellType getSpell1() {
        return spell1;
    }

    public void setSpell1(SpellType spell1) {
        this.spell1 = spell1;
    }

    @Override
    public SpellType fireSpell1() {
        if (spell1 != SpellType.NO_SPELL && TimeUtils.timeSinceMillis(lastFireS1) > SPELL_FIRE_REFILL
                && mana > SPELL_MANA_COST) {
            lastFireS1 = TimeUtils.millis();
            mana -= SPELL_MANA_COST;
            bodyState = BodiesStates.FIRE_SPELL;
            return spell1;
        } else {
            return SpellType.NO_SPELL;
        }
    }

    public SpellType getSpell2() {
        return spell2;
    }

    public void setSpell2(SpellType spell2) {
        this.spell2 = spell2;
    }

    @Override
    public SpellType fireSpell2() {
        if (spell2 != SpellType.NO_SPELL && TimeUtils.timeSinceMillis(lastFireS2) > SPELL_FIRE_REFILL
                && mana > SPELL_MANA_COST) {
            lastFireS2 = TimeUtils.millis();
            mana -= SPELL_MANA_COST;
            bodyState = BodiesStates.FIRE_SPELL;
            return spell2;
        } else {
            return SpellType.NO_SPELL;
        }
    }

    public SpellType getSpell3() {
        return spell3;
    }

    public void setSpell3(SpellType spell3) {
        this.spell3 = spell3;
    }

    @Override
    public SpellType fireSpell3() {
        if (spell3 != SpellType.NO_SPELL && TimeUtils.timeSinceMillis(lastFireS3) > SPELL_FIRE_REFILL
                && mana > SPELL_MANA_COST) {
            lastFireS3 = TimeUtils.millis();
            mana -= SPELL_MANA_COST;
            bodyState = BodiesStates.FIRE_SPELL;
            return spell3;
        } else {
            return SpellType.NO_SPELL;
        }
    }
    //endregion

    //region Inventory
    @Override
    public boolean isInInventory(Object o) {
        return inventory.contains(o, false);
    }

    @Override
    public void addToInventory(Object o) {
        if (!isInInventory(o)) inventory.add(o);
    }

    @Override
    public Array<Object> getInventory() {
        return inventory;
    }

    @Override
    public boolean isInventoryEmpty() {
        return inventory.size == 0;
    }

    @Override
    public void useObject(String objectToString) {
        for (Object o : inventory) {
            if (o.toString().equals(objectToString)) {
                //The object to use
                if (o instanceof Weapon) {
                    //If this object is a weapon
                    weapon = (Weapon) o;
                }
            }
        }
    }

    //endregion

    //region Weapon
    @Override
    public boolean hasWeapon() {
        return weapon != null;
    }

    @Override
    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public void manageWeapon() {
        Array<String> weaponAvailable = weaponFactory.getAvailableWeapon();

        for (String weapon : weaponAvailable) {
            setWeapon(weapon);
        }
    }

    @Override
    public void setWeapon(String weaponName) {
        //Need to use new Instance of weapon for inventory
        Weapon w = weaponFactory.getWeapon(this, weaponName);

        if (w != null) {
            inventory.add(w);
            weapon = w;
        }
    }

    @Override
    public boolean isAttacking() {
        return hasWeapon() && weapon.isAttack();
    }

    @Override
    public void attack() {
        if (hasWeapon()) {
            weapon.attack();
            bodyState = BodiesStates.ATTACK;
            if (isJumping()) bodyState = BodiesStates.JUMP_ATTACK;
        }
    }

    @Override
    public void stopAttack() {
        if (hasWeapon()) {
            weapon.stopAttack();
            bodyState = BodiesStates.IDLE;
        }
    }
    //endregion
}
