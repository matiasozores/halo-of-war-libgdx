package com.haloofwar.engine.events;

import java.util.*;
import java.util.function.Consumer;

public class EventListenerManager {

    private final Map<Class<?>, Consumer<?>> registered = new HashMap<>();
    private final List<EventListener<?>> listeners = new ArrayList<>();

    public <T> void add(EventBus bus, Class<T> type, Consumer<T> listener) {
        if (this.registered.containsKey(type)) {
            return;
        }

        final EventListener<T> disposable = new EventListener<>(bus, type, listener);
        this.listeners.add(disposable);
        this.registered.put(type, listener);
    }

    public void clear() {
        for (EventListener<?> listener : this.listeners) {
            listener.dispose();
        }
        this.listeners.clear();
        this.registered.clear();
    }
}
