package com.haloofwar.dependences.collision.tiled;

import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.entities.components.EntityStateHandler;
import com.haloofwar.entities.statics.Obstacle;
import com.haloofwar.interfaces.IEntityCreator;

public class ObstacleCreator implements IEntityCreator {
    private final GameContext context;

    public ObstacleCreator(GameContext context) {
        this.context = context;
    }

    @Override
    public Object create(Rectangle rect) {
        EntityStateHandler state = new EntityStateHandler(
            context.getGameplay().getCollisions(),
            context.getGameplay().getEntities()
        );
        return new Obstacle(rect.x, rect.y, (int)rect.width, (int)rect.height, state);
    }
}
