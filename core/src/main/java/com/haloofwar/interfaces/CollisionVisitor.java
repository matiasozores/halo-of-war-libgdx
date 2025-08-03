package com.haloofwar.interfaces;

import com.haloofwar.entities.Bullet;
import com.haloofwar.entities.Entity;
import com.haloofwar.entities.LivingEntity;
import com.haloofwar.entities.statics.Item;
import com.haloofwar.entities.statics.Obstacle;

public interface CollisionVisitor {
	/*
	 * Se utiliza el patrón Visitor para manejar las colisiones entre diferentes tipos de entidades.
	 * Decidimos generalizar con Entity antes que especificar una clase hija como LivingEntity.
	 * En alguno de los casos entity necesitaria un cast a algo en especifico. Es un equilibrio entre
	 * tener 200 metodos distintos y que la mayoria no se utilicen o tener un metodo general pero 
	 * tener que hacer un cast. A futuro se puede mejorar si se ve necesario.
	 * 
	 * */
	
	void visit(Bullet bullet, Entity entity);
	void visit(LivingEntity living, Entity entity);
	void visit(Obstacle obstacle, Entity entity);
	void visit(Item item, Entity entity); 
	void visit(Entity entity, Entity otherEntity); // General method for other entity types
}
