package com.haloofwar.dependences;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.haloofwar.enumerators.CharacterType;
import com.haloofwar.interfaces.TextureDescriptor;

public class TextureManager {
    private final HashMap<TextureDescriptor, Texture> textureMap = new HashMap<>();

    public TextureManager() {
    	this.load(CharacterType.KRATOS);
    	this.load(CharacterType.MASTER_CHIEF);
    }
    
    public void load(TextureDescriptor descriptor) {
        if (!this.textureMap.containsKey(descriptor)) {
            this.textureMap.put(descriptor, new Texture(descriptor.getPath()));
        }
    }

    public Texture get(TextureDescriptor descriptor) {
        if (!textureMap.containsKey(descriptor)) {
            load(descriptor); 
        }
        return textureMap.get(descriptor);
    }


    public void unload(TextureDescriptor descriptor) {
        Texture texture = this.textureMap.remove(descriptor);
        if (texture != null) {
            texture.dispose();
        }
    }
    
    public void dispose() {
        for (Texture texture : textureMap.values()) {
        	texture.dispose();
        }
        
        this.textureMap.clear();
    }
}
