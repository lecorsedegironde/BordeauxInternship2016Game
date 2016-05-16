package fr.internship2016.prototype.gameState.movable.interfaces;

import com.badlogic.gdx.utils.Array;

/**
 * Created by bastien on 13/05/16.
 */
public interface Inventory {

    boolean isInInventory(Object o);
    void addToInventory(Object o);
    Array<Object> getInventory();
    boolean isInventoryEmpty();
}
