package com.haloofwar.factories;

import com.haloofwar.dependences.GameContext;
import com.haloofwar.ecs.components.gameplay.HealthComponent;
import com.haloofwar.ecs.components.gameplay.InventoryComponent;
import com.haloofwar.ecs.components.gameplay.NameComponent;
import com.haloofwar.enumerators.animation.HeadType;
import com.haloofwar.enumerators.entities.WeaponType;
import com.haloofwar.ui.hud.HUD;
import com.haloofwar.ui.hud.components.HealthBar;
import com.haloofwar.ui.hud.components.InventoryRenderer;
import com.haloofwar.ui.hud.components.PlayerInfoRenderer;

public class HUDFactory {

    private final GameContext context;

    public HUDFactory(GameContext context) {
        this.context = context;
    }

    public HUD create() {
        // Obtenemos el jugador
        var player = context.getGameplay().getPlayer();
        
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

        // Inventory
        InventoryRenderer inventory = new InventoryRenderer(
                context.getRender().getBatch(),
                context.getRender().getFont().getSmallFont(),
                inventoryComp
        );

        // Construcción final del HUD
        return new HUD(context.getStaticCamera(), context.getRender().getBatch(), health, info, inventory);
    }
}
