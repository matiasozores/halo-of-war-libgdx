package com.haloofwar.components;

import com.badlogic.gdx.graphics.Texture;
import com.haloofwar.cameras.GameWorldCamera;
import com.haloofwar.interfaces.Component;

/*
 * Hubo discusiones mentales si tratar al crosshair como una entidad o componente, por simplicidad se decidio
 * dejar como componente, lo malo es que esta como utilizando los atributos de un Transform y Render component 
 * pero es lo mas simple y directo por ahora
 * 
 * */

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
