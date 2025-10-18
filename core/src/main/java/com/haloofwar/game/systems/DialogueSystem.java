package com.haloofwar.game.systems;

import java.util.LinkedList;
import java.util.Queue;

import com.badlogic.gdx.graphics.Texture;
import com.haloofwar.common.enumerators.GameState;
import com.haloofwar.common.enumerators.SoundType;
import com.haloofwar.common.managers.TextureManager;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.GameStateEvent;
import com.haloofwar.engine.events.HideDialogueEvent;
import com.haloofwar.engine.events.NextEvent;
import com.haloofwar.engine.events.PlaySoundEvent;
import com.haloofwar.engine.events.ShowDialogueEvent;
import com.haloofwar.engine.events.TalkEvent;

public class DialogueSystem extends EventSystem{
    private final EventBus bus;

    private boolean active = false;
    private Queue<String> lines = new LinkedList<>();
    private String currentLine = null;
    private Texture currentAvatar = null;
    private TextureManager texture;
    
    public DialogueSystem(EventBus bus, TextureManager texture) {
        this.bus = bus;
        this.listenerManager.add(bus, TalkEvent.class, this::onTalk);
        this.listenerManager.add(bus, NextEvent.class, this::onNext);
        this.texture = texture;
    }

    private void onTalk(TalkEvent event) {
        if (this.active) return;

        this.lines.clear();
        for (String line : event.texts) {
            this.lines.add(line);
        }

        this.active = true;
        this.currentLine = this.lines.poll();
        this.currentAvatar = this.texture.get(event.avatar);

        this.bus.publish(new GameStateEvent(GameState.WAITING)); 
        this.bus.publish(new ShowDialogueEvent(this.currentLine, this.currentAvatar));
        this.bus.publish(new PlaySoundEvent(SoundType.DIALOGUE));
    }

    private void onNext(NextEvent event) {
        if (!this.active || !event.isPressed()) return;

        if (lines.isEmpty()) {
            this.active = false;
            this.currentLine = null;
            this.currentAvatar = null;

            this.bus.publish(new GameStateEvent(GameState.PLAYING));
            this.bus.publish(new HideDialogueEvent()); 
        } else {
            this.currentLine = this.lines.poll();
            this.bus.publish(new ShowDialogueEvent(this.currentLine, this.currentAvatar)); 
            this.bus.publish(new PlaySoundEvent(SoundType.DIALOGUE));
        }
    }
}
