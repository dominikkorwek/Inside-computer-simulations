//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Algorytms;

import other.Proces;
import other.RealTimeProces;

public class FCFS extends AccessToHardDriveAlgorithms {
    public FCFS() {
        name = "FCFS";
    }

    void addProces(Proces proces) {
        _procesesQueue[proces.space] = proces;
        procesList.add(proces);

        if (proces instanceof RealTimeProces)
            _realTimeProceses.add((RealTimeProces)proces);
    }
}
