package com.haloofwar.components;

import java.util.ArrayList;

import com.haloofwar.interfaces.Component;

public class InventoryComponent implements Component{
	private ArrayList<Entity> objects = new ArrayList<Entity>();
	
	public void add(Entity entity) {
		if(!entity.hasComponent(NameComponent.class) || !entity.hasComponent(StockComponent.class)) {
			return;
		}
		
		final String NAME_NEW_ENTITY = entity.getComponent(NameComponent.class).name;
		
		if(this.objects.size() == 0) {
			this.objects.add(entity);
		} else {
			int i = 0;
			boolean found = false;
			int index = -1;
			
			while(i < this.objects.size() && !found) {
				final String NAME = this.objects.get(i).getComponent(NameComponent.class).name;
				
				if(NAME.equals(NAME_NEW_ENTITY)) {
					found = true;
					index = i;
				} else {
					i++;
				}
			}
			
			if(found) {
				this.objects.get(index).getComponent(StockComponent.class).affectStock(1);
			} else {
				this.objects.add(entity);
			}
		}
	}
	
	public void remove(Entity entity) {
		int i = 0;
		boolean found = false;
		int index = -1;
		
		final String NAME_REMOVE_ENTITY = entity.getComponent(NameComponent.class).name;
		
		while(i < this.objects.size() && !found) {
			final String NAME = this.objects.get(i).getComponent(NameComponent.class).name;
			
			if(NAME.equals(NAME_REMOVE_ENTITY)) {
				found = true;
				index = i;
			} else {
				i++;
			}
		}
		
		if(found) {
			StockComponent component = this.objects.get(index).getComponent(StockComponent.class);
			
			if(component.getStock() > 1) {
				component.affectStock(-1);
			} else {
				this.objects.remove(index);
			}
		}
	}
	
	public int getItemsCount() {
		int count = 0;
		
		for (Entity entity : this.objects) {
			count += entity.getComponent(StockComponent.class).getStock();
		}
		
		return count;
	}
	
	public ArrayList<Entity> getObjects(){
		return this.objects;
	}
}
