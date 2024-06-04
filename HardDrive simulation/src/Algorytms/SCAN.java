

package Algorytms;

import java.util.Comparator;
import java.util.LinkedList;
import other.Proces;
import other.RealTimeProces;

@SuppressWarnings("ALL")
public class SCAN extends AccessToHardDriveAlgorithms {
    private int _whereToGo;
    private int _bounce;
    public SCAN(boolean edf) {
        super(edf);
        _name = "SCAN";
        _whereToGo = 1;
        _bounce = 1;
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

        if(_pinIndex == _capacity || _pinIndex == 1) {
            _whereToGo *= -1;
            _bounce++;
        }

        if(!_sectionsOfProceses[_pinIndex].isEmpty()){
            LinkedList<Proces> list = new LinkedList<>(_sectionsOfProceses[_pinIndex]);
            for (Proces p : list)
                procesDone(p);
        }
    }

    @Override
    public String showInfo(){
        return super.showInfo() + "number of bounces to the begining: " + _bounce + "\n";
    }
}
