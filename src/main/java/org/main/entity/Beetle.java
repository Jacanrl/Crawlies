package org.main.entity;

import org.main.SimulationPanel;

import java.util.Random;

public class Beetle extends Crawlie {
    public static int startingLife = 3;
    public static int delay = 15;
    public int delayLeft = delay;

    public Beetle(int x, int y, SimulationPanel sp) {
        super(x, y, sp);
    }

    @Override
    public void update() {
        Random random = new Random();
        lock.lock();


        Entity foodOnPath = sp.entities.stream()
                .filter(e -> e.getClass() == Food.class)
                .filter(e -> e.x == x)
                .filter(e -> e.y == y)
                .findFirst().orElse(null);


        if (foodOnPath != null) {
            try {
                sp.remove(foodOnPath);
            } finally {
                lock.unlock();
            }
        }

        if ((xDest < 0 || yDest < 0)) {

            do {
                xDest = x + 20 * (random.nextInt(3) - 1);
                yDest = y + 20 * (random.nextInt(3) - 1);
            } while (!sp.breaches(xDest, yDest));
        } else if (x == xDest && y == yDest) {

            xDest = -1; yDest = -1;
        } else {
            // Move towards destination
            if (delayLeft > 0) { delayLeft--; }
            else {
                if (x < xDest) { x++; }
                else if (x > xDest) { x--; }
                if (y < yDest) { y++; }
                else if (y > yDest) { y--; }
                delayLeft = delay;
            }
        }
    }
}
