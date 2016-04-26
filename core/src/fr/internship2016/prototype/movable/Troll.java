package fr.internship2016.prototype.movable;

import static fr.internship2016.prototype.utils.Constants.WORLD_WIDTH;

/**
 * Created by pacaud on 16/04/25.
 */
public class Troll extends MovableElement {

    public Troll(float x, float y, float width, float height, float velocityX, float velocityY) {
        super(x, y, width, height, velocityX, velocityY);

    }

    @Override
    public void update() {
        super.update();

        if (elementRect.getX() <= 0) {
            moveRight();
        } else if (elementRect.getX() >= WORLD_WIDTH - getW()) {
            moveLeft();
        }
    }

}
