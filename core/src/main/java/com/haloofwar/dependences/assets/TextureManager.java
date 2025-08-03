package com.haloofwar.dependences.assets;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.haloofwar.interfaces.TextureDescriptor;

public class TextureManager {
    private final HashMap<TextureDescriptor, Texture> textureMap = new HashMap<>();
    private final Texture placeholderTexture;

    public TextureManager() {
        Texture placeholder = null;
        try {
            placeholder = new Texture("placeholder.png");
        } catch (Exception e) {
           System.out.println("No se ha podido cargar la textura placeholder, se usara null en su lugar.");
        }
        this.placeholderTexture = placeholder;
	}
    
    public void load(TextureDescriptor descriptor) {
        if (!this.textureMap.containsKey(descriptor)) {
            try {
                Texture texture = new Texture(descriptor.getPath());
                this.textureMap.put(descriptor, texture);
            } catch (GdxRuntimeException e) {
                System.err.println("No se pudo cargar la textura: " + descriptor.getPath() + " — Usando placeholder.");
                this.textureMap.put(descriptor, this.placeholderTexture);
            } catch (Exception e) {
                System.err.println("Error inesperado al cargar: " + descriptor.getPath() + " — Usando placeholder.");
                this.textureMap.put(descriptor, this.placeholderTexture);
            }
        }
    }

    public Texture get(TextureDescriptor descriptor) {
        if (!this.textureMap.containsKey(descriptor)) {
            this.load(descriptor); 
        }
        
        // devuelve la textura si existe, o la placeholder si no.
        return this.textureMap.getOrDefault(descriptor, this.placeholderTexture);
    }

    public void unload(TextureDescriptor descriptor) {
        Texture texture = this.textureMap.remove(descriptor);
        if (texture != null) {
            texture.dispose();
        }
    }
    
    public void dispose() {
    	System.out.println("Liberando recursos del TextureManager");
        for (Texture texture : this.textureMap.values()) {
        	texture.dispose();
        }
        
        this.textureMap.clear();
    }
}
