//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Algorytms;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import other.Proces;
import other.RealTimeProces;

public class SCAN extends AccessToHardDriveAlgorithms {
    public SCAN() {
        name = "SCAN";
    }

    @SuppressWarnings("unchecked")
    void addProces(Proces proces) {
        _procesesQueue[proces.space] = proces;
        procesList.add(proces);

        List<Proces> greaterThanA = procesList.stream()
                .filter((p) -> p.space >= _pinIndex)
                .sorted()
                .toList();

        List<Proces> lessThanA = procesList.stream()
                .filter((p) -> p.space < _pinIndex)
                .sorted(Comparator.reverseOrder())
                .toList();

        procesList = Stream.concat(greaterThanA.stream(), lessThanA.stream())
                .collect(Collectors.toList());

        if (proces instanceof RealTimeProces)
            _realTimeProceses.add((RealTimeProces)proces);
    }
}
