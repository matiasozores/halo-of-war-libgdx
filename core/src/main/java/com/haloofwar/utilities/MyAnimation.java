package com.haloofwar.utilities;

import java.util.Arrays;
import java.util.Comparator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.haloofwar.input.InputManager;

public class MyAnimation {

    private Animation<TextureRegion> walkAnimation;
    private Animation<TextureRegion> idleAnimation;
    private float stateTime = 0f;

    private TextureRegion currentFrame;
    private boolean isMoving = false;

    private Array<Texture> texturesToDispose = new Array<>();

    public MyAnimation(String path) {
        this.walkAnimation = loadAnimation("sprites/"+ path +"/walk", 0.1f);
        this.idleAnimation = loadAnimation("sprites/"+ path +"/idle", 0.2f); 
    }

    private Animation<TextureRegion> loadAnimation(String folderPath, float frameDuration) {
        Array<TextureRegion> frames = loadFramesFromFolder(folderPath);
        return new Animation<>(frameDuration, frames, PlayMode.LOOP);
    }
    
    private Array<TextureRegion> loadFramesFromFolder(String folderPath) {
        FileHandle folder = Gdx.files.internal(folderPath);
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
      //  isMoving = inputManager.isMoving();
        stateTime += Gdx.graphics.getDeltaTime();

        if (isMoving) {
            currentFrame = walkAnimation.getKeyFrame(stateTime);
        } else {
            currentFrame = idleAnimation.getKeyFrame(stateTime);
        }
    }

    public void render(Batch batch, float x, float y) {
        if (currentFrame != null) {
            batch.draw(currentFrame, x, y);
        }
    }

    public void dispose() {
        for (Texture texture : texturesToDispose) {
            texture.dispose();
        }
    }
}