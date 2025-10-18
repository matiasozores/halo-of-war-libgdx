package com.haloofwar.network;

import com.haloofwar.common.enumerators.BulletType;
import com.haloofwar.common.enumerators.EnemyType;
import com.haloofwar.common.enumerators.InventoryItemStatus;
import com.haloofwar.common.enumerators.LevelSceneType;
import com.haloofwar.common.enumerators.NPCType;
import com.haloofwar.common.enumerators.ObjectType;
import com.haloofwar.common.enumerators.PlayerType;
import com.haloofwar.common.enumerators.PowerUpType;
import com.haloofwar.engine.events.online.BuyWeaponEventOnline;
import com.haloofwar.engine.events.online.ChangeWeaponEventOnline;
import com.haloofwar.engine.events.online.InteractEventOnline;
import com.haloofwar.engine.events.online.LevelEnterEventOnline;
import com.haloofwar.engine.events.online.MeleeAttackEventOnline;
import com.haloofwar.engine.events.online.MoveEventOnline;
import com.haloofwar.engine.events.online.NextCutsceneEventOnline;
import com.haloofwar.engine.events.online.ShootBulletEventOnline;
import com.haloofwar.engine.interfaces.Disposable;
import com.haloofwar.interfaces.Weapon;

public interface GameController extends Disposable {
	void connect();
	void startGame(final int kratosId, final int masterchiefId, PlayerType playerType);
	void onServerClosed();
	
	void updateMovement(final int identifier, float x, float y, float dirX, float dirY);
	void updateInventory(final int identifier, PlayerType playerType, int amount, ObjectType itemType, InventoryItemStatus status);
	void updateScene(LevelSceneType levelType);
	void buySuccessWeapon(Weapon weapon, PlayerType playerType, final int PRICE);
	void changeWeaponSuccess(PlayerType playerType, Weapon weapon);
	void spawnItem(final int identifier, ObjectType objectType, float x, float y);
	void spawnEnemy(final int identifier, EnemyType type, float x, float y); 
	void advanceCutscene();
	void gameOver();
	void shootBullet(final int identifier, float x, float y, float dirX, float dirY, int damage, float speed, BulletType type);
	void meleeAttck(final int identifier, float x, float y, float width, float height, int damage, float range);
	void removeEntity(final int identifier);
	void levelCompleted(LevelSceneType levelType);
	void talk(NPCType npcType);
	void spawnPowerUp(final int identifier, PowerUpType powerUpType, float x, float y);
	void switchToSpectator();
	void respawnPlayer(final PlayerType type);
	
	void onMove(MoveEventOnline event);
	void onInteract(InteractEventOnline event);
	void onBuyWeapon(BuyWeaponEventOnline event);
	void onChangeWeapon(ChangeWeaponEventOnline event);
	void onAdvanceCutscene(NextCutsceneEventOnline event);
	void onEnterLevel(LevelEnterEventOnline event);
	void onShoot(ShootBulletEventOnline event);
	void onMeleeAttack(MeleeAttackEventOnline event);
}
