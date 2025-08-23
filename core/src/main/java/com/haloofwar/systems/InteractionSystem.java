package com.haloofwar.systems;

import com.haloofwar.events.EventBus;
import com.haloofwar.events.InteractEvent;

public class InteractionSystem {
    public InteractionSystem(EventBus bus) {
        bus.subscribe(InteractEvent.class, this::onInput);
    }


    private void onInput(InteractEvent input) {
        if (input.isPressed()) {
        }
    }
}
