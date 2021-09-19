package su.scmb.lvl3lesson5;

public class Car implements Runnable, ICar {
    private final Object monitor = new Object();
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
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
        this.name = "Racer #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            synchronized (monitor) {
                prepareCar(this.name);
                Thread.sleep(1500 + (int)(Math.random() * 800));
                System.out.println(this.name + " ready");
                monitor.wait();
            }
            monitor.notifyAll();

        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
    }
}