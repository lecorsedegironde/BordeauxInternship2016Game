package fr.internship2016.prototype;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Bastien on 20/04/16.
 *
 * This class concerns the player and all his attributes
 */
public class Player  {

    private int x;
    private int y;
    private int w;
    private int h;

    public Player(int x, int y, int width, int height) {

        this.x = x;
        this.y = y;
        this.w = width;
        this.h = height;
    }

    public Texture getTexture() {

        return null;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }
}
