package com.haloofwar.screens;

import com.badlogic.gdx.Screen;
import com.haloofwar.dependences.GameContext;

public class AudioScreen extends Menu {

	// en realidad hubiese hecho estas variables estaticas
	// ya que no deberian pertenecer a la clase en si. Mas bien se deberian
	// inicializar una vez y listo. Pero estoy traumado con esto asi que por las dudas lo dejo asi
    private final int MUSIC_VOLUME_OPTION = 0;
    private final int MUSIC_MUTE_OPTION = 1;
    private final int SOUND_VOLUME_OPTION = 2;
    private final int SOUND_MUTE_OPTION = 3;
    private final int BACK_OPTION = 4;

    public AudioScreen(GameContext context, Screen previousMenu) {
        super(context, "Musica y sonidos",new String[] {
            "", "", "", "", "Volver"
        }, previousMenu);
        this.updateAllTexts();
    }
    
    public AudioScreen(GameContext context) {
       this(context, null);
    }

    @Override
    protected void processOption(int optionIndex) {
        switch (optionIndex) {
            case MUSIC_MUTE_OPTION:
                this.context.getAudio().getMusic().toggleMute();
                break;

            case SOUND_MUTE_OPTION:
                this.context.getAudio().getSound().toggleMute();
                break;

            case BACK_OPTION:
                this.goBack();
                return;
        }
        this.updateAllTexts();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        this.handleVolumeAdjustment();
    }

    private void handleVolumeAdjustment() {
        if (!this.navigator.canMove()) {
            this.navigator.updateCooldown();
            return;
        }

        boolean changed = false;

        if (this.navigator.isSelectedIndex(this.MUSIC_VOLUME_OPTION)) {
            float current = context.getAudio().getMusic().getVolume();
            float newVolume = current;

            if (this.context.getInput().isArrowLeft()) {
                newVolume = Math.max(0f, current - 0.01f);
            } else if (context.getInput().isArrowRight()) {
                newVolume = Math.min(1f, current + 0.01f);
            }

            if (newVolume != current) {
                this.context.getAudio().getMusic().setVolume(newVolume);
                changed = true;
            }
        }

        if (this.navigator.isSelectedIndex(this.SOUND_VOLUME_OPTION)) {
            float current = context.getAudio().getSound().getVolume();
            float newVolume = current;

            if (this.context.getInput().isArrowLeft()) {
                newVolume = Math.max(0f, current - 0.01f);
            } else if (context.getInput().isArrowRight()) {
                newVolume = Math.min(1f, current + 0.01f);
            }

            if (newVolume != current) {
                this.context.getAudio().getSound().setVolume(newVolume);
                changed = true;
            }
        }

        if (changed) {
            this.navigator.forceCooldown(this.SELECTOR_COOLDOWN / 5);
            updateAllTexts();
        }
    }


    private void updateAllTexts() {
        this.updateText(this.MUSIC_VOLUME_OPTION, "Volumen de la musica: " + this.context.getAudio().getMusic().getVolumeText());
        this.updateText(this.MUSIC_MUTE_OPTION, "Musica muteada: " + (this.context.getAudio().getMusic().isMuted() ? "Si" : "No"));
        this.updateText(this.SOUND_VOLUME_OPTION, "Volumen de los sonidos: " + this.context.getAudio().getSound().getVolumeText());
        this.updateText(this.SOUND_MUTE_OPTION, "Sonido muteado: " + (this.context.getAudio().getSound().isMuted() ? "Si" : "No"));
    }
}
