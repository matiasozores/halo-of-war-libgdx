package com.haloofwar.factories;

import com.haloofwar.components.Entity;
import com.haloofwar.components.HealthComponent;
import com.haloofwar.components.InventoryComponent;
import com.haloofwar.components.NameComponent;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.enumerators.HeadType;
import com.haloofwar.enumerators.UIAsset;
import com.haloofwar.enumerators.WeaponType;
import com.haloofwar.ui.HUD;
import com.haloofwar.ui.components.DialogueBox;
import com.haloofwar.ui.components.HealthBar;
import com.haloofwar.ui.components.InventoryRenderer;
import com.haloofwar.ui.components.PlayerInfoRenderer;

public final class HUDFactory {

    private final GameContext context;

    public HUDFactory(GameContext context) {
        this.context = context;
    }

    public HUD create() {
        // Obtenemos el jugador
        Entity player = context.getGameplay().getPlayer();
        
        // Componentes del jugador necesarios
        HealthComponent healthComp = player.getComponent(HealthComponent.class);
        NameComponent nameComp = player.getComponent(NameComponent.class);
        InventoryComponent inventoryComp = player.getComponent(InventoryComponent.class);

        // Health bar
        HealthBar health = new HealthBar(
                context.getRender().getShape(),
                context.getStaticCamera(),
                healthComp
        );

        // Info del jugador
        PlayerInfoRenderer info = new PlayerInfoRenderer(
                context.getRender().getBatch(),
                context.getRender().getFont().getSmallFont(),
                context.getRender().getFont().getTitleFont(),
                context.getTexture().get(HeadType.KRATOS),      // Podés parametrizar luego
                context.getTexture().get(WeaponType.RIFLE_ASALTO),
                nameComp,
                healthComp
        );

        // Inventario
        InventoryRenderer inventory = new InventoryRenderer(
                context.getRender().getBatch(),
                context.getRender().getFont().getSmallFont(),
                inventoryComp
        );
        
        // Caja de dialogo
        DialogueBox dialogue = new DialogueBox(
        		context.getRender().getBatch(),
        		context.getTexture().get(UIAsset.DIALOGUE_BOX),
        		context.getRender().getFont().getDefaultFont()
        );

        // Construcción final del HUD
        return new HUD(context.getStaticCamera(), context.getRender().getBatch(), health, info, inventory, dialogue, context.getBus());
    }
}
