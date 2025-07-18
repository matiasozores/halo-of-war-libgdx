package com.haloofwar.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.haloofwar.utilities.Resources;

public class Crosshair {
    private ShapeRenderer shapeRenderer;
    private int mouseX, mouseY;
    private final int RANGO = 100;
    
    public Crosshair() {
        this.shapeRenderer = new ShapeRenderer();
    }

    public void update() {
    	Vector3 mouseWorldPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		Resources.getCameraGame().getCamera().unproject(mouseWorldPos);
		
		
		if (mouseWorldPos.x < 0 || mouseWorldPos.x > Gdx.graphics.getWidth() ||
			mouseWorldPos.y < 0 || mouseWorldPos.y > Gdx.graphics.getHeight()) {
			return; // Evita que el cursor esté fuera de los límites de la pantalla
		}
		
		this.mouseX = (int) mouseWorldPos.x;
		this.mouseY = (int) mouseWorldPos.y;
		Resources.setMousePosition(this.mouseX, this.mouseY);
	}
    
    public void render(Camera camera) {
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(1, 0, 0, 1); // Rojo

        // Línea horizontal
        shapeRenderer.line(this.mouseX - 3, this.mouseY, this.mouseX + 3, this.mouseY);

        // Línea vertical
        shapeRenderer.line(this.mouseX, this.mouseY - 3, this.mouseX, this.mouseY + 3);

        shapeRenderer.end();
    }

    

    
    public void dispose() {
        shapeRenderer.dispose();
    }
}
