package other;

import Algorytms.FIFO;
import Algorytms.RR;
import Algorytms.SJFExpro;
import Algorytms.SJFnoExpro;

public class Main {
    public static void main(String[] args) {
        FIFO fifo = new FIFO();
        SJFnoExpro sjFnoExpro = new SJFnoExpro();
        SJFExpro sjfExpro = new SJFExpro();
        RR rr = new RR();
        ProcesMaker procesMaker = ProcesMaker.getInstance();

        Thread thread1 = new Thread(fifo);
        Thread thread2 = new Thread(sjFnoExpro);
        Thread thread3 = new Thread(sjfExpro);
        Thread thread4 = new Thread(rr);
        Thread maker = new Thread(procesMaker);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        maker.start();
    }
}
