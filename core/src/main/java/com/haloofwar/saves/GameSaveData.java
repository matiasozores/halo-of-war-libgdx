package com.haloofwar.saves;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import com.haloofwar.enumerators.LevelType;

public class GameSaveData implements Serializable {
    private static final long serialVersionUID = 1L;

    public PlayerData playerData;
    public Set<LevelType> completedLevels = new HashSet<>();
}
