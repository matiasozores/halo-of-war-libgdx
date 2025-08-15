// ECS Component
package com.haloofwar.ecs.components.render;

import com.badlogic.gdx.graphics.Texture;
import com.haloofwar.cameras.GameWorldCamera;
import com.haloofwar.ecs.components.Component;

public class CrosshairComponent implements Component {
    public int mouseX = 0;
    public int mouseY = 0;
    public final int width = 16;
    public final int height = 16;
    public final Texture texture;
    public final GameWorldCamera camera;

    public CrosshairComponent(Texture texture, GameWorldCamera camera) {
        this.texture = texture;
        this.camera = camera;
    }
}
