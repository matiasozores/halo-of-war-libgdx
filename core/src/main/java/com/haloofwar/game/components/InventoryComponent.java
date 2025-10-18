package com.haloofwar.game.components;

import java.util.ArrayList;

import com.haloofwar.common.enumerators.ObjectType;
import com.haloofwar.engine.entity.Entity;

public class InventoryComponent implements Component {
    private ArrayList<Entity> objects = new ArrayList<Entity>();

    public void add(Entity entity, int amount) {
        if (!entity.hasComponent(NameComponent.class) || !entity.hasComponent(StockComponent.class)) {
            return;
        }
        
        if(amount < 0) {
        	amount = - amount;
        }

        final String NAME_NEW_ENTITY = entity.getComponent(NameComponent.class).name;

        int i = 0;
        boolean found = false;
        int index = -1;

        while (i < this.objects.size() && !found) {
            final String NAME = this.objects.get(i).getComponent(NameComponent.class).name;

            if (NAME.equals(NAME_NEW_ENTITY)) {
                found = true;
                index = i;
            } else {
                i++;
            }
        }

        if (found) {
            this.objects.get(index).getComponent(StockComponent.class).affectStock(amount);
        } else {
            this.objects.add(entity);
            this.objects.get(this.objects.size() - 1).getComponent(StockComponent.class).setStock(amount);
        }
    }

    public void remove(Entity entity) {
        int i = 0;
        boolean found = false;
        int index = -1;

        final String NAME_REMOVE_ENTITY = entity.getComponent(NameComponent.class).name;

        while (i < this.objects.size() && !found) {
            final String NAME = this.objects.get(i).getComponent(NameComponent.class).name;

            if (NAME.equals(NAME_REMOVE_ENTITY)) {
                found = true;
                index = i;
            } else {
                i++;
            }
        }

        if (found) {
            StockComponent component = this.objects.get(index).getComponent(StockComponent.class);

            if (component.getStock() > 1) {
                component.affectStock(-1);
            } else {
                this.objects.remove(index);
            }
        }
    }

    /**
     * Remueve cierta cantidad de items de un tipo (ej: MONEDA_DE_ORO).
     * Si existen múltiples stacks del mismo type, consume de todos hasta completar amount.
     * @param type tipo a remover
     * @param amount cantidad a remover
     * @return true si se removió la cantidad solicitada, false si no había suficientes.
     */
    public boolean remove(ObjectType type, int amount) {
        if(amount < 0) {
        	amount = - amount;
        }

        int remaining = amount;

        // Iteramos con índice porque posiblemente removamos elementos de la lista
        for (int i = 0; i < this.objects.size() && remaining > 0; i++) {
            Entity e = this.objects.get(i);
            StockComponent stock = e.getComponent(StockComponent.class);
            if (stock == null) continue;

            if (stock.getType() == type) {
                int have = stock.getStock();
                if (have <= 0) {
                    // si por alguna razón hay 0, limpiamos la entrada
                    this.objects.remove(i);
                    i--; // ajustar índice
                    continue;
                }

                if (have >= remaining) {
                    // podemos cubrir todo lo que falta
                    stock.affectStock(-remaining);
                    if (stock.getStock() <= 0) {
                        this.objects.remove(i);
                        i--;
                    }
                    remaining = 0;
                    break;
                } else {
                    // consumimos todo este stack y seguimos buscando
                    stock.affectStock(-have);
                    this.objects.remove(i);
                    i--;
                    remaining -= have;
                }
            }
        }

        return remaining == 0;
    }

    public int getItemsCount() {
        int count = 0;

        for (Entity entity : this.objects) {
            count += entity.getComponent(StockComponent.class).getStock();
        }

        return count;
    }

    public int getItemCount(ObjectType type) {
        int count = 0;

        for (Entity entity : this.objects) {
            StockComponent stock = entity.getComponent(StockComponent.class);

            if (stock.getType().equals(type)) {
                count += stock.getStock();
            }
        }

        return count;
    }

    public ArrayList<Entity> getObjects() {
        return this.objects;
    }
    
    public void setObjects(ArrayList<Entity> entities) {
        this.objects.clear();
        this.objects.addAll(entities);
    }
}
