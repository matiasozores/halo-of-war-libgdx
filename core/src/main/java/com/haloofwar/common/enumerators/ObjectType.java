package com.haloofwar.common.enumerators;

import com.haloofwar.engine.entity.EntityDescriptor;
import com.haloofwar.engine.utils.RandomUtils;

public enum ObjectType implements EntityDescriptor {
    LLAVE_DEL_TESORO("images/objects/treasure_key.png", "Llave del tesoro",
            "Una llave antigua y ornamentada que abre cofres ocultos en lo mas profundo de las mazmorras."),
    GEMAS_MAGICAS("images/objects/magic_gem.png", "Gemas mágicas",
            "Cristales brillantes cargados de energia arcana, usados para potenciar hechizos."),
    MAPA_ANTIGUO("images/objects/ancient_map.png", "Mapa antiguo",
            "Un pergamino desgastado que revela rutas secretas y tesoros olvidados."),
    
    AMULETO_DE_LUZ("images/objects/amulet_light.png", "Amuleto de luz",
            "Un amuleto sagrado que irradia energia pura, capaz de repeler la oscuridad."),
    ANILLO_DE_OMNISCIENCIA("images/objects/ring_omniscience.png", "Anillo de omnisciencia",
            "Un anillo legendario que otorga visiones del pasado, presente y futuro."),
    CRISTAL_DEL_TIEMPO("images/objects/time_crystal.png", "Cristal del tiempo",
            "Un cristal enigmatico que permite manipular el flujo del tiempo por breves instantes."),
    
    SOMBRERO_LOCO("images/objects/crazy_hat.png", "Sombrero loco",
            "Un sombrero extravagante que cambia de forma constantemente y desconcierta a los enemigos."),
    MONEDA_DE_ORO("images/objects/gold_coin.png", "Moneda de oro",
            "Una moneda brillante y codiciada, simbolo universal de riqueza y comercio.");

    private final String path;
    private final String name;
    private final String description;

    ObjectType(final String path, final String name, final String description) {
        this.path = path;
        this.name = name;
        this.description = description;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }

    public static ObjectType generate() {
        return ObjectType.values()[RandomUtils.generateInt(ObjectType.values().length)];
    }
    
    public static ObjectType getByName(final String NAME) {
    	boolean found = false;
    	ObjectType type = null;
    	int i = 0;
    	final ObjectType OBJECTS[] = ObjectType.values();
    	
    	while(i < OBJECTS.length && !found) {
    		if(OBJECTS[i].getName().equals(NAME)) {
    			found = true;
    			type = OBJECTS[i];
    		} else {
    			i++;
    		}
    	}
    	
    	return type;
    }
}
