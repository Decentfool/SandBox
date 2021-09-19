package su.scmb.lvl3lesson5;

import java.util.ArrayList;
import java.util.Arrays;

public class App {
    public static final int CARS_COUNT = 4;
    public static void main(String[] args) {
        /* initial Race*/
        Race race = new Race(new Road(60), new Tunnel(80), new Road(40));
        race.announceAboutStart();
        /* initial cars*/
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}