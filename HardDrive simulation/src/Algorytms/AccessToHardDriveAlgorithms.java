//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Algorytms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import other.Proces;
import other.ProcesMaker;
import other.RealTimeProces;
import other.parameters;

public abstract class AccessToHardDriveAlgorithms implements Runnable {
    private final int _maxProcesNumber = parameters.NUMBER_OF_PROCESSES.getValue();
    protected final int _capacity = parameters.CAPACITY.getValue();
    private final int mode = parameters.EDForSCAN.getValue();
    protected final int stamp = parameters.TIME_STAMP.getValue();


    protected Proces[] _procesesQueue;
    protected List<RealTimeProces> _realTimeProceses;
    protected List<Proces> _endedProcesses;
    protected List<Integer> filledSpace;
    protected List<Proces> procesList;
    private final ProcesMaker _procesMaker;
    protected int _curTime;
    protected int _pinIndex;
    protected int _numberOfProceses;
    Runnable functionMode;
    protected String name;

    public AccessToHardDriveAlgorithms() {
        _procesesQueue = new Proces[_capacity + 1];
        _endedProcesses = new ArrayList<>();
        filledSpace = new ArrayList<>();
        _realTimeProceses = new ArrayList<>();
        procesList = new LinkedList<>();
        _procesMaker = ProcesMaker.getInstance();
        _numberOfProceses = 0;
        _curTime = 0;
        _pinIndex = 1;
        functionMode = mode == 0 ? this::EDF : this::FDSCAN;

        for(int i = 1; i <= _capacity; ++i)
            filledSpace.add(i);
    }

    @SuppressWarnings("BusyWait")
    public void run() {
        while(_maxProcesNumber >= _numberOfProceses + 1 || _endedProcesses.size() != _maxProcesNumber) {
            getProcesFromQueue(_curTime);
            HardDriveManagement();
            updateRealTime();

            try {
                Thread.sleep(parameters.LOOP_SLEEP.getValue());
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }

        System.out.println(name + " : " + _curTime);
    }

    public void getProcesFromQueue(int _curTime) {
        if (_numberOfProceses < _procesMaker.procesList.size()) {
            try {
                Proces proces = (Proces)(_procesMaker.procesList.get(_numberOfProceses++)).clone();
                fillSpace(proces);
                proces._birthTime = _curTime;
                addProces(proces);
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException();
            }
        }
    }

    private void fillSpace(Proces newProces) {
        Random random = new Random();

        try {
            int number = random.nextInt(filledSpace.size() - 1);
            newProces.space = filledSpace.get(number);
            filledSpace.remove(number);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("not enough space in HardDrive");
        }
    }

    private void HardDriveManagement() {
        if (!_realTimeProceses.isEmpty())
            functionMode.run();
        else
            HardDriveAlgoritm();
    }

    protected void HardDriveAlgoritm() {
        if (!procesList.isEmpty()) {
            _curTime += stamp;
            Proces proces = procesList.getFirst();

            if (_pinIndex == proces.space)
                procesDone(proces);

            _pinIndex += Math.abs(proces.space - _pinIndex) > Math.abs(proces.space - (_pinIndex + 1)) ? 1 : -1;
        }
    }

    protected void procesDone(Proces proces) {
        proces.endProces(_curTime);

        try {
            _endedProcesses.add((Proces)proces.clone());
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException();
        }

        procesList.remove(proces);
        if (proces instanceof RealTimeProces)
            _realTimeProceses.remove(proces);

        filledSpace.add(proces.space);
        _procesesQueue[proces.space] = null;
    }

    private void updateRealTime() {
        List<RealTimeProces> toDelete = new ArrayList<>();

        for (RealTimeProces proces : _realTimeProceses) {
            boolean stillWaiting = proces.update(stamp);

            if (!stillWaiting)
                toDelete.add(proces);
        }

        for (RealTimeProces proces : toDelete) {
            proces.starving = true;
            procesDone(proces);
        }

    }

    void EDF() {
        RealTimeProces proces = _realTimeProceses.getFirst();
        _curTime += stamp;
        _pinIndex = proces.space;
        procesDone(proces);
    }

    void FDSCAN() {
        RealTimeProces proces = _realTimeProceses.getFirst();

        if (Math.abs(_pinIndex - proces.space) > proces.demandTime) {
            proces.starving = true;
            procesDone(proces);

        } else {
            _curTime += stamp;
            _pinIndex += Math.abs(proces.space - _pinIndex) > Math.abs(proces.space - (_pinIndex + 1)) ? 1 : -1;

            if (_procesesQueue[_pinIndex] != null)
                procesDone(_procesesQueue[_pinIndex]);
        }
    }

    abstract void addProces(Proces proces);
}
