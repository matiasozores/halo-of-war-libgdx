package com.haloofwar.utilities;

import java.util.Arrays;
import java.util.Comparator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.haloofwar.enumerators.Sprite;
import com.haloofwar.input.InputManager;

public class MyAnimation {

    private Animation<TextureRegion> walkAnimation;
    private Animation<TextureRegion> idleAnimation;
    private float stateTime = 0f;
    
    private SpriteBatch batch = Resources.getBatch();
    private TextureRegion currentFrame;
    private boolean isMoving = false;

    private Array<Texture> texturesToDispose = new Array<>();

    public MyAnimation(Sprite sprite) {
        this.walkAnimation = loadAnimation("sprites/"+ sprite.getFolder() +"/walk", 0.1f);
        this.idleAnimation = loadAnimation("sprites/"+ sprite.getFolder() +"/idle", 0.2f); 
    }

    private Animation<TextureRegion> loadAnimation(String folderPath, float frameDuration) {
        Array<TextureRegion> frames = loadFramesFromFolder(folderPath);
        return new Animation<>(frameDuration, frames, PlayMode.LOOP);
    }

    private Array<TextureRegion> loadFramesFromFolder(String folderPath) {
        FileHandle folder = Gdx.files.internal(folderPath);
        System.out.println("Ruta: " + folder.path());
        System.out.println("Exists: " + folder.exists());
        System.out.println("IsDirectory: " + folder.isDirectory());

        FileHandle[] files = folder.list((file) -> file.getName().toLowerCase().endsWith(".png"));

        if (files == null || files.length == 0) {
            throw new IllegalArgumentException("No .png files found in: " + folderPath);
        }

        Arrays.sort(files, Comparator.comparing(FileHandle::name));

        Array<TextureRegion> frames = new Array<>();

        for (FileHandle file : files) {
            Texture texture = new Texture(file);
            texturesToDispose.add(texture);
            frames.add(new TextureRegion(texture));
        }

        return frames;
    }

    public void update(InputManager inputManager) {
    	this.isMoving = inputManager.isMoving();
    	this.stateTime += Gdx.graphics.getDeltaTime();

        if (this.isMoving) {
        	this.currentFrame = this.walkAnimation.getKeyFrame(this.stateTime);
        } else {
        	this.currentFrame = this.idleAnimation.getKeyFrame(this.stateTime);
        }
    }

    public void render(float x, float y) {
        if (this.currentFrame != null) {
            this.batch.draw(this.currentFrame, x, y);
        }
    }

    public void dispose() {
        for (Texture texture : this.texturesToDispose) {
            texture.dispose();
        }
    }
}