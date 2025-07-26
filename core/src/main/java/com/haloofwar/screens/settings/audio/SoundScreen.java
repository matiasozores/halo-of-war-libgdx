package com.haloofwar.screens.settings.audio;

import com.haloofwar.dependences.GameContext;
import com.haloofwar.screens.Menu;

public class SoundScreen extends Menu {
	
	private final int VOLUME_OPTION = 0;
	private final int MUTE_OPTION = 1;
	private final int BACK_OPTION = 2;
	
	public SoundScreen(GameContext gameContext) {
		super(gameContext, new String[] {
				"Volumen de los sonidos: " + gameContext.getSoundManager().getSoundVolumeText(),
				"Sonido muteado: " + (gameContext.getSoundManager().isSoundMuted() ? "Sí" : "No"),
				"Volver"
		});
	}

	@Override
	protected void processOption(int optionIndex) {
		switch (optionIndex) {
		
		case MUTE_OPTION:
			this.gameContext.getSoundManager().toggleSoundMute();
			this.updateSoundTexts();
			break;
		case BACK_OPTION:
			this.gameContext.getGame().setScreen(new AudioScreen(this.gameContext));
			break;
		}
	}

	@Override
	public void update() {
		super.update();
		this.handleVolumeControl();
	}

	private void handleVolumeControl() {
		if (this.selectedIndex != 0) {
			return;
		}
			
		if (this.selectorCooldown > 0) {
			this.selectorCooldown--;
			return;
		}

		final float CURRENT_VOLUME = this.gameContext.getSoundManager().getSoundVolume();
		float newVolume = CURRENT_VOLUME;

		if (this.inputManager.isArrowLeft()) {
			newVolume = Math.max(0f, CURRENT_VOLUME - 0.01f);
		} else if (this.inputManager.isArrowRight()) {
			newVolume = Math.min(1f, CURRENT_VOLUME + 0.01f);
		}

		if (newVolume != CURRENT_VOLUME) {
			this.gameContext.getSoundManager().setSoundVolume(newVolume);
			this.selectorCooldown = this.SELECTOR_COOLDOWN / 5;
			this.updateSoundTexts();
		}
	}
	
	private void updateSoundTexts() {
		this.updateText(this.VOLUME_OPTION, "Volumen de los sonidos: " + this.gameContext.getSoundManager().getSoundVolumeText());
		this.updateText(this.MUTE_OPTION, "Sonido muteado: " + (this.gameContext.getSoundManager().isSoundMuted() ? "Sí" : "No"));
	}
}
