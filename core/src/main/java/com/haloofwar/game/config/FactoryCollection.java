package com.haloofwar.game.config;

import com.haloofwar.common.context.GameContext;
import com.haloofwar.game.factories.CutSceneFactory;
import com.haloofwar.game.factories.EnemyFactory;
import com.haloofwar.game.factories.HUDFactory;
import com.haloofwar.game.factories.NPCFactory;
import com.haloofwar.game.factories.PlayerFactory;
import com.haloofwar.game.factories.PromptFactory;
import com.haloofwar.game.factories.SceneFactory;

public class FactoryCollection {
	private final HUDFactory HUD_FACTORY;
	private final PlayerFactory PLAYER_FACTORY;
	private final SceneFactory SCENE_FACTORY;
	private final PromptFactory PROMPT_FACTORY;
	private final NPCFactory NPC_FACTORY;
	private final EnemyFactory ENEMY_FACTORY;
	private final CutSceneFactory CUTSCENE_FACTORY;	
	
	public FactoryCollection(final GameContext CONTEXT) {
		this.HUD_FACTORY = new HUDFactory(CONTEXT);
		this.ENEMY_FACTORY = new EnemyFactory(CONTEXT);
		this.PLAYER_FACTORY = new PlayerFactory(CONTEXT);
		this.CUTSCENE_FACTORY = new CutSceneFactory(CONTEXT);
		this.NPC_FACTORY = new NPCFactory(CONTEXT);
		this.SCENE_FACTORY = new SceneFactory(CONTEXT, this.HUD_FACTORY, this.ENEMY_FACTORY, this.NPC_FACTORY, this.CUTSCENE_FACTORY);
		this.PROMPT_FACTORY = new PromptFactory(CONTEXT);
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
