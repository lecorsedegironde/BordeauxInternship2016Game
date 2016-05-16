package fr.internship2016.prototype.engine.input;

import com.badlogic.gdx.Input;
import fr.internship2016.prototype.engine.Action;

import static fr.internship2016.prototype.engine.Action.*;

/**
 * Created by bastien on 16/05/16.
 */
public class ITLInput {

    //region Controls
    private int right;
    private int left;
    private int jump;
    private int invisibility;
    private int attack;
    private int spellOne;
    private int spellTwo;
    private int spellThree;
    private int inventory;
    private int pause;
    private int reset;
    private int quit;
    //endregion

    public ITLInput(boolean qwerty) {
        if (qwerty) {
            left = Input.Keys.A;
            jump = Input.Keys.W;
        } else {
            left = Input.Keys.Q;
            jump = Input.Keys.Z;
        }

        //Same Azerty-Qwerty
        right = Input.Keys.D;
        invisibility = Input.Keys.SHIFT_LEFT;
        attack = Input.Keys.SPACE;
        spellOne = Input.Keys.C;
        spellTwo = Input.Keys.V;
        spellThree = Input.Keys.B;
        inventory = Input.Keys.E;
        pause = Input.Keys.P;
        reset = Input.Keys.R;
        quit = Input.Keys.ESCAPE;
    }

    public Action getActionFromKey(int keycode) {
        if (keycode == right) {
            return RIGHT;
        } else if (keycode == left) {
            return LEFT;
        } else if (keycode == jump) {
            return JUMP;
        } else if (keycode == invisibility) {
            return INVISIBILITY;
        } else if (keycode == attack) {
            return ATTACK;
        } else if (keycode == spellOne) {
            return SPELL_ONE;
        } else if (keycode == spellTwo) {
            return SPELL_TWO;
        } else if (keycode == spellThree) {
            return SPELL_THREE;
        } else if (keycode == inventory) {
            return INVENTORY;
        } else if (keycode == pause) {
            return PAUSE;
        } else if (keycode == reset) {
            return RESET;
        } else if (keycode == quit) {
            return QUIT;
        } else {
            return NO_ACTION;
        }
    }
}
