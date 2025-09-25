package com.haloofwar.game.data;

import java.io.Serializable;
import java.util.ArrayList;

public class InventoryData implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public ArrayList<ItemData> items = new ArrayList<ItemData>();
	
    public static class ItemData implements Serializable {
		private static final long serialVersionUID = 1L;
		
		public String name;
        public int stock;
        public String type; 
    }
}
