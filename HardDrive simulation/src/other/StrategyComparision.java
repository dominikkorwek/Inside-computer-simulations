package other;

import Algorytms.AccessToHardDriveAlgorithms;
import Algorytms.SSTF;

public class StrategyComparision {
    public static void main(String[] args) {
        AccessToHardDriveAlgorithms sstfedf = new SSTF(true);
        AccessToHardDriveAlgorithms sstffd = new SSTF(false);

        ProcesMaker maker = ProcesMaker.getInstance();

        boolean allended = false;
        while (!allended){
            allended = true;
            maker.makeProces();
            if(sstfedf.getEndedProcessesSize() != parameters.NUMBER_OF_PROCESSES.getValue()) {
                sstfedf.run();
                allended = false;
            }
            if(sstffd.getEndedProcessesSize() != parameters.NUMBER_OF_PROCESSES.getValue()) {
                sstffd.run();
                allended = false;
            }
        }

        System.out.println(sstfedf.showInfo());
        System.out.println(sstffd.showInfo());
    }
}
