package Algorytms;

import java.util.*;
import other.*;
public abstract class SiteChanger implements Runnable{
    private final int _maxProcesNumber = parameters.NUMBER_OF_PROCESSES.getValue();
    private final int _capacity = parameters.CAPACITY.getValue();
    private final ProcesMaker _procesMaker = ProcesMaker.getInstance();
    private final int _stamp = parameters.TIME_STAMP.getValue();

    protected Proces[] _frames;
    protected List<Proces> _procesQueue;
    protected  int _error;
    protected int _curTime;
    protected int _numberOfProceses;
    protected String _name;

    public SiteChanger() {
        _numberOfProceses = 0;
        _curTime = 0;
        _error = 0;
        _frames = new Proces[_capacity];
        _procesQueue = new ArrayList<>();
    }
    @SuppressWarnings("BusyWait")
    public void run(){
        while(_maxProcesNumber >= _numberOfProceses + 1 || !_procesQueue.isEmpty() ){
            getProcesFromQueue();
            HandleProces();
            _curTime+=_stamp;

            try {
                Thread.sleep(parameters.LOOP_SLEEP.getValue());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(_name +": " + _error);
    }
    public void getProcesFromQueue(){
        while (_numberOfProceses < _procesMaker.getProcesList().size()){
            try {
            Proces proces = (Proces)_procesMaker.getProcesList().get(_numberOfProceses++).clone();
            addProces(proces);

            } catch (CloneNotSupportedException e) {
               throw new RuntimeException(e);
            }
        }
    }
    private void addProces(Proces proces){
        _procesQueue.add(proces);
    }
    abstract void HandleProces();
}
