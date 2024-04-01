//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Algorytms;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import other.Proces;
import other.RealTimeProces;

public class CSCAN extends AccessToHardDriveAlgorithms {
    public CSCAN() {
        name = "CSCAN";
    }

    void addProces(Proces proces) {
        _procesesQueue[proces.space] = proces;
        procesList.add(proces);

        List<Proces> greaterThanA = procesList.stream()
                .filter((p) -> p.space >= _pinIndex)
                .sorted()
                .toList();

        List<Proces> lessThanA = procesList.stream()
                .filter((p) -> p.space < _pinIndex)
                .sorted()
                .toList();

        procesList = Stream.concat(greaterThanA.stream(), lessThanA.stream())
                .collect(Collectors.toList());

        if (proces instanceof RealTimeProces)
            _realTimeProceses.add((RealTimeProces)proces);
    }

    protected void HardDriveAlgoritm() {
        if (!procesList.isEmpty()) {
            _curTime += stamp;
            Proces proces = procesList.getFirst();

            if (_pinIndex == proces.space)
                procesDone(proces);

            ++_pinIndex;
            if (_pinIndex == _capacity + 1)
                _pinIndex = 1;

        }
    }
}
