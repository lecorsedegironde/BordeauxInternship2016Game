package fr.internship2016.prototype;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Bastien on 20/04/16.
 * <p>
 * This class concerns the player and all his attributes
 */
public class Player {

    public static final int PLAYER_SPEED = 100;

    private Rectangle playerRect;

    public Player(int x, int y, int width, int height) {

        this.playerRect = new Rectangle();

        playerRect.x = x;
        playerRect.y = y;
        playerRect.width = width;
        playerRect.height = height;
    }

    public void moveRight(float dist) {
        playerRect.x += dist;
    }

    public void moveLeft(float dist) {
        playerRect.x -= dist;
    }

    public float getX() {
        return playerRect.x;
    }

    public void setX(float x) {
        playerRect.x = x;
    }

    public float getY() {
        return playerRect.y;
    }

    public void setY(float y) {
        playerRect.y = y;
    }

    public float getW() {
        return playerRect.width;
    }

    public void setW(float w) {
        playerRect.width = w;
    }

    public float getH() {
        return playerRect.height;
    }

    public void setH(float h) {
        playerRect.height = h;
    }
}
