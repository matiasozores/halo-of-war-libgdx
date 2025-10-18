package com.haloofwar.game.data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.haloofwar.common.enumerators.LevelSceneType;
import com.haloofwar.common.enumerators.PlayerType;

public class SaveGameData implements Serializable {
	private static final long serialVersionUID = 1L;

	public PlayerData playerData[] = new PlayerData[2];
	public Set<LevelSceneType> levelsCompleted = new HashSet<>();

	public PlayerData getPlayerData(final PlayerType TYPE) {
		boolean found = false;
		int i = 0;
		PlayerData data = null;
		
		while(i < this.playerData.length && !found) {
			if(this.playerData[i].type.equals(TYPE)) {
				found = true;
				data = this.playerData[i];
			} else {
				i++;
			}
		}
		
		if(data == null) {
			System.out.println("No se ha encontrado la informacion de un jugador '" + TYPE + "'...");
		}
		return data;
	}
	
	public void saveData(final PlayerData DATA) {
		boolean available = false;
		int i = 0;
		int index = -1;
		
		while(i < this.playerData.length && !available) {
			if(this.playerData[i] == null || this.playerData[i].type.equals(DATA.type)) {
				index = i;
				available = true;
			} else {
				i++;
			}
		}
		
		if(index == -1) {
			System.out.println("No hay espacio para guardar mas información");
			return;
		}
		
		this.playerData[index] = DATA;
	}
}
