package com.haloofwar.engine.events;

import com.haloofwar.common.enumerators.MusicTrack;

public class PlayMusicEvent {
    public final MusicTrack track;

    public PlayMusicEvent(MusicTrack track) {
        this.track = track;
    }
}


