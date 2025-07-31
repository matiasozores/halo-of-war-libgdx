package com.haloofwar.components;

import com.haloofwar.dependences.audio.SoundManager;
import com.haloofwar.enumerators.SoundType;

public class AudioComponent {
    private final SoundManager soundManager;

    public AudioComponent(SoundManager soundManager) {
        this.soundManager = soundManager;
    }

    /**
     * Reproduce un sonido asociado a esta entidad.
     */
    public void play(SoundType soundType) {
        this.soundManager.play(soundType);
    }

    /**
     * Llamado cada frame (opcional por si necesitás lógica temporal de audio).
     */
    public void update(float delta) {
        // Por ahora no hace nada, pero podés agregar lógica para cooldowns, colas de sonidos, etc.
    }

    /**
     * Limpia referencias si es necesario.
     */
    public void dispose() {
        // No hace falta por ahora porque el SoundManager se encarga del manejo de recursos.
    }
}
