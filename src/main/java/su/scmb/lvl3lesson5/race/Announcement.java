package su.scmb.lvl3lesson5.race;

import static su.scmb.lvl3lesson5.race.App.ID;

public class Announcement implements Runnable {
    @Override
    public void run() {
        synchronized(ID) {
            try {
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
                ID.wait();
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
                ID.wait();
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
