package com.haloofwar.saves;

import java.io.Serializable;
import java.util.ArrayList;

import com.haloofwar.enumerators.ObjectType;
import com.haloofwar.enumerators.PlayerType;

public class PlayerData implements Serializable {
    private static final long serialVersionUID = 1L;

    public PlayerType type;
    public int currentHealth;
    public int maxHealth;
    public float shield;

    public ArrayList<ItemData> inventory = new ArrayList<>();

    public static class ItemData implements Serializable {
        private static final long serialVersionUID = 1L;
        public ObjectType type;
        public int stock;

        public ItemData(ObjectType type, int stock) {
            this.type = type;
            this.stock = stock;
        }
    }
}
