package com.haloofwar.common.enums;

import com.haloofwar.engine.entity.EntityDescriptor;

public enum Background implements EntityDescriptor{
	MAIN_MENU("Fondo del menu principal", "images/background/main_menu.png"),
	PORTAL_MENU("Fondo portal", "images/background/main_menu2.png"),
	PLAYER_SELECTION("Fondo de seleccion", "images/background/player_selection.png"),
	GAME_OVER("Fondo de game over", "images/background/game_over.png"),
	
	INVENTORY("Fondo del inventario", "images/background/fullinventory.png"),
	EQUIPMENT("Fondo del equipamiento", "images/background/fullequipment.png"),
	SHOP("Fonde de la tienda", "images/background/fullshop.png"),
	PIXEL("Pixel 1x1 ", "images/background/pixel.png"),
	SHOP_ICON("Icono de Tienda", "images/background/shop_icon.png"),
	INVENTORY_ICON("Icono de Inventario", "images/background/inventory_icon.png"),
	EQUIPMENT_ICON("Icono de Equipamiento", "images/background/equipment_icon.png"),
	PLACEHOLDER_ICON("Icono de Placeholder", "images/background/placeholder_icons.png"),
	BUY("Boton de comprar", "images/background/buy.png"),
	SELECT("Boton de seleccionar", "images/background/select.png");
	private String name;
	private String path;
	
	private Background(String name, String path) {
		this.name = name;
		this.path = path;
	}
	
	@Override
	public String getPath() {
		return this.path;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	
}
