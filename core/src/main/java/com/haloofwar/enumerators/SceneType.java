package com.haloofwar.enumerators;

public enum SceneType {
    // Nivel 1 
    COSTA_PERDIDA("Costa Perdida", "maps/mario.tmx"),
    TEMPLE_SUMERGIDO("Templo Sumergido", "maps/temple_sumergido.tmx"),
    TRONO_ABISAL("Trono Abisal", "maps/trono_abisal.tmx"),

    // Nivel 2 
    ENTRADA_TARTARO("Entrada al Tártaro", "maps/entrada_tartaro.tmx"),
    RIO_ESTIGIA("Río Estigia", "maps/rio_estigia.tmx"),
    TRONO_HADES("Trono de Hades", "maps/trono_hades.tmx"),

    // Nivel 3 
    RUINAS_AMANECER("Ruinas del Amanecer", "maps/ruinas_amanecer.tmx"),
    SALA_PRISMA("Zona Prisma", "maps/sala_prisma.tmx"),
    RESCATE_OSCURO("Rescate Oscuro", "maps/rescate_oscuro.tmx"),

    // Nivel 4
    CIUDAD_ROTA("Ciudad Rota", "maps/ciudad_rota.tmx"),
    ESTACION_DIMENSIONAL("Estación Dimensional", "maps/estacion_dimensional.tmx"),
    BATALLA_RELLAMPAGO("Batalla del Relámpago", "maps/batalla_rellampago.tmx"),

    // Nivel 5 
    ASCENSO_OLIMPO("Ascenso al Olimpo", "maps/ascenso_olimpo.tmx"),
    SALON_CONVERGENCIAS("Salón de Convergencias", "maps/salon_convergencias.tmx"),
    DIOS_SUPREMO("Dios Supremo", "maps/dios_supremo.tmx"),

    // Extras (si necesitás una zona general)
    ZONA_TUTORIAL("Zona de Tutorial", "maps/mario.tmx"),
    ZONA_MENU("Menu Principal", "maps/menu_principal.tmx"),
    ZONA_TIENE_SHOP("Zona de Tienda", "maps/tienda.tmx"),
    ZONA_MULTIJUGADOR_LOBBY("Lobby Multijugador", "maps/lobby_multijugador.tmx");
    
	private String name;
    private String path;
    
    private SceneType(String name, String path) {
		this.name = name;
		this.path = path;
	}

	public String getName() {
		return this.name;
	}

	public String getPath() {
		return this.path;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
