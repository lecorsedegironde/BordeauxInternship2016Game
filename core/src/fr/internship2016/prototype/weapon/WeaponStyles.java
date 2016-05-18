package fr.internship2016.prototype.weapon;

/**
 * Created by bastien on 24/04/16.
 * List of all weapons
 */
public enum WeaponStyles {
    //Player weapons
    SWORD("Sword"),
    SPEAR("Spear"),
    //Enemies weapons
    CLUB("Club");

    private String name;

    WeaponStyles(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
