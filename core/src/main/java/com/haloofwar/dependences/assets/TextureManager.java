package com.haloofwar.dependences.assets;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.haloofwar.interfaces.TextureDescriptor;

public class TextureManager {
    private final HashMap<TextureDescriptor, Texture> textureMap = new HashMap<>();
    
    public void load(TextureDescriptor descriptor) {
        if (!this.textureMap.containsKey(descriptor)) {
            try {
                Texture texture = new Texture(descriptor.getPath());
                this.textureMap.put(descriptor, texture);
            } catch (GdxRuntimeException e) {
                System.err.println("No se pudo cargar la textura, el archivo no existe/es corrupto o la ruta esta mal: " + descriptor.getPath());
            } catch (Exception e) {
				System.err.println("Error al cargar la textura: " + descriptor.getPath());
			}
        }
    }


    public Texture get(TextureDescriptor descriptor) {
        if (!this.textureMap.containsKey(descriptor)) {
            this.load(descriptor); 
        }
        return this.textureMap.get(descriptor);
    }

    public void unload(TextureDescriptor descriptor) {
        Texture texture = this.textureMap.remove(descriptor);
        if (texture != null) {
            texture.dispose();
        }
    }
    
    public void dispose() {
        for (Texture texture : this.textureMap.values()) {
        	texture.dispose();
        }
        
        this.textureMap.clear();
    }
}
