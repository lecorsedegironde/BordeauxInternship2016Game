package fr.internship2016.prototype.movable.armed;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import fr.internship2016.prototype.movable.spells.FireSpell;
import fr.internship2016.prototype.movable.spells.Spell;
import fr.internship2016.prototype.weapon.WeaponStyles;
import fr.internship2016.prototype.weapon.rotate.Sword;

import static fr.internship2016.prototype.utils.Constants.*;

/**
 * Created by bastien on 20/04/16.
 * <p>
 * This class concerns the player and all attributes related
 * </p>
 */
public class Player extends ArmedElement {

    //Player vital signs
    private double lifePoints;
    private double magicPoints;

    //TODO Remove
    private boolean canStopMovement;

    private boolean invisible;
    private boolean canBeInvisible;
    private long invisibilityTime;

    private long lastFireS1;


    public Player(float x, float y, float width, float height, float velocityX, float velocityY, boolean createWeapon) {
        super(x, y, width, height, velocityX, velocityY);
        canStopMovement = true;
        rightFacing = true;

        if (createWeapon)
            setWeapon(WeaponStyles.SWORD);

        invisible = false;
        canBeInvisible = true;

        lifePoints = DEFAULT_LIFE;
        magicPoints = DEFAULT_MAGIC;
    }

    @Override
    public void update() {

        super.update();

        autoMagicPointRegain();

        //Invisibility
        if (invisible && TimeUtils.timeSinceMillis(invisibilityTime) > INVISIBILITY_DURATION) {
            invisible = false;
            invisibilityTime = TimeUtils.millis();
        }
        if (TimeUtils.timeSinceMillis(invisibilityTime) > INVISIBILITY_REFILL) {
            canBeInvisible = true;
        }
    }

    @Override
    public void hitWeapon() {
        //TODO
    }

    @Override
    public void hitSpell(double spellDmg) {
        lifePoints -= spellDmg;
    }

    @Override
    public void setWeapon(WeaponStyles w) {
        switch (w) {
            case SWORD:
                weapon = new Sword(this, SWORD_WIDTH, SWORD_HEIGHT);
                break;
            default:
                weapon = null;
                break;
        }
    }

    public boolean canStopMovement() {
        return canStopMovement;
    }

    public void setCanStopMovement(boolean canStopMovement) {
        this.canStopMovement = canStopMovement;
    }

    //TODO: Add support for different type of spells
    public Spell fireSpell1() {
        if (TimeUtils.timeSinceMillis(lastFireS1) > SPELL_REFILL && magicPoints > SPELL_COST) {
            //Maj pos spell
            lastFireS1 = TimeUtils.millis();
            magicPoints -= SPELL_COST;
            return new FireSpell(this);
        } else {
            return null;
        }
    }

    public boolean isInvisible() {
        return invisible;
    }

    public void startInvisibility() {
        if (!invisible && canBeInvisible) {
            invisible = true;
            canBeInvisible = false;
            invisibilityTime = TimeUtils.millis();
        }
    }

    public boolean canBeInvisible() {
        return canBeInvisible;
    }

    public void gainLife(double life) {
        if (life > 0) {
            lifePoints += life;
        }
    }

    public void looseLife(double life) {
        if (life > 0) {
            lifePoints -= life;
        }
    }

    @Override
    public double getLife() {
        return lifePoints;
    }

    public void setLifePoints(double lifePoints) {
        if (lifePoints > 0) {
            this.lifePoints = lifePoints;
        }
    }

    private void autoMagicPointRegain() {
        if (magicPoints <= DEFAULT_MAGIC - MAGIC_REFILL) {
            magicPoints += MAGIC_REFILL;
        } else if (magicPoints > DEFAULT_MAGIC - MAGIC_REFILL
                && magicPoints < DEFAULT_MAGIC) {
            magicPoints = DEFAULT_MAGIC;
        }
    }

    public double getMagicPoints() {
        return magicPoints;
    }

    public void setMagicPoints(double magicPoints) {
        if (magicPoints > 0) {
            this.magicPoints = magicPoints;
        }
    }

    @Override
    public void knockBackLeft() {
        velocityX=KNOCKBACK_X_PLAYER;
        velocityY=KNOCKBACK_Y_PLAYER;
    }

    public void knockBackRight(){
        velocityX=-KNOCKBACK_X_PLAYER;
        velocityY=KNOCKBACK_Y_PLAYER;
    }
}