package fr.internship2016.prototype.weapon;

/**
 * Created by bastien on 24/04/16.
 * Implementation of weapon
 */
public class Sword extends Weapon {

    private float angularSpeed;

    public Sword(float x, float y, float width, float height, float angularSpeed) {
        super(x, y, width, height);
        this.angularSpeed = angularSpeed;
    }


}
