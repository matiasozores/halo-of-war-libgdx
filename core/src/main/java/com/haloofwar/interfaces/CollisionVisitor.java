package com.haloofwar.interfaces;

import com.haloofwar.entities.Bullet;
import com.haloofwar.entities.Entity;
import com.haloofwar.entities.LivingEntity;
import com.haloofwar.entities.statics.Obstacle;
import com.haloofwar.entities.statics.items.Item;
import com.haloofwar.enumerators.entities.behavior.CollisionType;

public interface CollisionVisitor {
	/*
	 * Se utiliza el patrón Visitor para manejar las colisiones entre diferentes tipos de entidades.
	 * Decidimos generalizar con Entity antes que especificar una clase hija como LivingEntity.
	 * En alguno de los casos entity necesitaria un cast a algo en especifico. Es un equilibrio entre
	 * tener 200 metodos distintos y que la mayoria no se utilicen o tener un metodo general pero 
	 * tener que hacer un cast. A futuro se puede mejorar si se ve necesario.
	 * 
	 * */
	
	void visit(Bullet bullet, Collidable entity);
	void visit(LivingEntity living, Collidable entity);
	void visit(Obstacle obstacle, Collidable entity);
	void visit(Item item, Collidable entity); 
	void visit(Entity entity, Collidable otherEntity); // General method for other entity types
	CollisionType getCollisionType(); 
}
