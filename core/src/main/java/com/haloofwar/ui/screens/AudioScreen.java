package com.haloofwar.ui.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.MathUtils;
import com.haloofwar.common.context.GameContext;
import com.haloofwar.common.enums.Background;
import com.haloofwar.common.enums.Direction;
import com.haloofwar.common.enums.SoundType;
import com.haloofwar.engine.events.NavigationEvent;
import com.haloofwar.engine.events.PlaySoundEvent;
import com.haloofwar.ui.menus.Menu;

public class AudioScreen extends Menu {

    private final int MUSIC_VOLUME_OPTION = 0;
    private final int MUSIC_MUTE_OPTION = 1;
    private final int SOUND_VOLUME_OPTION = 2;
    private final int SOUND_MUTE_OPTION = 3;
    private final int BACK_OPTION = 4;

    private final float VOLUME_ADJUST_COOLDOWN = 0.1f;

    private boolean leftFlag = false;
    private boolean rightFlag = false;
    private float volumeCooldown = 0f;

    public AudioScreen(final GameContext CONTEXT, final Screen PREVIOUS_SCREEN) {
        super(CONTEXT, "Musica y sonidos", new String[] { "", "", "", "", "Volver" }, PREVIOUS_SCREEN, Background.PORTAL_MENU);
        this.updateAllTexts();
    }

    public AudioScreen(final GameContext CONTEXT) {
        this(CONTEXT, null);
    }

    @Override
    protected void processOption(int optionIndex) {
        switch (optionIndex) {
            case MUSIC_MUTE_OPTION:
                this.context.getAUDIO().getMusic().toggleMute();
                break;
            case SOUND_MUTE_OPTION:
                this.context.getAUDIO().getSound().toggleMute();
                break;
            case BACK_OPTION:
                this.goBack();
                return;
        }
        this.updateAllTexts();
    }

    @Override
    protected void onNavigationEvent(NavigationEvent event) {
        super.onNavigationEvent(event);

        if (event.direction == Direction.LEFT) {
        	this.leftFlag = event.isPressed;
        }
        
        if (event.direction == Direction.RIGHT) {
        	this.rightFlag = event.isPressed;
        }
    }

    @Override
    protected void update(float delta) {
        super.update(delta);

        boolean moved = false;
        
        if (this.volumeCooldown > 0f) {
            this.volumeCooldown -= delta;
        } else {
            if (this.leftFlag) {
            	moved = true;
                this.adjustVolume(-0.01f);
                this.volumeCooldown = this.VOLUME_ADJUST_COOLDOWN;
            } else if (this.rightFlag) {
            	moved = true;
                this.adjustVolume(0.01f);
                this.volumeCooldown = this.VOLUME_ADJUST_COOLDOWN;
            }
        }
        
        if(moved) {
        	this.context.getGLOBAL_BUS().publish(new PlaySoundEvent(SoundType.NAVIGATE_MENU));
        }
    }

    private void adjustVolume(float delta) {
        boolean changed = false;

        if (this.navigator.isSelectedIndex(this.MUSIC_VOLUME_OPTION)) {
            float current = this.context.getAUDIO().getMusic().getVolume();
            float newVolume = MathUtils.clamp(current + delta, 0f, 1f);
            if (newVolume != current) {
                this.context.getAUDIO().getMusic().setVolume(newVolume);
                changed = true;
            }
        }

        if (this.navigator.isSelectedIndex(this.SOUND_VOLUME_OPTION)) {
            float current = this.context.getAUDIO().getSound().getVolume();
            float newVolume = MathUtils.clamp(current + delta, 0f, 1f);
            if (newVolume != current) {
                this.context.getAUDIO().getSound().setVolume(newVolume);
                changed = true;
            }
        }

        if (changed) {
            this.updateAllTexts();
        }
    }

    private void updateAllTexts() {
        updateText(this.MUSIC_VOLUME_OPTION,
            "Volumen de la musica: " + this.context.getAUDIO().getMusic().getVolumeText());
        updateText(this.MUSIC_MUTE_OPTION,
            "Musica muteada: " + (this.context.getAUDIO().getMusic().isMuted() ? "Si" : "No"));
        updateText(this.SOUND_VOLUME_OPTION,
            "Volumen de los sonidos: " + this.context.getAUDIO().getSound().getVolumeText());
        updateText(this.SOUND_MUTE_OPTION,
            "Sonido muteado: " + (this.context.getAUDIO().getSound().isMuted() ? "Si" : "No"));
    }
}
