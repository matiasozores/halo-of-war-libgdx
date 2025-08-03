package com.haloofwar.dependences.collision.tiled;

import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.enumerators.entities.objects.ItemType;
import com.haloofwar.factories.ItemsFactory;
import com.haloofwar.interfaces.IEntityCreator;

public class ItemCreator implements IEntityCreator {
    private final ItemsFactory factory;

    public ItemCreator(GameContext context) {
        this.factory = new ItemsFactory(context);
    }

    @Override
    public Object create(Rectangle rect) {
    	// por ahora son todas posiones sin efectos, pero a futuro cuando quiera tener otro tipo de item
    	// en un enumerador por ejemplo el de layer se le pued asociar el tipo de item para crear algo en 
    	// concreto, por ahora como solo tengo este y estamos probando lo dejamos asi pero estaria listo
    	// para escalar
        return factory.create(rect.x, rect.y, (int)rect.width, (int)rect.height, ItemType.POSION_SIN_EFECTOS);
    }
}
