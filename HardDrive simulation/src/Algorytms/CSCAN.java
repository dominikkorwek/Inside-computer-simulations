//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Algorytms;

import java.util.Comparator;
import java.util.LinkedList;

import other.Proces;
import other.RealTimeProces;

@SuppressWarnings("ALL")
public class CSCAN extends AccessToHardDriveAlgorithms {
    private final int _whereToGo;
    private int _goToTheBegining;

    public CSCAN(boolean edf) {
        super(edf);
        _name = "CSCAN";
        _whereToGo = 1;
        _goToTheBegining = 0;
    }
    @Override
    @SuppressWarnings("unchecked")
    void addProces(Proces proces) {
        _sectionsOfProceses[proces.space].add(proces);
        _procesList.add(proces);

        if (proces instanceof RealTimeProces) {
            _realTimeProceses.add((RealTimeProces) proces);
            _realTimeProceses.sort(Comparator.comparingInt(RealTimeProces::getDemandTime));
        }
    }

    @Override
    protected void HardDriveAlgoritm() {
        _curTime += _stamp;
        _pinIndex += _whereToGo;

        if (_pinIndex == _capacity + 1) {
            _pinIndex = 1;
            _curTime += (int) Math.log(_capacity);
            _goToTheBegining++;
        }

        if(!_sectionsOfProceses[_pinIndex].isEmpty()){
            LinkedList<Proces> list = new LinkedList<>(_sectionsOfProceses[_pinIndex]);
            for (Proces p : list)
                procesDone(p);
        }
    }
    @Override
    public String showInfo(){
        return super.showInfo() + "number of returns to the begining (included in time): " + _goToTheBegining + "\n";
    }
}
