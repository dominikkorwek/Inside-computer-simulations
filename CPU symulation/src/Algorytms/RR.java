package Algorytms;
import other.Proces;

public class RR extends AccessToCPUAlgorythms {
    private int _procesIndex ;
    private double _excessTime;

    public RR() {
        _procesIndex = -1;
        _excessTime = 0;
        super.algName = "RR";

    }

    @Override
    void addToQueue(Proces newProces) {
        _procesesQueue.add(newProces);
    }
    @Override
    void CPUManagement() {
        if (_procesesQueue.isEmpty())
            return;
        _procesIndex = (_procesIndex + 1)% _procesesQueue.size();
        Proces curProces = _procesesQueue.get(_procesIndex);

        _excessTime +=curProces.getShutDownProcesTime();
        double freeTime = _timeStamp - _excessTime;
        _excessTime = 0;

        while(freeTime > 0){
            if (curProces.getProcesLength()>freeTime){
                curProces.handleProces(freeTime);
                break;
            }
            freeTime-= curProces.getProcesLength();
            procesDone(curProces);
            freeTime-=curProces.getShutDownProcesTime();

            if (_procesesQueue.isEmpty())
                break;
            if (freeTime<0) {
                _excessTime = freeTime * -1;
                break;
            }
            curProces = getProcesFromQueue(_procesIndex % _procesesQueue.size(), _timeStamp - freeTime);
        }

        procesUpdate(curProces);
    }
}
