package other;

import Algorytms.*;

@SuppressWarnings("ALL")
public class Main {
    public static void main(String[] args) {
        SiteChanger fifo = new FIFO();
        SiteChanger lru = new LRU();
        SiteChanger alru = new ALRU();
        SiteChanger rand = new RAND();
        SiteChanger opt = new OPT();

        sequenceMaker maker = sequenceMaker.getInstance();

        for(int i = 0; i<parameters.DELAY_AFTER_FIRST_FRAME_MADE.getValue(); i++){
            maker.run();
        }

        boolean t = true;
        while (t){
            t = false;
            t |= fifo.run();
            t |= lru.run();
            t |= alru.run();
            t |= rand.run();
            t |= opt.run();
            maker.run();
        }
        //ogarnac tworzenie procesow
        System.out.println(fifo.toString());
        System.out.println(lru.toString());
        System.out.println(alru.toString());
        System.out.println(rand.toString());
        System.out.println(opt.toString());

    }
}
