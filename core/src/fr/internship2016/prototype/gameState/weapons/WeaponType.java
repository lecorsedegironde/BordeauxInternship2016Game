package fr.internship2016.prototype.gameState.weapons;

/**
 * Created by bastien on 17/05/16.
 */
public class WeaponType {
    //Update damage value corresponding
    //Player weapons
//    SWORD("Sword", 0.1f, 1.75f, 1,500),
//    SPEAR("Spear", 2f, 0.1f, 0.75,500),
//    Enemies weapons
//    CLUB("Club", 0.2f, 2.5f, 2,1000);

    private String name;
    private float width;
    private float height;
    private double dmg;
    private float refillTime;
    private String typeName;
    private String bodyDestination;
    private float defaultPos;
    private float maxPos;
    private float increments;

    WeaponType(String name) {
        this.name = name;
    }

    WeaponType(String name, float width, float height, double dmg, float refillTime, String typeName,
               String bodyDestination, int defaultPos, int maxPos, int increments) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.dmg = dmg;
        this.refillTime = refillTime;
        this.typeName = typeName;
        this.bodyDestination = bodyDestination;
        this.defaultPos = defaultPos;
        this.maxPos = maxPos;
        this.increments = increments;
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

    public String getTypeName() {
        return typeName;
    }

    public String getBodyDestination() {
        return bodyDestination;
    }

    public float getDefaultPos() {
        return defaultPos;
    }

    public float getMaxPos() {
        return maxPos;
    }

    public float getIncrements() {
        return increments;
    }
    //endregion

    //region Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setDmg(double dmg) {
        this.dmg = dmg;
    }

    public void setRefillTime(float refillTime) {
        this.refillTime = refillTime;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setBodyDestination(String bodyDestination) {
        this.bodyDestination = bodyDestination;
    }

    public void setDefaultPos(float defaultPos) {
        this.defaultPos = defaultPos;
    }

    public void setMaxPos(float maxPos) {
        this.maxPos = maxPos;
    }

    public void setIncrements(float increments) {
        this.increments = increments;
    }

    //endregion
}
