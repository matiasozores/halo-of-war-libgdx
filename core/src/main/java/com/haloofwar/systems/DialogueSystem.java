package com.haloofwar.systems;

import java.util.LinkedList;
import java.util.Queue;

import com.haloofwar.enumerators.GameState;
import com.haloofwar.events.EventBus;
import com.haloofwar.events.GameStateEvent;
import com.haloofwar.events.HideDialogueEvent;
import com.haloofwar.events.NextEvent;
import com.haloofwar.events.ShowDialogueEvent;
import com.haloofwar.events.TalkEvent;
import com.badlogic.gdx.graphics.Texture;

public class DialogueSystem {
    private final EventBus bus;

    private boolean active = false;
    private Queue<String> lines = new LinkedList<>();
    private String currentLine = null;
    private Texture currentAvatar = null;

    public DialogueSystem(EventBus bus) {
        this.bus = bus;

        bus.subscribe(TalkEvent.class, this::onTalk);
        bus.subscribe(NextEvent.class, this::onNext);
    }

    private void onTalk(TalkEvent event) {
        if (this.active) return;

        this.lines.clear();
        for (String line : event.texts) {
            this.lines.add(line);
        }

        this.active = true;
        this.currentLine = this.lines.poll();
        this.currentAvatar = event.avatar;

        this.bus.publish(new GameStateEvent(GameState.WAITING)); 
        this.bus.publish(new ShowDialogueEvent(this.currentLine, this.currentAvatar));
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
        }
    }
}
