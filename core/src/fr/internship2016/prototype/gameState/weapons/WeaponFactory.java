package fr.internship2016.prototype.gameState.weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import fr.internship2016.prototype.gameState.movable.bodies.BodyElement;
import fr.internship2016.prototype.gameState.movable.bodies.Player;

/**
 * Created by bastien on 24/05/16.
 */
public class WeaponFactory {

    private String bodyRequesting;
    private Array<WeaponType> availableWeapon;

    public WeaponFactory(BodyElement bodyElement) {
        //Set up body requesting
        setBodyRequesting(bodyElement);

        //Set up available weapon
        availableWeapon = new Array<>();

        //Get all weapon Json files
        FileHandle[] files = Gdx.files.local("weapons/").list();
        for (FileHandle file : files) {
            //Json Parser
            JsonValue jsonValue = new JsonReader().parse(file);
            JsonValue valueType = jsonValue.get("type");
            String dest = valueType.getString("dest");
            if (dest.equals(bodyRequesting)) {
                WeaponType type = new WeaponType(jsonValue.getString("name"));
                type.setWidth(jsonValue.get("size").getFloat("w"));
                type.setHeight(jsonValue.get("size").getFloat("h"));
                type.setDmg(jsonValue.getDouble("dmg"));
                type.setRefillTime(jsonValue.getFloat("refill"));
                type.setTypeName(valueType.getString("name"));
                type.setBodyDestination(bodyRequesting);
                type.setDefaultPos(valueType.getFloat("default"));
                type.setMaxPos(valueType.getFloat("max"));
                type.setIncrements(valueType.getFloat("increments"));

                availableWeapon.add(type);
            }
        }
    }

    private void setBodyRequesting(BodyElement bodyElement) {
        if (bodyElement instanceof Player) bodyRequesting = "player";
        else bodyRequesting = "enemy";
    }

    public boolean isWeaponAvailable(String weaponName) {
        boolean isAvailable = false;

        for (WeaponType w : availableWeapon) {
            if (w.getName().equals(weaponName)) {
                isAvailable = true;
                break;
            }
        }

        return isAvailable;
    }

    public Array<String> getAvailableWeapon() {
        Array<String> nameWeapons = new Array<>();
        for (WeaponType w : availableWeapon) {
            nameWeapons.add(w.getName());
        }
        return nameWeapons;
    }

    public Weapon getWeapon(BodyElement b, String weaponName) {
        Weapon weapon = null;

        for (WeaponType w : availableWeapon) {
            if (w.getName().equals(weaponName)) {
                switch (w.getTypeName()) {
                    case "translate":
                        weapon = new TranslatingWeapon(b, w);
                        break;
                    case "rotate":
                        weapon = new RotatingWeapon(b, w);
                        break;
                }
            }
        }
        return weapon;
    }
}
