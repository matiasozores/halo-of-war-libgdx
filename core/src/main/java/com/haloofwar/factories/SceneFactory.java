package com.haloofwar.factories;

import com.haloofwar.dependences.GameContext;
import com.haloofwar.entities.characters.Player;
import com.haloofwar.enumerators.game.SceneType;
import com.haloofwar.game.GameScene;
import com.haloofwar.game.safezonescene.Tutorial;

public final class SceneFactory {
	private SceneFactory() {}
	
    public static GameScene create(SceneType type, GameContext context, Player player) {
        switch (type) {
            case TUTORIAL:
                return new Tutorial(context, player);

            default:
                System.out.println("Escena no reconocida. Se carga el Tutorial por defecto.");
                return new Tutorial(context, player);
        }
    }
}
