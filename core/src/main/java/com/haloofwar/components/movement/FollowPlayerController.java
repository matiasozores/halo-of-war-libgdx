package com.haloofwar.components.movement;

import com.haloofwar.entities.players.Player;
import com.haloofwar.interfaces.MovementController;

public class FollowPlayerController implements MovementController {
    private final Player player;
    private float currentX, currentY;

    public FollowPlayerController(Player player, float startX, float startY) {
        this.player = player;
        this.currentX = startX;
        this.currentY = startY;
    }

    @Override
    public float getDirectionX() {
        float deltaX = player.getX() - currentX;
        if (Math.abs(deltaX) < 100) return 0f;
        return Math.signum(deltaX);
    }

    @Override
    public float getDirectionY() {
        float deltaY = player.getY() - currentY;
        if (Math.abs(deltaY) < 5) return 0f;
        return Math.signum(deltaY);
    }

    public void updatePosition(float x, float y) {
        this.currentX = x;
        this.currentY = y;
    }
}
