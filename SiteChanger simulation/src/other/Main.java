package other;

import Algorytms.*;

public class Main {
    @SuppressWarnings("CallToPrintStackTrace")
    public static void main(String[] args) {
        SiteChanger fifo = new FIFO();
        SiteChanger lru = new LRU();
        SiteChanger alru = new ALRU();
        SiteChanger rand = new RAND();
        SiteChanger opt = new OPT();

        ProcesMaker maker = ProcesMaker.getInstance();

        Thread f1 = new Thread(fifo);
        Thread l1 = new Thread(lru);
        Thread a1 = new Thread(alru);
        Thread r1 = new Thread(rand);
        Thread o1 = new Thread(opt);
        Thread m = new Thread(maker);

        m.start();
        try {
            Thread.sleep(parameters.DELAY_AFTER_FIRST_PROCES_MADE.getValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        f1.start();
        l1.start();
        a1.start();
        r1.start();
        o1.start();
    }
}
