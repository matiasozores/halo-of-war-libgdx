package com.haloofwar.network;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.common.enumerators.BulletType;
import com.haloofwar.common.enumerators.EnemyType;
import com.haloofwar.common.enumerators.InventoryItemStatus;
import com.haloofwar.common.enumerators.LevelSceneType;
import com.haloofwar.common.enumerators.NPCType;
import com.haloofwar.common.enumerators.ObjectType;
import com.haloofwar.common.enumerators.PlayerType;
import com.haloofwar.common.enumerators.PowerUpType;
import com.haloofwar.common.enumerators.SoundType;
import com.haloofwar.common.managers.TextureManager;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.AddWeaponToInventoryEvent;
import com.haloofwar.engine.events.ConnectEvent;
import com.haloofwar.engine.events.DisconnectEvent;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.EventListenerManager;
import com.haloofwar.engine.events.GameOverEvent;
import com.haloofwar.engine.events.LevelCompletedEvent;
import com.haloofwar.engine.events.LevelEnterEvent;
import com.haloofwar.engine.events.MeleeAttackEvent;
import com.haloofwar.engine.events.NewEntityEvent;
import com.haloofwar.engine.events.PlaySoundEvent;
import com.haloofwar.engine.events.RemoveEntityByIdentifierEvent;
import com.haloofwar.engine.events.RespawnPlayerEvent;
import com.haloofwar.engine.events.ShootBulletEvent;
import com.haloofwar.engine.events.SpawnEnemyEvent;
import com.haloofwar.engine.events.StartGameEvent;
import com.haloofwar.engine.events.SwitchToSpectatorEvent;
import com.haloofwar.engine.events.TalkEvent;
import com.haloofwar.engine.events.UpdateCurrentWeaponEvent;
import com.haloofwar.engine.events.online.BuyWeaponEventOnline;
import com.haloofwar.engine.events.online.ChangeWeaponEventOnline;
import com.haloofwar.engine.events.online.ConfirmationNextCutsceneEvent;
import com.haloofwar.engine.events.online.InteractEventOnline;
import com.haloofwar.engine.events.online.InventoryUpdateEventOnline;
import com.haloofwar.engine.events.online.LevelEnterEventOnline;
import com.haloofwar.engine.events.online.MeleeAttackEventOnline;
import com.haloofwar.engine.events.online.MoveEventOnline;
import com.haloofwar.engine.events.online.NextCutsceneEventOnline;
import com.haloofwar.engine.events.online.RemotePositionUpdateEventOnline;
import com.haloofwar.engine.events.online.ShootBulletEventOnline;
import com.haloofwar.game.factories.ObjectFactory;
import com.haloofwar.interfaces.Weapon;
import com.haloofwar.threads.ClientThread;

public class ClientGameController implements GameController {
	private final ClientThread client;
	private final EventBus gameplayBus;
	private final TextureManager textureManager;
	private final EventListenerManager listenerManager = new EventListenerManager();
	
	public ClientGameController(EventBus gameplayBus, TextureManager textureManager) {
		this.client = new ClientThread(this);
		this.client.start();
		this.gameplayBus = gameplayBus;
		this.textureManager = textureManager;
		
		this.listenerManager.add(this.gameplayBus, MoveEventOnline.class, this::onMove);
		this.listenerManager.add(this.gameplayBus, InteractEventOnline.class, this::onInteract);
		this.listenerManager.add(this.gameplayBus, BuyWeaponEventOnline.class, this::onBuyWeapon);
		this.listenerManager.add(this.gameplayBus, ChangeWeaponEventOnline.class, this::onChangeWeapon);
		this.listenerManager.add(this.gameplayBus, NextCutsceneEventOnline.class, this::onAdvanceCutscene);
		this.listenerManager.add(this.gameplayBus, LevelEnterEventOnline.class, this::onEnterLevel);
		this.listenerManager.add(this.gameplayBus, ShootBulletEventOnline.class, this::onShoot);
		this.listenerManager.add(this.gameplayBus, MeleeAttackEventOnline.class, this::onMeleeAttack);
		this.client.sendFirstMessage();
	}

	@Override
	public void connect() {
		this.gameplayBus.publish(new ConnectEvent());
	}

	@Override
	public void onServerClosed() {
		this.gameplayBus.publish(new DisconnectEvent());
	}

	@Override
	public void startGame(final int kratosId, final int masterchiefId, PlayerType playerType) {
		Gdx.app.postRunnable(() -> {
			this.gameplayBus.publish(new StartGameEvent(kratosId, masterchiefId, playerType));
		});
	}

	@Override
	public void updateMovement(int identifier, float x, float y, float dirX, float dirY) {
		Gdx.app.postRunnable(() -> {
			this.gameplayBus.publish(new RemotePositionUpdateEventOnline(identifier, x, y, dirX, dirY));
		});
	}
	
	@Override
	public void spawnItem(final int identifier, ObjectType type, float x, float y) {
		Gdx.app.postRunnable(() -> {
			Entity entity = ObjectFactory.createItem(identifier, new Rectangle(x,y,16,16), type, this.textureManager);
			this.gameplayBus.publish(new NewEntityEvent(entity));
		});
	}

	@Override
	public void updateInventory(final int identifier, PlayerType playerType, int amount, ObjectType itemType, InventoryItemStatus status) {
		Gdx.app.postRunnable(() -> {
			this.gameplayBus.publish(new InventoryUpdateEventOnline(identifier, itemType, amount, playerType, status));
			this.gameplayBus.publish(new PlaySoundEvent(SoundType.ITEM_PICKUP)); // le deberia llegar el sonido unicamente al que agarro el item
		});
	}

	@Override
	public void buySuccessWeapon(Weapon weapon, PlayerType playerType, final int PRICE) {
		this.gameplayBus.publish(new InventoryUpdateEventOnline(ObjectType.MONEDA_DE_ORO, PRICE, playerType, InventoryItemStatus.REMOVE));
		this.gameplayBus.publish(new AddWeaponToInventoryEvent(weapon, playerType) );
		this.gameplayBus.publish(new PlaySoundEvent(SoundType.PURCHASE));
	}

	@Override
	public void changeWeaponSuccess(PlayerType playerType, Weapon weapon) {
		this.gameplayBus.publish(new UpdateCurrentWeaponEvent(playerType, weapon));
		this.gameplayBus.publish(new PlaySoundEvent(SoundType.SELECT_WEAPON));
	}
	
	@Override
	public void updateScene(LevelSceneType type) {
		Gdx.app.postRunnable(() -> {
			this.gameplayBus.publish(new LevelEnterEvent(type));
		});
	}
	
	@Override
	public void spawnEnemy(int IDENTIFIER, EnemyType type, float x, float y) {
		Gdx.app.postRunnable(() -> {
			this.gameplayBus.publish(new SpawnEnemyEvent(IDENTIFIER, type, x, y));
		});
	}
	
	@Override
	public void advanceCutscene() {
		Gdx.app.postRunnable(() -> {
			this.gameplayBus.publish(new ConfirmationNextCutsceneEvent());
		});
	}
	
	@Override
	public void gameOver() {
		Gdx.app.postRunnable(() -> {
			this.gameplayBus.publish(new GameOverEvent());
		});
	}
	
	@Override
	public void shootBullet(final int identifier, float x, float y, float dirX, float dirY, int damage, float speed, BulletType type) {
		Gdx.app.postRunnable(() -> {
			this.gameplayBus.publish(new ShootBulletEvent(identifier, x, y, dirX, dirY, damage, speed, type));
		});
	}
	
	@Override
	public void meleeAttck(int identifier, float x, float y, float width, float height, int damage, float range) {
		Gdx.app.postRunnable(() -> {
			this.gameplayBus.publish(new MeleeAttackEvent(identifier, x, y, width, height, damage, range));
		});
	}
	

	@Override
	public void removeEntity(int identifier) {
		Gdx.app.postRunnable(() -> {
			this.gameplayBus.publish(new RemoveEntityByIdentifierEvent(identifier));
		});
	}
	
	@Override
	public void levelCompleted(LevelSceneType levelType) {
		Gdx.app.postRunnable(() -> {
			this.gameplayBus.publish(new LevelCompletedEvent(levelType));
		});
	}
	

	@Override
	public void talk(NPCType npcType) {
		Gdx.app.postRunnable(() -> {
			this.gameplayBus.publish(new TalkEvent(npcType.getLines(), npcType.getHead()));
		});
	}
	
	@Override
	public void spawnPowerUp(int identifier, PowerUpType powerUpType, float x, float y) {
		Gdx.app.postRunnable(() -> {
			Entity entity = ObjectFactory.createPowerUp(identifier, new Rectangle(x,y,16,16), powerUpType, this.textureManager);
			this.gameplayBus.publish(new NewEntityEvent(entity));
		});
	}
	
	@Override
	public void switchToSpectator() {
		this.gameplayBus.publish(new SwitchToSpectatorEvent());
	}
	
	@Override
	public void respawnPlayer(PlayerType type) {
		Gdx.app.postRunnable(() -> {
			this.gameplayBus.publish(new RespawnPlayerEvent(type));
		});
	}
	
	@Override
	public void onMove(MoveEventOnline event) {
		String message = "Move:" + event.getIDENTIFIER() + ":" + event.getDirection().toString() + ":" + event.isPressed();
		this.client.sendMessage(message);
	}

	@Override
	public void onInteract(InteractEventOnline event) {
		String message = "Interact:" + event.type.getName() + ":" + event.isPressed;
		this.client.sendMessage(message);
	}
	
	@Override
	public void onBuyWeapon(BuyWeaponEventOnline event) {
		String message = "BuyWeapon:" + event.playerType.getName() + ":" + event.weapon.getName();
		this.client.sendMessage(message);
	}

	@Override
	public void onChangeWeapon(ChangeWeaponEventOnline event) {
		String message = "ChangeWeapon:" + event.playerType.getName() + ":" + event.weapon.getName();
		this.client.sendMessage(message);
	}
	
	@Override
	public void onAdvanceCutscene(NextCutsceneEventOnline event) {
		String message = "NextCutscene";
		this.client.sendMessage(message);
	}
	
	@Override
	public void onEnterLevel(LevelEnterEventOnline event) {
		String message = "EnterLevel:" + event.type.getName();
		this.client.sendMessage(message);
	}
	
	@Override
	public void onShoot(ShootBulletEventOnline event) {
		String message = "ShootBullet:" + event.x + ":" + event.y + ":" + event.dirX + ":" + event.dirY + ":" + event.damage + ":" + event.speed + ":" + event.type.getName();
		this.client.sendMessage(message);
	}

	@Override
	public void onMeleeAttack(MeleeAttackEventOnline event) {
		String message = "MeleeAttack:" + event.identifier + ":" + event.x + ":" + event.y + ":" + event.width + ":" + event.height + ":" + event.damage + ":" + event.range;
		this.client.sendMessage(message);
	}
	
	@Override
	public void dispose() {
		this.client.sendMessage("Disconnect");
		this.client.terminate();
	}
}
