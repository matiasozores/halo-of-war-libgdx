package com.haloofwar.enumerators;

public enum Zone {
    // Nivel 1 
    ZONA_COSTA_PERDIDA("Costa Perdida", "maps/mario.tmx"),
    ZONA_TEMPLE_SUMERGIDO("Templo Sumergido", "maps/temple_sumergido.tmx"),
    ZONA_TRONO_ABISAL("Trono Abisal", "maps/trono_abisal.tmx"),

    // Nivel 2 
    ZONA_ENTRADA_TARTARO("Entrada al Tártaro", "maps/entrada_tartaro.tmx"),
    ZONA_RIO_ESTIGIA("Río Estigia", "maps/rio_estigia.tmx"),
    ZONA_TRONO_HADES("Trono de Hades", "maps/trono_hades.tmx"),

    // Nivel 3 
    ZONA_RUINAS_AMANECER("Ruinas del Amanecer", "maps/ruinas_amanecer.tmx"),
    ZONA_SALA_PRISMA("Zona Prisma", "maps/sala_prisma.tmx"),
    ZONA_RESCATE_OSCURO("Rescate Oscuro", "maps/rescate_oscuro.tmx"),

    // Nivel 4
    ZONA_CIUDAD_ROTA("Ciudad Rota", "maps/ciudad_rota.tmx"),
    ZONA_ESTACION_DIMENSIONAL("Estación Dimensional", "maps/estacion_dimensional.tmx"),
    ZONA_BATALLA_RELLAMPAGO("Batalla del Relámpago", "maps/batalla_rellampago.tmx"),

    // Nivel 5 
    ZONA_ASCENSO_OLIMPO("Ascenso al Olimpo", "maps/ascenso_olimpo.tmx"),
    ZONA_SALON_CONVERGENCIAS("Salón de Convergencias", "maps/salon_convergencias.tmx"),
    ZONA_DIOS_SUPREMO("Dios Supremo", "maps/dios_supremo.tmx"),

    // Extras (si necesitás una zona general)
    ZONA_TUTORIA("Zona de Tutorial", "maps/tutorial.tmx"),
    ZONA_MENU("Menu Principal", "maps/menu_principal.tmx"),
    ZONA_TIENE_SHOP("Zona de Tienda", "maps/tienda.tmx"),
    ZONA_MULTIJUGADOR_LOBBY("Lobby Multijugador", "maps/lobby_multijugador.tmx");
    
	private String name;
    private String path;
    
    private Zone(String name, String path) {
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
