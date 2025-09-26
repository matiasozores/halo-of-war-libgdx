package com.haloofwar.engine.events;

import java.util.function.Consumer;

import com.haloofwar.engine.interfaces.Disposable;

public class EventListener<T> implements Disposable {

	private final EventBus bus;
	private final Class<T> type;
	private final Consumer<T> listener;
	private boolean disposed = false;

	public EventListener(final EventBus bus, final Class<T> type, final Consumer<T> listener) {
		this.bus = bus;
		this.type = type;
		this.listener = listener;
		bus.subscribe(type, listener);
	}
	
	@Override
	public void dispose() {
        if (!this.disposed) {
            this.bus.unsubscribe(this.type, this.listener);
            this.disposed = true;
        }
	}
}
