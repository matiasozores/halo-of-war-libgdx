package com.haloofwar.ecs.components.gameplay;

import com.haloofwar.ecs.components.Component;

public class NameComponent implements Component{
	public String name;
	
	public NameComponent(String name) {
		this.name = name;
	}

}
