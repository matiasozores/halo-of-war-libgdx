package com.haloofwar.game.components;

import java.util.ArrayList;

import com.haloofwar.engine.components.Component;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.game.data.EquipmentData;
import com.haloofwar.interfaces.Weapon;

public class EquipmentComponent implements Component {
	public Entity currentWeapon;
	public ArrayList<Entity> weaponInventory = new ArrayList<>();
	
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
	
	public EquipmentData toData() {
	    EquipmentData data = new EquipmentData();

	    data.currentWeaponName = this.currentWeapon.getComponent(NameComponent.class).name;

	    for (Entity e : this.weaponInventory) {
	        data.weaponInventoryNames.add(e.getComponent(NameComponent.class).name);
	    }

	    return data;
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
