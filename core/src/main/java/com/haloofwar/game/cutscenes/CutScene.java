package com.haloofwar.game.cutscenes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.common.enumerators.GameState;
import com.haloofwar.common.enumerators.MusicTrack;
import com.haloofwar.common.enumerators.SoundType;
import com.haloofwar.engine.cameras.GameStaticCamera;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.EventListenerManager;
import com.haloofwar.engine.events.GameStateEvent;
import com.haloofwar.engine.events.NextEvent;
import com.haloofwar.engine.events.PeacefulEvent;
import com.haloofwar.engine.events.PlayMusicEvent;
import com.haloofwar.engine.events.PlaySoundEvent;
import com.haloofwar.engine.events.StopMusicEvent;
import com.haloofwar.engine.events.StopSoundsEvent;
import com.haloofwar.engine.events.online.ConfirmationNextCutsceneEvent;
import com.haloofwar.engine.events.online.NextCutsceneEventOnline;

public class CutScene {
	private final Texture[] images;
	private final SoundType[] SOUNDS;
	private final SpriteBatch batch;
	private final EventBus bus;
	private final EventListenerManager listenerManager = new EventListenerManager();	
	private final GameStaticCamera camera;
	private int currentIndex = 0;
	private boolean finished = false;
	private boolean started = false;
	private MusicTrack previousMusic;
	
	public CutScene(final CutSceneData DATA, GameStaticCamera camera, MusicTrack previousMusic) {
		this.images = DATA.images;
		this.SOUNDS = DATA.sounds;
		this.batch = DATA.batch;
		this.bus = DATA.bus;
		this.camera = camera;
		this.previousMusic = previousMusic;
		
		this.listenerManager.add(bus, NextEvent.class, this::onNext);
		this.listenerManager.add(bus, ConfirmationNextCutsceneEvent.class, this::onConfirmation);
		this.bus.publish(new PeacefulEvent(true));
		this.bus.publish(new GameStateEvent(GameState.WAITING));
	}

	private void onNext(NextEvent event) {
		if(event.isPressed()) {
			this.bus.publish(new NextCutsceneEventOnline());
		}
	}
	
	private void onConfirmation(ConfirmationNextCutsceneEvent event) {
		if (this.currentIndex >= this.images.length - 1) {
			this.finished = true;
			this.bus.publish(new GameStateEvent(GameState.PLAYING));
			this.bus.publish(new PeacefulEvent(false));
			this.bus.publish(new StopMusicEvent());
			this.bus.publish(new StopSoundsEvent());
			this.bus.publish(new PlayMusicEvent(this.previousMusic));
		} else {
			currentIndex++;
			this.bus.publish(new StopSoundsEvent());
			this.bus.publish(new PlaySoundEvent(this.SOUNDS[this.currentIndex]));
		}
	}

	
	public void render(float delta) {
		batch.setProjectionMatrix(camera.getOrthographic().combined);
		batch.begin();
		if (currentIndex < images.length) {
			batch.draw(images[currentIndex], 0, 0);
		}
		batch.end();
	}

	public void update(float delta) {
		if(!this.started) {
			this.started = true;
			this.bus.publish(new StopMusicEvent());
			this.bus.publish(new PlayMusicEvent(MusicTrack.CUTSCENE));
			this.bus.publish(new PlaySoundEvent(this.SOUNDS[0]));
		}
	}

	public boolean isFinished() {
		return finished;
	}
	
	public void dispose() {
		
	}
}
