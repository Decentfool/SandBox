package su.scmb.lvl3lesson5.race;

public class App {
    public static final Object ID = new Object();
    public static final int CARS_COUNT = 4;
    public static void main(String[] args) {
        /* initial Announcement*/
        Announcement announcement = new Announcement();
        new Thread(announcement).start();
        /* initial Race*/
        Race race = new Race(new Road(60), new Tunnel(80), new Road(40));
        /* initial Cars*/
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        /* start Thread */
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }

    }
}