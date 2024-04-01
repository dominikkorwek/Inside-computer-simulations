//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Algorytms;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Collectors;
import other.Proces;
import other.RealTimeProces;

public class SSTF extends AccessToHardDriveAlgorithms {
    public SSTF() {
        name = "SSTF";
    }

    void addProces(Proces proces) {
        _procesesQueue[proces.space] = proces;
        procesList.add(proces);

        procesList = procesList.stream()
                .sorted(Comparator.comparingInt((p) -> Math.abs(_pinIndex - p.space)))
                .collect(Collectors.toCollection(LinkedList::new));

        if (proces instanceof RealTimeProces)
            _realTimeProceses.add((RealTimeProces)proces);
    }
}
