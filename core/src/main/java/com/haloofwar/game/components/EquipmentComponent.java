package com.haloofwar.game.components;

import java.util.ArrayList;

import com.haloofwar.engine.entity.Entity;
import com.haloofwar.interfaces.Weapon;

public class EquipmentComponent implements Component {
	public Entity currentWeapon;
	public ArrayList<Entity> weaponInventory;
	
	public EquipmentComponent() {
		this.weaponInventory = new ArrayList<Entity>();
	}

	public boolean isInInventory(final String NAME) {
		boolean found = false;
		int i = 0;
		while(i < this.weaponInventory.size() && !found) {
			final NameComponent NAME_COMP = this.weaponInventory.get(i).getComponent(NameComponent.class);

			if(NAME_COMP.name.equals(NAME)) {
				found = true;
			}
		
			i++;
		}
		
		if(this.currentWeapon.getComponent(NameComponent.class).name.equals(NAME)) {
			found = true;
		}
		
		return found;
	}
	
	public void printInventory() {
		if(this.weaponInventory.size() == 0) {
			System.out.println("No hay armas :(");
		}
		
		for (int i = 0; i < this.weaponInventory.size(); i++) {
			System.out.println(this.weaponInventory.get(i).getComponent(NameComponent.class).name + " ");
		}
	}
	
	
	public Weapon getCurrentWeapon() {
		if(this.currentWeapon.hasComponent(FireArmComponent.class)) {
			return this.currentWeapon.getComponent(FireArmComponent.class).getWeapon();
		} else {
			if(this.currentWeapon.hasComponent(MeleeWeaponComponent.class)) {
				return this.currentWeapon.getComponent(MeleeWeaponComponent.class).getWeapon();
			} else {
				System.out.println("Error, el arma no tiene un componente de arma... | EquipmentComponent");
				return null;
			}
		}
	}
	
	public Entity getEntityByWeapon(final Weapon weaponType) {
	    if (weaponType == null) {
	        System.out.println("Error: weaponType es null | EquipmentComponent");
	        return null;
	    }

	    final String NAME = weaponType.getName();

	    // primero, revisar el arma actual
	    if (this.currentWeapon != null) {
	        NameComponent currentName = this.currentWeapon.getComponent(NameComponent.class);
	        if (currentName != null && currentName.name.equals(NAME)) {
	            return this.currentWeapon;
	        }
	    }

	    // luego revisar el inventario
	    for (Entity weapon : this.weaponInventory) {
	        NameComponent nameComp = weapon.getComponent(NameComponent.class);
	        if (nameComp != null && nameComp.name.equals(NAME)) {
	            return weapon;
	        }
	    }

	    // si no se encontró
	    System.out.println("No se encontró el arma con nombre: " + NAME + " | EquipmentComponent");
	    return null;
	}	
	
    public void setCurrentWeapon(Entity weapon) {
        this.currentWeapon = weapon;
    }

    public void setWeaponInventory(ArrayList<Entity> weapons) {
        this.weaponInventory.clear();
        if (weapons != null) {
            this.weaponInventory.addAll(weapons);
        }
    }

}
