package su.scmb.lvl3lesson5.race;

import java.util.concurrent.atomic.AtomicBoolean;

import static su.scmb.lvl3lesson5.race.App.ID;

public class Car implements Runnable {
    private final static AtomicBoolean haveWinner = new AtomicBoolean(false);
    private static int CARS_COUNT;

    private static volatile int CURRENT_CAR;

    static {
        CARS_COUNT = 0;
        CURRENT_CAR = 0;
    }

    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            synchronized (ID) {
                while (CARS_COUNT > CURRENT_CAR) {
                    CURRENT_CAR++;
                    Thread.sleep(1500 + (int) (Math.random() * 800));
                    System.out.println(this.name + " готов");
                    if(CURRENT_CAR == CARS_COUNT) ID.notifyAll(); else ID.wait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        if (!haveWinner.get()) {
            haveWinner.set(true);
            System.out.println(this.name + " - Финишировал первым!!! У нас есть победитель!!!");
        }
        CURRENT_CAR--;
        if (CURRENT_CAR == 0) {
            synchronized (ID) {
                ID.notify();
            }
        }

    }
}