package com.haloofwar.ecs.events;

import java.util.*;
import java.util.function.Consumer;

public class EventBus {
    private final Map<Class<?>, List<Consumer<?>>> listeners = new HashMap<>();

    /** Suscribe un listener a un tipo de evento específico */
    public <T> void subscribe(Class<T> eventType, Consumer<T> listener) {
        if (eventType == null || listener == null) {
        	return;
        }
        listeners.computeIfAbsent(eventType, k -> new ArrayList<>()).add(listener);
    }

    /** Desuscribe un listener de un tipo de evento */
    public <T> void unsubscribe(Class<T> eventType, Consumer<T> listener) {
        List<Consumer<?>> list = listeners.get(eventType);
        if (list != null) {
            list.remove(listener);
            if (list.isEmpty()) {
                listeners.remove(eventType);
            }
        }
    }

    /** Publica un evento a todos los listeners correspondientes */
    public <T> void publish(T event) {
        if (event == null) return;

        Class<?> eventClass = event.getClass();

        // Filtrar solo los listeners que aplican a este tipo de evento
        listeners.entrySet().stream()
            .filter(entry -> entry.getKey().isAssignableFrom(eventClass))
            .forEach(entry -> notifyListeners(entry.getKey(), entry.getValue(), event));
    }

    @SuppressWarnings("unchecked")
    private <T> void notifyListeners(Class<?> type, List<Consumer<?>> rawListeners, T event) {
        // Usamos copia para evitar ConcurrentModification
        for (Consumer<?> rawListener : new ArrayList<>(rawListeners)) {
            try {
                ((Consumer<T>) rawListener).accept(event);
            } catch (Exception ex) {
                System.err.println("Error en listener para " + type.getSimpleName() + ": " + ex);
                ex.printStackTrace();
            }
        }
    }


    /** Limpia todos los listeners */
    public void clear() {
        listeners.clear();
    }
}
