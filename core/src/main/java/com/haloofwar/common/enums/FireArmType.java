package com.haloofwar.common.enums;

import com.haloofwar.game.components.FireArmComponent;
import com.haloofwar.interfaces.Weapon;

public enum FireArmType implements Weapon {
    RIFLE_ASALTO("Rifle de asalto", "images/weapons/assaultRifle.png", 10, 25f, 0.1f, 60f,
            "Rifle equilibrado, buena cadencia y daño moderado.", 350, BulletType.ASSAULT),
    PISTOLA("Pistola", "images/weapons/pistol.png", 5, 30f, 0.5f, 60f,
            "Arma básica, ligera y rápida, pero con bajo daño.", 0, BulletType.PISTOL),
    ESCOPETA("Escopeta", "images/weapons/shotgun.png", 25, 20f, 1.2f, 70f,
            "Gran daño a corta distancia, lenta y poco precisa.", 400, BulletType.SHOTGUN),
    SUBFUSIL("Subfusil", "images/weapons/submachineGun.png", 8, 28f, 0.08f, 55f,
            "Dispara ráfagas rápidas, daño bajo pero alta cadencia.", 300, BulletType.SMG),
    AMETRALLADORA("Ametralladora", "images/weapons/machineGun.png", 12, 27f, 0.05f, 65f,
            "Potente arma automática, ideal para suprimir al enemigo.", 600, BulletType.MACHINE_GUN),
    FRANCO("Francotirador", "images/weapons/sniper.png", 90, 50f, 1.5f, 100f,
            "Daño letal a larga distancia, pero muy lenta.", 800, BulletType.SNIPER),
    REVOLVER("Revolver", "images/weapons/revolver.png", 20, 35f, 0.7f, 65f,
            "Arma clásica de alto daño, cadencia limitada.", 250, BulletType.REVOLVER);

    private final String name;
    private final String path;
    private final int damage;
    private final float speed;
    private final float fireCooldown;
    private final float bulletOffset;
    private final String description;
    private final int price;
    private final BulletType bulletType;

    FireArmType(String name, String path, int damage, float speed, float fireCooldown, float bulletOffset, String description, int price, BulletType bulletType) {
        this.name = name;
        this.path = path;
        this.damage = damage;
        this.speed = speed;
        this.fireCooldown = fireCooldown;
        this.bulletOffset = bulletOffset;
        this.description = description;
        this.price = price;
        this.bulletType = bulletType;
    }

    @Override
    public String getName() { return this.name; }

    @Override
    public String getPath() { return this.path; }

    public FireArmComponent createComponent() {
        return new FireArmComponent(this.damage, this.speed, this.fireCooldown, this.bulletOffset, this);
    }

    @Override
    public String getDescription() { return this.description; }

    @Override
    public int getPrice() { return this.price; }
    
    public BulletType getBulletType() {
		return this.bulletType;
	}
    
    public static FireArmType getByName(final String NAME) {
    	boolean found = false;
    	FireArmType type = null;
    	int i = 0;
    	final FireArmType FIRE_ARMS[] = FireArmType.values();
    	
    	while(i < FIRE_ARMS.length && !found) {
    		if(FIRE_ARMS[i].getName().equals(NAME)) {
    			found = true;
    			type = FIRE_ARMS[i];
    		} else {
    			i++;
    		}
    	}
    	
    	return type;
    }
}
