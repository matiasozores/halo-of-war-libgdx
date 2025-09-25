package com.haloofwar.common.enums;

public enum SoundType {
	// Principal
	NAVIGATE_MENU("audio/sounds/navigate_menu.wav"),
    LOAD_GAME("audio/sounds/load_game.wav"),
	ENTER_MENU("audio/sounds/enter_menu.wav"),
    
    // Juego
    NAVIGATE_GAME("audio/sounds/navigate_game.wav"),
    HIT("audio/sounds/elite_hit.wav"),
	ITEM_PICKUP("audio/sounds/item_pickup.wav"),
    GAME_OVER("audio/sounds/game_over.wav"),
    PURCHASE("audio/sounds/purchase.wav"),
    SELECT_WEAPON("audio/sounds/select_weapon.wav"),
    DIALOGUE("audio/sounds/dialogue.wav"),
    // Disparos de armas
    SHOOT_ASSAULT("audio/sounds/bullets/shoot_assault.wav"),
    SHOOT_PISTOL("audio/sounds/bullets/shoot_pistol.wav"),
    SHOOT_SHOTGUN("audio/sounds/bullets/shoot_shotgun.wav"),
    SHOOT_SMG("audio/sounds/bullets/shoot_smg.wav"),
    SHOOT_MG("audio/sounds/bullets/shoot_mg.wav"),
    SHOOT_SNIPER("audio/sounds/bullets/shoot_sniper.wav"),
    SHOOT_REVOLVER("audio/sounds/bullets/shoot_revolver.wav"),
	
	// Sonidos de cinematicas
	INTRO_VOICE_1("audio/sounds/off/intro_voice_1.wav"),
	INTRO_VOICE_2("audio/sounds/off/intro_voice_2.wav"),
	INTRO_VOICE_3("audio/sounds/off/intro_voice_3.wav"),;

    private final String path;

    private SoundType(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }
}
