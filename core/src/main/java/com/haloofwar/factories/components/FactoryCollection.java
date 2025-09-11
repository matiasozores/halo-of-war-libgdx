package com.haloofwar.factories.components;

import com.haloofwar.dependences.GameContext;
import com.haloofwar.factories.CutSceneFactory;
import com.haloofwar.factories.EnemyFactory;
import com.haloofwar.factories.HUDFactory;
import com.haloofwar.factories.NPCFactory;
import com.haloofwar.factories.PlayerFactory;
import com.haloofwar.factories.PromptFactory;
import com.haloofwar.factories.SceneFactory;

public class FactoryCollection {
	private final HUDFactory HUD_FACTORY;
	private final PlayerFactory PLAYER_FACTORY;
	private final SceneFactory SCENE_FACTORY;
	private final PromptFactory PROMPT_FACTORY;
	private final NPCFactory NPC_FACTORY;
	private final EnemyFactory ENEMY_FACTORY;
	private final CutSceneFactory CUTSCENE_FACTORY;	
	
	public FactoryCollection(GameContext context) {
		this.HUD_FACTORY = new HUDFactory(context);
		this.PLAYER_FACTORY = new PlayerFactory(context);
		this.SCENE_FACTORY = new SceneFactory(context);
		this.PROMPT_FACTORY = new PromptFactory(context);
		this.NPC_FACTORY = new NPCFactory(context);
		this.ENEMY_FACTORY = new EnemyFactory(context);
		this.CUTSCENE_FACTORY = new CutSceneFactory(context);
	}
	
	public HUDFactory getHUD_FACTORY() {
		return this.HUD_FACTORY;
	}
	
	public PlayerFactory getPLAYER_FACTORY() {
		return this.PLAYER_FACTORY;
	}
	
	public SceneFactory getSCENE_FACTORY() {
		return this.SCENE_FACTORY;
	}
	
	public PromptFactory getPROMPT_FACTORY() {
		return this.PROMPT_FACTORY;
	}
	
	public NPCFactory getNPC_FACTORY() {
		return this.NPC_FACTORY;
	}
	
	public EnemyFactory getENEMY_FACTORY() {
		return this.ENEMY_FACTORY;
	}
	
	public CutSceneFactory getCUTSCENE_FACTORY() {
		return this.CUTSCENE_FACTORY;
	}
}
