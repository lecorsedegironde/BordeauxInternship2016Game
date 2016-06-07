package fr.internship2016.prototype.screen.drawer;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ObjectMap;

/**
 * Created by bastien on 01/06/16.
 */
public class SpriteDrawer {

    public enum Level {
        REPLACE,
        INSERT,
        REPLACE_TO_END,
        NEAREST,
        NEAREST_UP,
        NEAREST_DOWN,
        FIRST_AVAILABLE,
        LAST_AVAILABLE,
    }

    private ObjectMap<Integer, Sprite> sprites;

    public SpriteDrawer() {
        sprites = new ObjectMap<>();
    }

    public void add(Sprite s, int priority, Level comportmentIfOccupied) {
        if (priority < 0) priority = 0;

        if (!sprites.containsKey(priority)) {
            sprites.put(priority, s);
        } else {
            switch (comportmentIfOccupied) {
                case REPLACE:
                    sprites.put(priority, s);
                    break;
                case INSERT:
                    moveValues(priority + 1);
                    sprites.put(priority, s);
                    break;
                case REPLACE_TO_END:
                    sprites.put(sprites.size, sprites.get(priority));
                    sprites.put(priority, s);
                    break;
                case NEAREST:
                    int prioCpt1 = priority;
                    int prioCpt2 = priority;
                    while (sprites.containsKey(prioCpt1)) {
                        prioCpt1++;
                    }
                    while (sprites.containsKey(prioCpt2)) {
                        prioCpt2--;
                    }

                    if (prioCpt1 - priority < priority - prioCpt2) {
                        sprites.put(prioCpt1, s);
                    } else if (prioCpt1 - priority > priority - prioCpt2) {
                        sprites.put(prioCpt2, s);
                    } else if (prioCpt1 - priority == priority - prioCpt2) {
                        //TODO Define
                    }
                    break;
                case NEAREST_UP:
                    int prioCptUp = priority;
                    while (sprites.containsKey(prioCptUp)) {
                        prioCptUp++;
                    }
                    sprites.put(prioCptUp, s);
                    break;
                case NEAREST_DOWN:
                    int prioCptDown = priority;
                    while (sprites.containsKey(prioCptDown)) {
                        prioCptDown--;
                    }
                    sprites.put(prioCptDown, s);
                    break;
                case FIRST_AVAILABLE:
                    int i = 0;

                    while (sprites.containsKey(i)) {
                        i++;
                    }

                    sprites.put(i, s);
                    break;
                case LAST_AVAILABLE:
                    sprites.put(sprites.size, s);
                    break;
            }
        }
    }

    private void moveValues(int priority) {
        if (sprites.containsKey(priority + 1)) {
            moveValues(priority + 1);
        }
        sprites.put(priority + 1, sprites.get(priority));
    }

    public void draw(SpriteBatch batch) {
        batch.begin();

        for (int i = 0; i < sprites.size; i++) {
            if (sprites.containsKey(i)) sprites.get(i).draw(batch);
        }

        batch.end();

        //Clear array for next render
        sprites.clear();
    }
}
