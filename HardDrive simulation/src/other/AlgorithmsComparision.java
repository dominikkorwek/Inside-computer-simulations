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

public class AlgorithmsComparision {
    public static void main(String[] args) {
        AccessToHardDriveAlgorithms fcfs = new FCFS(true);
        AccessToHardDriveAlgorithms sstf = new SSTF(true);

        AccessToHardDriveAlgorithms cscan = new CSCAN(true);
        AccessToHardDriveAlgorithms scan = new SCAN(true);
        ProcesMaker maker = ProcesMaker.getInstance();

        boolean allended = false;
        while (!allended){
            allended = true;
            maker.makeProces();
            if(fcfs.getEndedProcessesSize() != parameters.NUMBER_OF_PROCESSES.getValue()) {
                fcfs.run();
                allended = false;
            }
            if(sstf.getEndedProcessesSize() != parameters.NUMBER_OF_PROCESSES.getValue()) {
                sstf.run();
                allended = false;
            }
            if(cscan.getEndedProcessesSize() != parameters.NUMBER_OF_PROCESSES.getValue()) {
                cscan.run();
                allended = false;

            }if(scan.getEndedProcessesSize() != parameters.NUMBER_OF_PROCESSES.getValue()) {
                scan.run();
                allended = false;
            }
        }
        System.out.println(fcfs.showInfo());
        System.out.println(sstf.showInfo());
        System.out.println(cscan.showInfo());
        System.out.println(scan.showInfo());

    }
}
