package fr.internship2016.prototype.gameState.weapons;

/**
 * Created by bastien on 17/05/16.
 */
public enum WeaponType {
    //Update damage value corresponding
    //Player weapons
    SWORD("Sword", 0.1f, 1.75f, 1,500),
    SPEAR("Spear", 2f, 0.1f, 0.75,500),
    //Enemies weapons
    CLUB("Club", 0.2f, 2.5f, 2,1000);

    private String name;
    private float width;
    private float height;
    private double dmg;
    private float refillTime;


    WeaponType(String name, float width, float height, double dmg, float refillTime) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.dmg = dmg;
        this.refillTime = refillTime;
    }

    //region Getters
    public String getName() {
        return name;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public double getDmg() {
        return dmg;
    }

    public float getRefillTime() {
        return refillTime;
    }
    //endregion
}
