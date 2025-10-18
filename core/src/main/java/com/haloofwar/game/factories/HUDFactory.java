package com.haloofwar.game.factories;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.common.context.GameContext;
import com.haloofwar.common.enumerators.Background;
import com.haloofwar.common.enumerators.FireArmType;
import com.haloofwar.common.enumerators.MeleeWeaponType;
import com.haloofwar.common.enumerators.PlayerType;
import com.haloofwar.common.enumerators.UIAsset;
import com.haloofwar.common.managers.TextureManager;
import com.haloofwar.engine.cameras.GameStaticCamera;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.game.components.EquipmentComponent;
import com.haloofwar.game.components.HealthComponent;
import com.haloofwar.game.components.InventoryComponent;
import com.haloofwar.game.components.NameComponent;
import com.haloofwar.game.components.PlayerComponent;
import com.haloofwar.game.dependences.LevelData;
import com.haloofwar.ui.HUD;
import com.haloofwar.ui.components.DialogueBox;
import com.haloofwar.ui.components.EquipmentPopup;
import com.haloofwar.ui.components.HUDComponent;
import com.haloofwar.ui.components.InterfaceLevel;
import com.haloofwar.ui.components.InterfaceLobby;
import com.haloofwar.ui.components.InventoryPopup;
import com.haloofwar.ui.components.PlayerInfoRenderer;
import com.haloofwar.ui.components.Popup;
import com.haloofwar.ui.components.ShopPopup;
import com.haloofwar.ui.hud.LevelHUD;
import com.haloofwar.ui.hud.LobbyHUD;

public final class HUDFactory {

    private final GameContext context;
    private final GameStaticCamera camera;
    private final SpriteBatch batch;
    private final EventBus gameplayBus;
    
    public HUDFactory(GameContext context) {
        this.context = context;
        this.camera = context.getStaticCamera();
        this.batch = context.getRender().getBatch();
        this.gameplayBus = context.getGameplay().getBus();
    }

    public HUD createLevelHUD(final LevelData DATA) {
        final Entity player = context.getGameplay().getCurrentPlayer();

        final HealthComponent healthComp = player.getComponent(HealthComponent.class);
        final NameComponent nameComp = player.getComponent(NameComponent.class);
        final PlayerComponent playerComp = player.getComponent(PlayerComponent.class);
        final EquipmentComponent EQUIPMENT = player.getComponent(EquipmentComponent.class);
        
        final PlayerInfoRenderer info = new PlayerInfoRenderer(
                context.getRender().getBatch(),
                context.getRender().getFont().getSmallFont(),
                context.getRender().getFont().getTitleFont(),
                context.getTEXTURE(),
                context.getTEXTURE().get(playerComp.type.getHead()),
                context.getTEXTURE().get(EQUIPMENT.getCurrentWeapon()),
                context.getTEXTURE().get(Background.PLACEHOLDER_ICON),
                nameComp,
                healthComp
        );

        
        final InterfaceLevel level = new InterfaceLevel(DATA, context.getRender().getBatch(), context.getRender().getFont().getDefaultFont());

        final HUDComponent[] components = {info, level};
        
        return new LevelHUD(components, this.camera, this.batch, this.gameplayBus);
    }

    public HUD createLobbyHUD() {
    	final TextureManager TEXTURE = this.context.getTEXTURE();
    	final BitmapFont FONT = this.context.getRender().getFont().getDefaultFont();
    	final InventoryComponent INVENTORY = this.context.getGameplay().getCurrentPlayer().getComponent(InventoryComponent.class);
    	final EquipmentComponent EQUIPMENT = this.context.getGameplay().getCurrentPlayer().getComponent(EquipmentComponent.class);
    	final PlayerType playerType = this.context.getGameplay().getCurrentPlayer().getComponent(PlayerComponent.class).type;
    	
    	final InterfaceLobby lobby = new InterfaceLobby(this.batch, TEXTURE, FONT, INVENTORY);
    	
        final DialogueBox dialogue = new DialogueBox(this.batch, TEXTURE.get(UIAsset.DIALOGUE_BOX), FONT, this.gameplayBus);
        
        final InventoryPopup inventory = new InventoryPopup(this.gameplayBus, TEXTURE, FONT, INVENTORY, this.batch);
   
        final ShopPopup shop = new ShopPopup(this.gameplayBus, TEXTURE, FONT, this.shopInitializer(playerType), EQUIPMENT, playerType, this.batch);

        final EquipmentPopup equipment = new EquipmentPopup(this.gameplayBus, TEXTURE, FONT, EQUIPMENT, playerType, this.batch);
        
        final Popup[] popups = {shop, inventory, equipment};
        final HUDComponent[] components = {lobby, dialogue};
        
        return new LobbyHUD(components, popups, this.camera, this.batch, this.gameplayBus);
    }
    
    private ArrayList<Entity> shopInitializer(PlayerType type) {
    	ArrayList<Entity> items = new ArrayList<Entity>();
    	
    	if(type.equals(PlayerType.MASTER_CHIEF)) {
	    	items.add(WeaponFactory.createWeapon(FireArmType.RIFLE_ASALTO));
	    	items.add(WeaponFactory.createWeapon(FireArmType.AMETRALLADORA));
	    	items.add(WeaponFactory.createWeapon(FireArmType.ESCOPETA));
	    	items.add(WeaponFactory.createWeapon(FireArmType.SUBFUSIL));
	    	items.add(WeaponFactory.createWeapon(FireArmType.AMETRALLADORA));
	    	items.add(WeaponFactory.createWeapon(FireArmType.FRANCO));
	    	items.add(WeaponFactory.createWeapon(FireArmType.PISTOLA));
	    	return items;
    	} else {
	    	items.add(WeaponFactory.createWeapon(MeleeWeaponType.ESPADA));
	    	items.add(WeaponFactory.createWeapon(MeleeWeaponType.HACHA)); 		
    	}
    	
    	return items;
    }
}
