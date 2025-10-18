package com.haloofwar.network;

import com.haloofwar.common.enumerators.BulletType;
import com.haloofwar.common.enumerators.Direction;
import com.haloofwar.common.enumerators.PlayerType;
import com.haloofwar.engine.events.RespawnPlayerEventOnline;
import com.haloofwar.engine.events.online.BuyWeaponEventOnline;
import com.haloofwar.engine.events.online.ChangeWeaponEventOnline;
import com.haloofwar.engine.events.online.EnterLevelEventOnline;
import com.haloofwar.engine.events.online.GameOverEventOnline;
import com.haloofwar.engine.events.online.LevelCompletedEventOnline;
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
import com.haloofwar.engine.interfaces.Disposable;
import com.haloofwar.interfaces.Weapon;
import com.haloofwar.network.components.Client;

public interface ServerController extends Disposable {
	
	// Para todo lo que llega
    void addClient(Client client);
    void removeClient(Client client);
    void startGame();
    
    void moveEntity(final int identifier, Direction direction, boolean pressed);
    void interact(PlayerType type, boolean isPressed);
    void buyWeapon(PlayerType type, Weapon weapon);
    void changeWeapon(PlayerType playerType, Weapon weapon);
    void advanceCutscene();
    void shootBullet(float x, float y, float dirX, float dirY, int damage, float speed, BulletType type);
    void meleeAttack(final int identifier, float x, float y, float width, float height, int damage, float range);

    // Para eventos
    void onStartGame(StartGameEventOnline event);
    
    void updateEntitiesPosition(UpdateEntityPositionEventOnline event);
    void updateInventory(UpdateInventoryEventOnline event);
    void updateScene(EnterLevelEventOnline event);

    void onSpawnItem(SpawnItemEventOnline event);
    void onBuyWeapon(BuyWeaponEventOnline event);
    void onChangeWeapon(ChangeWeaponEventOnline event);
    void onSpawnEnemy(SpawnEnemyEventOnline event);
    void onAdvanceCutscene(NextCutsceneEventOnline event);
    void onGameOver(GameOverEventOnline event);
    void onShoot(ShootBulletEventOnline event);
    void onRemoveEntity(RemoveEntityEventOnline event);
    void onLevelCompleted(LevelCompletedEventOnline event);
    void onTalk(TalkEventOnline event);
    void onSpawnPowerUp(SpawnPowerUpEventOnline event);
    void onPlayerDied(SwitchToSpectatorEventOnline event);
    void onRespawnPlayer(RespawnPlayerEventOnline event);
}