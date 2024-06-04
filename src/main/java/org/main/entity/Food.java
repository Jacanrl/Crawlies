package org.main.entity;

import org.main.SimulationPanel;

public class Food extends Entity {
    private int hpToAdd;

    public Food(int x, int y, SimulationPanel sp, int hpToAdd) {
        super(x, y, sp);
        this.hpToAdd = hpToAdd;
    }

    @Override
    public void update() {

        for (Entity entity : sp.entities) {
            if (entity instanceof Beetle) {
                if (entity.x == x && entity.y == y) {
                    ((Beetle) entity).life = ((Beetle) entity).startingLife;
                    sp.remove(this);
                    return;
                }
            }
        }
    }
}
