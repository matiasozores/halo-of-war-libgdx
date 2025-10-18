package com.haloofwar.network;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.haloofwar.common.enumerators.BulletType;
import com.haloofwar.common.enumerators.Direction;
import com.haloofwar.common.enumerators.PlayerType;
import com.haloofwar.engine.events.ChangeCurrentWeaponEvent;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.EventListenerManager;
import com.haloofwar.engine.events.InteractEvent;
import com.haloofwar.engine.events.MeleeAttackEvent;
import com.haloofwar.engine.events.NextEvent;
import com.haloofwar.engine.events.PurchaseWeaponEvent;
import com.haloofwar.engine.events.RespawnPlayerEventOnline;
import com.haloofwar.engine.events.ShootBulletEvent;
import com.haloofwar.engine.events.StartGameEvent;
import com.haloofwar.engine.events.online.BuyWeaponEventOnline;
import com.haloofwar.engine.events.online.ChangeWeaponEventOnline;
import com.haloofwar.engine.events.online.EnterLevelEventOnline;
import com.haloofwar.engine.events.online.GameOverEventOnline;
import com.haloofwar.engine.events.online.LevelCompletedEventOnline;
import com.haloofwar.engine.events.online.MoveEventOnline;
import com.haloofwar.engine.events.online.NextCutsceneEventOnline;
import com.haloofwar.engine.events.online.RemoveEntityEventOnline;
import com.haloofwar.engine.events.online.ShootBulletEventOnline;
import com.haloofwar.engine.events.online.SpawnEnemyEventOnline;
import com.haloofwar.engine.events.online.SpawnItemEventOnline;
import com.haloofwar.engine.events.online.SpawnPowerUpEventOnline;
import com.haloofwar.engine.events.online.StartGameEventOnline;
import com.haloofwar.engine.events.online.SwitchToSpectatorEventOnline;
import com.haloofwar.engine.events.online.TalkEventOnline;
import com.haloofwar.engine.events.online.UpdateEntityPositionEventOnline;
import com.haloofwar.engine.events.online.UpdateInventoryEventOnline;
import com.haloofwar.interfaces.Weapon;
import com.haloofwar.network.components.Client;
import com.haloofwar.threads.ServerThread;

public class ServerGameController implements ServerController {
    private final int MAX_PLAYERS = 2;
    private final Map<String, Client> clients = new HashMap<>();
    private boolean serverStarted = false;
    private ServerThread server;
    private EventBus globalBus;
    private EventBus gameplayBus;
    private EventListenerManager listenerManager = new EventListenerManager();

    public ServerGameController(EventBus globalBus, EventBus gameplayBus) {
        this.server = new ServerThread(this);
        this.globalBus = globalBus;
        this.gameplayBus = gameplayBus;

        this.listenerManager.add(this.gameplayBus, UpdateEntityPositionEventOnline.class, this::updateEntitiesPosition);
        this.listenerManager.add(this.gameplayBus, SpawnItemEventOnline.class, this::onSpawnItem);
        this.listenerManager.add(this.gameplayBus, RemoveEntityEventOnline.class, this::onRemoveEntity);
        this.listenerManager.add(this.gameplayBus, UpdateInventoryEventOnline.class, this::updateInventory);
        this.listenerManager.add(this.gameplayBus, BuyWeaponEventOnline.class, this::onBuyWeapon);
        this.listenerManager.add(this.gameplayBus, ChangeWeaponEventOnline.class, this::onChangeWeapon);
        this.listenerManager.add(this.gameplayBus, EnterLevelEventOnline.class, this::updateScene);
        this.listenerManager.add(this.gameplayBus, NextCutsceneEventOnline.class, this::onAdvanceCutscene);
        this.listenerManager.add(this.gameplayBus, SpawnEnemyEventOnline.class, this::onSpawnEnemy);
        this.listenerManager.add(this.gameplayBus, GameOverEventOnline.class, this::onGameOver);
        this.listenerManager.add(this.gameplayBus, ShootBulletEventOnline.class, this::onShoot);
        this.listenerManager.add(this.gameplayBus, StartGameEventOnline.class, this::onStartGame);
        this.listenerManager.add(this.gameplayBus, LevelCompletedEventOnline.class, this::onLevelCompleted);
        this.listenerManager.add(this.gameplayBus, TalkEventOnline.class, this::onTalk);
        this.listenerManager.add(this.gameplayBus, SpawnPowerUpEventOnline.class, this::onSpawnPowerUp);
        this.listenerManager.add(this.gameplayBus, SwitchToSpectatorEventOnline.class, this::onPlayerDied);
        this.listenerManager.add(this.gameplayBus, RespawnPlayerEventOnline.class, this::onRespawnPlayer);
    }
    
    public void start() {
    	if(this.serverStarted) {
    		System.out.println("Ya se ha iniciado el servidor...");
    		return;
    	}
    	
    	this.server.start();
    	this.serverStarted = true;
    }

    @Override
    public void addClient(Client client){
        if (this.clients.size() >= this.MAX_PLAYERS){
            System.out.println("Servidor lleno...");
            return;
        }

        if (this.clients.containsKey(client.getKey())){
            System.out.println("El cliente ya existe...");
            return;
        }

        this.clients.put(client.getKey(), client);
        this.server.sendMessage("Connected", client.getIp(), client.getPort());
        
        if(this.clients.size() == this.MAX_PLAYERS){
        	this.clients.get(client.getKey()).playerType = PlayerType.MASTER_CHIEF;
            this.startGame();
        } else {
        	this.clients.get(client.getKey()).playerType = PlayerType.KRATOS;
        }
    }
    
	@Override
	public void startGame() {
		this.globalBus.publish(new StartGameEvent());
	}

    @Override
    public void removeClient(Client client){
        if (this.clients.remove(client.getKey()) == null) {
        	System.out.println("Intento de eliminar cliente que no existía: " + client.getKey());
        } 
    }

    private void sendMessageToAll(String message) {
        for(Client client : this.clients.values()){
            this.server.sendMessage(message, client.getIp(), client.getPort());
        }
    }
    
    private void sendMessageToPlayerType(String message, PlayerType type) {
        for (Client client : clients.values()) {
            if (client.playerType == type) {
                this.server.sendMessage(message, client.getIp(), client.getPort());
            }
        }
    }

    
	// Para actualizar servidor
	
    @Override
	public void moveEntity(final int identifier, Direction direction, boolean pressed) {
		this.gameplayBus.publish(new MoveEventOnline(identifier, direction, pressed));
	}

    @Override
	public void interact(PlayerType type, boolean isPressed) {
		this.gameplayBus.publish(new InteractEvent(type, isPressed));
	}
    
    @Override
	public void buyWeapon(PlayerType type, Weapon weapon) {
		this.gameplayBus.publish(new PurchaseWeaponEvent(type, weapon));
	}
    
    @Override
	public void changeWeapon(PlayerType playerType, Weapon weapon) {
		this.gameplayBus.publish(new ChangeCurrentWeaponEvent(playerType, weapon));
	}
    
    @Override
	public void advanceCutscene() {
		this.gameplayBus.publish(new NextEvent(true));
	}
	
	@Override
	public void shootBullet(float x, float y, float dirX, float dirY, int damage, float speed, BulletType type) {
		Gdx.app.postRunnable(() -> {
			this.gameplayBus.publish(new ShootBulletEvent(x, y, dirX, dirY, damage, speed, type));
		});
	}
	
	@Override
	public void meleeAttack(final int identifier, float x, float y, float width, float height, int damage, float range) {
		this.gameplayBus.publish(new MeleeAttackEvent(identifier, x, y, width, height, damage, range));
	}
    
    // Para actualizar cliente/clientes
	
	@Override
	public void onStartGame(StartGameEventOnline event) {
		for(Client client : this.clients.values()){
	    	String message = "StartGame:" + client.playerType.getName() + ":" + event.kratosId + ":" + event.masterchiefId;
	        this.server.sendMessage(message, client.getIp(), client.getPort());
	    }
	}
    
	@Override
	public void updateEntitiesPosition(UpdateEntityPositionEventOnline event) {
		String message = "UpdatePosition:" + event.identifier + ":" + event.x + ":" + event.y + ":" + event.dirX + ":" + event.dirY;
		this.sendMessageToAll(message);
	}

	@Override
	public void onSpawnItem(SpawnItemEventOnline event) {
		String message = "SpawnItem:" + event.identifier + ":" + event.item.getName() + ":" +  event.x + ":" + event.y;
		this.sendMessageToAll(message);
	}
	
	@Override
	public void onSpawnEnemy(SpawnEnemyEventOnline event) {
		String message = "SpawnEnemy:" + event.identifier + ":" + event.type.getName() + ":" + event.x + ":" + event.y;
		this.sendMessageToAll(message);
	}

	@Override
	public void updateInventory(UpdateInventoryEventOnline event) {
		String message = "UpdateInventory:" + event.identifier  + ":" + event.playerType.getName() + ":" + event.itemType.getName() + ":" + event.quantity + ":" + event.status;
		Client client = this.getClientByType(event.playerType);
		this.server.sendMessage(message, client.getIp(), client.getPort());
	}

	@Override
	public void onBuyWeapon(BuyWeaponEventOnline event) {
		String message = "BuySuccessWeapon:" + event.playerType.getName() + ":" + event.weapon.getName() + ":" + event.weapon.getPrice();
		this.sendMessageToAll(message);
	}

	@Override
	public void onChangeWeapon(ChangeWeaponEventOnline event) {
		String message = "ChangeSuccessWeapon:" + event.playerType.getName() + ":" + event.weapon.getName();
		this.sendMessageToAll(message);
	}

	@Override
	public void updateScene(EnterLevelEventOnline event) {
		String message = "EnterLevel:" + event.type.getName();
		this.sendMessageToAll(message);
	}

	@Override
	public void onAdvanceCutscene(NextCutsceneEventOnline event) {
		String message = "NextCutscene";
		this.sendMessageToAll(message);
	}
	
	@Override
	public void onGameOver(GameOverEventOnline event) {
		String message = "GameOver";
		this.sendMessageToAll(message);
	}
	
	@Override
	public void onShoot(ShootBulletEventOnline event) {
		String message = "ShootBullet:" + event.identifier + ":" + event.x + ":" + event.y + ":" + event.dirX + ":" + event.dirY + ":" + event.damage + ":" + event.speed + ":" + event.type.getName();
		this.sendMessageToAll(message);
	}
	
	@Override
	public void onRemoveEntity(RemoveEntityEventOnline event) {
		String message = "RemoveEntity:" + event.identifier;
		this.sendMessageToAll(message);
	}
	
	@Override
	public void onLevelCompleted(LevelCompletedEventOnline event) {
		String message = "LevelCompleted:" + event.levelType.getName();
		this.sendMessageToAll(message);
	}
	
	@Override
	public void onTalk(TalkEventOnline event) {
		String message = "StartDialogue:" + event.npcType.getName();
		this.sendMessageToPlayerType(message, event.playerType);
	}
	
	@Override
	public void onSpawnPowerUp(SpawnPowerUpEventOnline event) {
		String message = "SpawnPowerUp:" + event.identifier + ":" + event.powerUp.getName() + ":" +  event.x + ":" + event.y;
		this.sendMessageToAll(message);	
	}
	
	@Override
	public void onPlayerDied(SwitchToSpectatorEventOnline event) {
		String message = "SwitchToSpectator";
		this.sendMessageToPlayerType(message, event.type);
	}
	
	@Override
	public void onRespawnPlayer(RespawnPlayerEventOnline event) {
		String message = "RespawnPlayer:" + event.type.getName();
		this.sendMessageToAll(message);
	}
	
    private Client getClientByType(PlayerType type) {
		boolean found = false;
		int i = 0;
		Client client = null;
		
		while(!found && i < this.clients.values().size()) {
			client = (Client) this.clients.values().toArray()[i];
			if(client.playerType.equals(type)) {
				found = true;
			} else {
				i++;
			}
		}
    	
		return client;
	}
    
    @Override
    public void dispose(){
        this.sendMessageToAll("ServerClosed");
        this.server.terminate();
        this.listenerManager.clear();
    
    }
}
