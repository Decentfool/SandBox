package su.scmb.lvl3lesson4;

public class Printable {
    private volatile char currentLetter;
    private final static Object monitor = new Object();

    public char getCurrentLetter() {
        return currentLetter;
    }

    public void printLetter(char currentLetter, char nextLetter, int countPrint) {
        synchronized (monitor) {
            for (int i = 0; i < countPrint; i++) {
                while (this.currentLetter != currentLetter) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                System.out.print(this.currentLetter);
                this.currentLetter = nextLetter;
                monitor.notifyAll();
            }
        }
    }

    public void setCurrentLetter(char currentLetter) {
        this.currentLetter = currentLetter;
    }
}
