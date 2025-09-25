package com.haloofwar.game.world;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.haloofwar.common.enums.NPCType;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.NewEntityEvent;
import com.haloofwar.game.factories.NPCFactory;
import com.haloofwar.interfaces.Spawner;

public class NPCSpawner implements Spawner {

    private final TiledMap map;
    private final NPCFactory NPC_FACTORY;
    private final EventBus GAMEPLAY_BUS;
    
    public NPCSpawner(final NPCFactory NPC_FACTORY, final EventBus GAMEPLAY_BUS, final TiledMap map) {
        this.map = map;
        this.NPC_FACTORY = NPC_FACTORY;
        this.GAMEPLAY_BUS = GAMEPLAY_BUS;
    }

    @Override
    public void spawn() {
        for (MapObject obj : map.getLayers().get("NPCs").getObjects()) {
            if (obj instanceof RectangleMapObject) {
                RectangleMapObject rect = (RectangleMapObject) obj;
                
                float x = rect.getRectangle().x;
                float y = rect.getRectangle().y;

                final Entity NPC =  this.NPC_FACTORY.create(NPCType.VILLAGER, x, y);
                this.GAMEPLAY_BUS.publish(new NewEntityEvent(NPC));
            }
        }
    }
}
