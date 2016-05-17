package fr.internship2016.prototype.gameState.weapons;

/**
 * Created by bastien on 17/05/16.
 */
public enum WeaponType {
    //Player weapons
    SWORD("Sword", 0.1f, 1.75f, 750),
    SPEAR("Spear", 2f, 0.1f, 750),
    //Enemies weapons
    CLUB("Club", 0.2f, 2.5f, 1000);

    private String name;
    private float width;
    private float height;
    private float refillTime;


    WeaponType(String name, float width, float height, float refillTime) {
        this.name = name;
        this.width = width;
        this.height = height;
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

    public float getRefillTime() {
        return refillTime;
    }
    //endregion
}
