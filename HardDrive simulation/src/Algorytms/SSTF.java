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

@SuppressWarnings("ALL")
public class SSTF extends AccessToHardDriveAlgorithms {
    private boolean _haveGoal;
    private Proces _proces;

    public SSTF(boolean edf) {
        super(edf);
        _name = "SSTF";
        _haveGoal = false;
    }

    @Override
    @SuppressWarnings("unchecked")
    void addProces(Proces proces) {
        _sectionsOfProceses[proces.space].add(proces);

        _procesList.add(proces);

        _procesList = _procesList.stream()
                .sorted(Comparator.comparingInt((p) -> Math.abs(_pinIndex - p.space)))
                .collect(Collectors.toCollection(LinkedList::new));

        if (proces instanceof RealTimeProces) {
            _realTimeProceses.add((RealTimeProces) proces);
            _realTimeProceses.sort(Comparator.comparingInt(RealTimeProces::getDemandTime));
        }
    }

    @Override
    protected void HardDriveAlgoritm() {
        if (!_procesList.isEmpty()) {

            if(!_haveGoal){
                _proces = _procesList.getFirst();
                _haveGoal = true;
            }
            _curTime += _stamp;

            if (_pinIndex != _proces.space)
                _pinIndex += Math.abs(_proces.space - _pinIndex) > Math.abs(_proces.space - (_pinIndex + 1)) ? 1 : -1;

            if (_pinIndex == _proces.space) {
                _haveGoal = false;
                LinkedList<Proces> list = new LinkedList<>(_sectionsOfProceses[_pinIndex]);
                for (Proces p : list)
                    procesDone(p);
            }
        }
    }
}
