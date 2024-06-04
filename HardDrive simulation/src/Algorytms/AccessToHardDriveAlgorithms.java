

package Algorytms;

import java.util.*;

import other.Proces;
import other.ProcesMaker;
import other.RealTimeProces;
import other.parameters;

@SuppressWarnings("rawtypes")
public abstract class AccessToHardDriveAlgorithms {
    protected final int _maxProcesNumber;
    protected final int _capacity;
    protected final int _stamp;
    protected LinkedList[] _sectionsOfProceses;
    protected List<RealTimeProces> _realTimeProceses;
    protected List<Proces> _endedProcesses;
    protected List<Proces> _procesList;
    private final ProcesMaker _procesMaker;
    protected int _curTime;
    protected int _pinIndex;
    protected int _numberOfProceses;
    private final String _mode;
    private final Runnable _functionMode;
    protected String _name;

    public AccessToHardDriveAlgorithms(boolean edf) {
        _capacity = parameters.CAPACITY.getValue();
        _sectionsOfProceses = new LinkedList[_capacity + 1];
        for (int i = 0;i<_capacity + 1;i++)
            _sectionsOfProceses[i] = new LinkedList<>();

        _endedProcesses = new ArrayList<>();
        _realTimeProceses = new ArrayList<>();
        _procesList = new LinkedList<>();
        _procesMaker = ProcesMaker.getInstance();
        _numberOfProceses = 0;
        _curTime = 0;
        _pinIndex = parameters.PIN_START_INDEX.getValue();
        _functionMode = edf ? this::EDF : this::FDSCAN;
        _mode = edf ? "EDF" : "FDSCAN";
        _maxProcesNumber = parameters.NUMBER_OF_PROCESSES.getValue();
        _stamp = parameters.TIME_STAMP.getValue();
    }

    public void run() {
            getProcesFromQueue(_curTime);
            HardDriveManagement();
            updateRealTime();
    }


    public void getProcesFromQueue(int _curTime) {
        if (_numberOfProceses < _procesMaker.procesList.size()) {
            try {
                Proces newProces = (Proces)(_procesMaker.procesList.get(_numberOfProceses++)).clone();
                newProces._birthTime = _curTime;
                addProces(newProces);
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException();
            }
        }
    }

    private void HardDriveManagement() {
        if (!_realTimeProceses.isEmpty())
            _functionMode.run();
        else
            HardDriveAlgoritm();
    }


    protected abstract void HardDriveAlgoritm();

    protected void procesDone(Proces proces) {
        proces.endProces(_curTime);

        try {
            _endedProcesses.add((Proces)proces.clone());
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException();
        }

        _procesList.remove(proces);
        if (proces instanceof RealTimeProces)
            _realTimeProceses.remove(proces);

        _sectionsOfProceses[proces.space].remove(proces);
    }

    private void updateRealTime() {
        List<RealTimeProces> toDelete = new ArrayList<>();

        for (RealTimeProces proces : _realTimeProceses) {
            boolean stillWaiting = proces.update(_stamp);

            if (!stillWaiting)
                toDelete.add(proces);
        }

        for (RealTimeProces proces : toDelete) {
            proces.starving = true;
            procesDone(proces);
        }

    }

    private void EDF() {
        RealTimeProces proces = _realTimeProceses.getFirst();
        _curTime += _stamp;
        _pinIndex += Math.abs(proces.space - _pinIndex) > Math.abs(proces.space - (_pinIndex + 1)) ? 1 : -1;
        if (_pinIndex == proces.space)
            procesDone(proces);
    }

    @SuppressWarnings("unchecked")
    private void FDSCAN() {
        RealTimeProces proces = _realTimeProceses.getFirst();

        while (Math.abs(_pinIndex - proces.space) >= proces.getDemandTime()) {
            proces.starving = true;
            procesDone(proces);
            if(_realTimeProceses.isEmpty())
                return;
            proces = _realTimeProceses.getFirst();
        }

        _curTime += _stamp;
        _pinIndex += Math.abs(proces.space - _pinIndex) > Math.abs(proces.space - (_pinIndex + 1)) ? 1 : -1;

        if(!_sectionsOfProceses[_pinIndex].isEmpty()){
           LinkedList<Proces> list = new LinkedList<>(_sectionsOfProceses[_pinIndex]);
           for (Proces p : list)
               procesDone(p);
        }
    }

    abstract void addProces(Proces proces);

    public String showInfo(){
        String value = "";
        value += "----------------------\n";
        value += "name: " + _name +"\n";
        value += "end time: " + _curTime + "\n";
        if(parameters.NEW_REALTIME_PROCES_PROPABILITY.getValue() != 0)
            value += "strategy: " + _mode + "\n";

        int starving = 0;
        int minWaitingTime = Integer.MAX_VALUE;
        int maxWaitingTime = 0;
        double averageWaitingTime = 0;

        for (Proces p : _endedProcesses){
            if(p instanceof RealTimeProces){
                if (((RealTimeProces) p).starving) {
                    ++starving;
                    continue;
                }
            }
            if(minWaitingTime > p.getWaitingTime())
                minWaitingTime = p.getWaitingTime();

            if (maxWaitingTime < p.getWaitingTime())
                maxWaitingTime = p.getWaitingTime();

            averageWaitingTime += p.getWaitingTime();
        }
        averageWaitingTime /= (_maxProcesNumber - starving);

        if(parameters.NEW_REALTIME_PROCES_PROPABILITY.getValue() != 0)
            value += "starving Real Time Processes: " + starving + "\n";

        value += "minimum proces waiting time: " + minWaitingTime + "\n";
        value += "maximum proces waiting time: " + maxWaitingTime + "\n";
        value += "average proces waiting time: " + averageWaitingTime + "\n";

        return value;
    }

    public int getEndedProcessesSize() {
        return _endedProcesses.size();
    }
}
