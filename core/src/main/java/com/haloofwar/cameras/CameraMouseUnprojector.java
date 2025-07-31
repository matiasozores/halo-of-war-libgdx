package com.haloofwar.cameras;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.haloofwar.interfaces.MouseUnprojector;

public class CameraMouseUnprojector implements MouseUnprojector{
    private final OrthographicCamera camera;

    public CameraMouseUnprojector(OrthographicCamera camera) {
        this.camera = camera;
    }

    @Override
    public Vector3 unprojectMouse(int screenX, int screenY) {
        return camera.unproject(new Vector3(screenX, screenY, 0));
    }
}
