package Algorytms;
import other.Proces;

public class FIFO extends AccessToCPUAlgorythms{
    private double _excessTime ;

    public FIFO() {
        this._excessTime = 0;
        super.algName = "FIFO";
    }

    @Override
    void addToQueue(Proces newProces) {
        _procesesQueue.add(newProces);
    }
    @Override
    void CPUManagement() {
        if (_procesesQueue.isEmpty())
            return;
        Proces curProces = _procesesQueue.getFirst();
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
            curProces = getProcesFromQueue(0, _timeStamp - freeTime);
        }

        procesUpdate(curProces);
    }
}
