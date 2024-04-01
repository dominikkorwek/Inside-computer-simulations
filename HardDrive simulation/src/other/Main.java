//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package other;

import Algorytms.AccessToHardDriveAlgorithms;
import Algorytms.CSCAN;
import Algorytms.FCFS;
import Algorytms.SCAN;
import Algorytms.SSTF;

public class Main {
    public static void main(String[] args) {
        AccessToHardDriveAlgorithms fcfs = new FCFS();
        AccessToHardDriveAlgorithms sstf = new SSTF();
        AccessToHardDriveAlgorithms cscan = new CSCAN();
        AccessToHardDriveAlgorithms scan = new SCAN();
        ProcesMaker maker = ProcesMaker.getInstance();

        Thread t1 = new Thread(fcfs);
        Thread t2 = new Thread(sstf);
        Thread t3 = new Thread(cscan);
        Thread t4 = new Thread(scan);
        Thread m = new Thread(maker);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        m.start();
    }
}
