package com.haloofwar.entities.characters.components;

import java.util.ArrayList;

import com.haloofwar.entities.statics.Item;

public class Inventory {
	private ArrayList<Item> items = new ArrayList<Item>();
	
	public void add(Item item) {
		if(this.items.size() == 0) {
			this.items.add(item);
			System.out.println("Se ha agregado el item " + item.getName() + " al inventario");
		} else {
			int i = 0;
			boolean found = false;
			int index = -1;
			
			while(i < this.items.size() && !found) {
				if(this.items.get(i).getName().equals(item.getName())) {
					found = true;
					index = i;
				} else {
					i++;
				}
			}
			
			if(found) {
				this.items.get(index).affectStock(1);
			} else {
				this.items.add(item);
			}
		}
	}
	
	public void remove(Item item) {
		int i = 0;
		boolean found = false;
		int index = -1;
		
		while(i < this.items.size() && !found) {
			if(this.items.get(i).getName().equals(item.getName())) {
				found = true;
				index = i;
			} else {
				i++;
			}
		}
		
		if(found) {
			if(this.items.get(index).getStock() > 1) {
				this.items.get(index).affectStock(-1);
				System.out.println("Se ha reducido el stock del item " + item.getName() + " a " + this.items.get(index).getStock());
			} else {
				this.items.remove(index);
				System.out.println("Se ha eliminado el item " + item.getName() + " del inventario");	
			}
		}
	}
	
	public int getItemsCount() {
		int count = 0;
		
		for (Item item : this.items) {
			count += item.getStock();
		}
		
		return count;
	}
}
