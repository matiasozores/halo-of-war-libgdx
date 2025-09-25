package com.haloofwar.game.factories;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.common.context.GameContext;
import com.haloofwar.common.enums.Background;
import com.haloofwar.common.enums.FireArmType;
import com.haloofwar.common.enums.UIAsset;
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
import com.haloofwar.ui.components.DialogueBox;
import com.haloofwar.ui.components.EquipmentPopup;
import com.haloofwar.ui.components.HealthBar;
import com.haloofwar.ui.components.InterfaceLevel;
import com.haloofwar.ui.components.InterfaceLobby;
import com.haloofwar.ui.components.InventoryPopup;
import com.haloofwar.ui.components.PlayerInfoRenderer;
import com.haloofwar.ui.components.ShopPopup;
import com.haloofwar.ui.hud.HUD;

public final class HUDFactory {

    private final GameContext context;

    public HUDFactory(GameContext context) {
        this.context = context;
    }

    public HUD createLevelHUD(final LevelData DATA) {
        Entity player = context.getGAMEPLAY().getCurrentPlayer();

        HealthComponent healthComp = player.getComponent(HealthComponent.class);
        NameComponent nameComp = player.getComponent(NameComponent.class);
        PlayerComponent playerComp = player.getComponent(PlayerComponent.class);
        final EquipmentComponent EQUIPMENT = player.getComponent(EquipmentComponent.class);
        
        HealthBar health = new HealthBar(
                context.getRENDER().getShape(),
                context.getSTATIC_CAMERA(),
                healthComp
        );

        PlayerInfoRenderer info = new PlayerInfoRenderer(
                context.getRENDER().getBatch(),
                context.getRENDER().getFont().getSmallFont(),
                context.getRENDER().getFont().getTitleFont(),
                context.getTEXTURE(),
                context.getTEXTURE().get(playerComp.type.getHead()),
                context.getTEXTURE().get(EQUIPMENT.getCurrentWeapon()),
                context.getTEXTURE().get(Background.PLACEHOLDER_ICON),
                nameComp,
                healthComp
        );

        DialogueBox dialogue = new DialogueBox(
                context.getRENDER().getBatch(),
                context.getTEXTURE().get(UIAsset.DIALOGUE_BOX),
                context.getRENDER().getFont().getDefaultFont()
        );
        
        InterfaceLevel level = new InterfaceLevel(DATA, context.getRENDER().getBatch(), context.getRENDER().getFont().getDefaultFont());

        return new HUD(
                context.getSTATIC_CAMERA(),
                context.getRENDER().getBatch(),
                health,
                info,
                dialogue,
                null,
                null,
                null,
                null,
                level,
                context.getGAMEPLAY().getBus()
        );
    }

    /**
     * HUD simplificado para el lobby
     */
    public HUD createLobbyHUD() {
    	final SpriteBatch BATCH = this.context.getRENDER().getBatch();
    	final TextureManager TEXTURE = this.context.getTEXTURE();
    	final BitmapFont FONT = this.context.getRENDER().getFont().getDefaultFont();
    	final InventoryComponent INVENTORY = this.context.getGAMEPLAY().getCurrentPlayer().getComponent(InventoryComponent.class);
    	final EquipmentComponent EQUIPMENT = this.context.getGAMEPLAY().getCurrentPlayer().getComponent(EquipmentComponent.class);
    	final GameStaticCamera STATIC_CAMERA = this.context.getSTATIC_CAMERA();
    	final EventBus GAMEPLAY_BUS = this.context.getGAMEPLAY().getBus();
    	
        DialogueBox dialogue = new DialogueBox(BATCH, TEXTURE.get(UIAsset.DIALOGUE_BOX), FONT);
        
        InventoryPopup inventoryPopup = new InventoryPopup(GAMEPLAY_BUS, TEXTURE, FONT, INVENTORY);
        
        ShopPopup shopPopup = new ShopPopup(GAMEPLAY_BUS, TEXTURE, FONT, this.shopInitializer(), EQUIPMENT, INVENTORY);
        
        EquipmentPopup equipmentPopup = new EquipmentPopup(GAMEPLAY_BUS, TEXTURE, FONT, EQUIPMENT);
        
        InterfaceLobby lobby = new InterfaceLobby(BATCH, TEXTURE, FONT, INVENTORY);
        
        
        // Los demás componentes se pasan como null
        return new HUD(
                STATIC_CAMERA,
                BATCH,
                null,
                null,
                dialogue,
                inventoryPopup,
                shopPopup,
                equipmentPopup,
                lobby,
                null,
                context.getGAMEPLAY().getBus()
        );
    }
    
    private ArrayList<Entity> shopInitializer() {
    	ArrayList<Entity> items = new ArrayList<Entity>();
    	items.add(WeaponFactory.createWeapon(FireArmType.RIFLE_ASALTO));
    	items.add(WeaponFactory.createWeapon(FireArmType.AMETRALLADORA));
    	items.add(WeaponFactory.createWeapon(FireArmType.ESCOPETA));
    	items.add(WeaponFactory.createWeapon(FireArmType.SUBFUSIL));
    	items.add(WeaponFactory.createWeapon(FireArmType.AMETRALLADORA));
    	items.add(WeaponFactory.createWeapon(FireArmType.FRANCO));
    	items.add(WeaponFactory.createWeapon(FireArmType.PISTOLA));
    	
    	return items;
    }
}
