package com.haloofwar.interfaces;

import com.haloofwar.engine.entity.AnimatedEntityDescriptor;

public interface Talkable extends AnimatedEntityDescriptor{
	String[] getLines();
}
