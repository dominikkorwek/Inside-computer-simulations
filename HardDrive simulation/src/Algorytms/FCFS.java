//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Algorytms;

import other.Proces;
import other.RealTimeProces;

import java.util.Comparator;

@SuppressWarnings("ALL")
public class FCFS extends AccessToHardDriveAlgorithms {
    public FCFS(boolean edf) {
        super(edf);
        _name = "FCFS";
    }

    @Override
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
        if (!_procesList.isEmpty()) {
            Proces proces = _procesList.getFirst();

            if(_pinIndex != proces.space ){
                if ( _maxProcesNumber >= _numberOfProceses + 1){
                    _pinIndex += Math.abs(proces.space - _pinIndex) > Math.abs(proces.space - (_pinIndex + 1)) ? 1 : -1;
                    _curTime += _stamp;
                }
                else {
                    int length = Math.abs(proces.space - _pinIndex);
                    _pinIndex = proces.space;
                    _curTime += _stamp * length;
                }
            }

            if (_pinIndex == proces.space)
                procesDone(proces);
        }
    }

}
