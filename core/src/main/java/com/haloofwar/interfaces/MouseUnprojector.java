package com.haloofwar.interfaces;

import com.badlogic.gdx.math.Vector3;

public interface MouseUnprojector {
	Vector3 unprojectMouse(int screenX, int screenY);
}
