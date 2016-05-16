package fr.internship2016.prototype.gameState.movable;

/**
 * Created by bastien on 16/05/16.
 */
public abstract class Spell extends MovableElement{

    private boolean disappear;

    public Spell(float x, float y, float width, float height, float velocityX, float velocityY) {
        super(x, y, width, height, velocityX, velocityY, 0f);
    }

    public boolean isDisappear() {
        return disappear;
    }

    public void setDisappear() {
        disappear = true;
    }
}
