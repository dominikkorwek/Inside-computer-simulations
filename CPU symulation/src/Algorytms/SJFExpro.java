package Algorytms;
import other.Proces;
public class SJFExpro extends AccessToCPUAlgorythms {
    private double _excessTime ;

    public SJFExpro() {
        _excessTime = 0;
        super.algName = "SJFExpro";
    }

    @Override
    void addToQueue(Proces newProces) {
        if (_procesesQueue.isEmpty()) {
            _procesesQueue.add(newProces);
            return;
        }

        int index = 0;
        for (Proces actProces : _procesesQueue) {
            if (newProces.getProcesLength() <= actProces.getProcesLength()) {
                _procesesQueue.add(index, newProces);
                return;
            }
            index++;
        }

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
