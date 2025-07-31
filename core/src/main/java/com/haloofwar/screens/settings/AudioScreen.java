package com.haloofwar.screens.settings;

import com.badlogic.gdx.Screen;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.screens.Menu;

public class AudioScreen extends Menu {

    private static final int MUSIC_VOLUME_OPTION = 0;
    private static final int MUSIC_MUTE_OPTION = 1;
    private static final int SOUND_VOLUME_OPTION = 2;
    private static final int SOUND_MUTE_OPTION = 3;
    private static final int BACK_OPTION = 4;

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
                this.context.getAudio().getSound().toggleSoundMute();
                break;

            case BACK_OPTION:
                this.goBack();
                return;
        }
        this.updateAllTexts();
    }

    @Override
    public void update() {
        super.update();
        this.handleVolumeAdjustment();
    }

    private void handleVolumeAdjustment() {
        if (this.selectorCooldown > 0) {
            this.selectorCooldown--;
            return;
        }

        boolean changed = false;

        if (this.selectedIndex == MUSIC_VOLUME_OPTION) {
            float current = this.context.getAudio().getMusic().getVolume();
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
        
        if (this.selectedIndex == SOUND_VOLUME_OPTION) {
            float current = this.context.getAudio().getSound().getVolume();
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
            this.selectorCooldown = this.SELECTOR_COOLDOWN / 5;
            this.updateAllTexts();
        }
    }

    private void updateAllTexts() {
        updateText(MUSIC_VOLUME_OPTION, "Volumen de la musica: " + this.context.getAudio().getMusic().getVolumeText());
        updateText(MUSIC_MUTE_OPTION, "Musica muteada: " + (this.context.getAudio().getMusic().isMuted() ? "Si" : "No"));
        updateText(SOUND_VOLUME_OPTION, "Volumen de los sonidos: " + this.context.getAudio().getSound().getSoundVolumeText());
        updateText(SOUND_MUTE_OPTION, "Sonido muteado: " + (this.context.getAudio().getSound().isSoundMuted() ? "Si" : "No"));
    }
}
