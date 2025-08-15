package com.haloofwar.ecs.components.gameplay;

import java.util.ArrayList;

import com.haloofwar.ecs.Entity;
import com.haloofwar.ecs.components.Component;
import com.haloofwar.ecs.components.collision.PickupComponent;

public class InventoryComponent implements Component{
	private ArrayList<Entity> objects = new ArrayList<Entity>();
	
	public void add(Entity entity) {
		System.out.println("Agregando item");
		if(!entity.hasComponent(NameComponent.class) || !entity.hasComponent(PickupComponent.class)) {
			return;
		}
		
		final String NAME_NEW_ENTITY = entity.getComponent(NameComponent.class).name;
		
		if(this.objects.size() == 0) {
			this.objects.add(entity);
			System.out.println("Se ha agregado un item al inventario");
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
				this.objects.get(index).getComponent(PickupComponent.class).affectStock(1);
				System.out.println("Se ha agregado un item que ya existia al inventario");
			} else {
				this.objects.add(entity);
				System.out.println("Se ha agregado un item al inventario");
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
			PickupComponent component = this.objects.get(index).getComponent(PickupComponent.class);
			
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
			count += entity.getComponent(PickupComponent.class).getStock();
		}
		
		return count;
	}
	
	public ArrayList<Entity> getObjects(){
		return this.objects;
	}
}
