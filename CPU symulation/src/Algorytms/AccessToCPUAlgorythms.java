package Algorytms;

import java.util.ArrayList;
import java.util.Iterator;

import other.ProcesMaker;
import other.parameters;
import other.Proces;


public abstract class AccessToCPUAlgorythms implements Runnable{
    private final int _maxProcesNumber =(int) parameters.NUMBER_OF_PROCESSES.getValue();
    protected double _timeStamp = parameters.TIME_STAMP.getValue();
    protected double _curTime = 0;

    protected ArrayList<Proces> _procesesQueue = new ArrayList<>();
    protected ArrayList<Proces> _endedProcesses = new ArrayList<>();

    private int _numberOfProceses = 0;
    private ProcesMaker _procesMaker = ProcesMaker.getInstance();
    protected String algName;


    public void run(){
        while(_maxProcesNumber >= _numberOfProceses + 1 || !_procesesQueue.isEmpty()){
            getProcesFromQueue();
            //QueueInfo();
            CPUManagement();
            _curTime += _timeStamp;
            try {
                Thread.sleep((long) parameters.LOOP_SLEEP.getValue());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        getInfo();
    }
    
    public void getProcesFromQueue(){
        if (_numberOfProceses >= _procesMaker.procesList.size())
            return;

        try {
            addToQueue( (Proces)_procesMaker.procesList.get(_numberOfProceses++).clone());
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    abstract void addToQueue(Proces newProces);

    abstract void CPUManagement();

    protected void procesDone(Proces proces){
        _endedProcesses.add(proces);
        _procesesQueue.remove(proces);
        proces.setProcesLength(0);
    }

    public void QueueInfo(){
        System.out.println("\n\tprocesses in queue:");
        for(Proces process : _procesesQueue)
            System.out.print(process.toString() + " | ");

        System.out.println("\n\tprocesses ended");
        for(Proces process : _endedProcesses)
            System.out.print(process.toString() + " | ");

        System.out.println("\n=========================================");
    }

    public void procesUpdate(Proces curProces) {
        Iterator<Proces> iterator = _procesesQueue.iterator();
        while (iterator.hasNext()) {
            Proces proces = iterator.next();
            if (curProces != proces) {
                proces.update(_timeStamp);
                if(proces.is_starving()) {
                    iterator.remove();
                    procesDone(proces);
                }
            }
        }
    }

    public Proces getProcesFromQueue(int index, double time){
        Proces proces = _procesesQueue.get(index);
        proces.update(time);
        return proces;
    }
    public void getInfo(){
        int numberOfRealised = 0;
        double averageWaiting = 0;
        double algoritmEndTime =  _curTime;
        double maxWaitingTime = 0;
        double minWaitingTime = Double.MAX_VALUE;
        double numberOfStarving = 0;
        double standardDeviation = 0;

        for (Proces proces : _endedProcesses){
            if(proces.is_starving()){
                numberOfStarving++;
            }else {
                averageWaiting+=proces.get_waitingTime();
                numberOfRealised++;
                if(proces.get_waitingTime() > maxWaitingTime)
                    maxWaitingTime = proces.get_waitingTime();
                else if(proces.get_waitingTime() < minWaitingTime)
                    minWaitingTime = proces.get_waitingTime();
            }
        }
        averageWaiting = averageWaiting/numberOfRealised;
        for (Proces proces : _endedProcesses){
            if(!proces.is_starving()) {
                standardDeviation += Math.pow (averageWaiting - proces.get_waitingTime(),2);
                standardDeviation /= numberOfRealised;
                standardDeviation = Math.sqrt(standardDeviation);
            }
        }

        System.out.println("\nalgoritm name: " + algName +
                "\nnumber Of Realised: " + numberOfRealised +
                "\nnumber Of Starving: " + numberOfStarving +
                "\naverage waiting time per proces: " + averageWaiting +
                "\nalgoritm end time: " + algoritmEndTime +
                "\nmax waiting time for one proces: " + maxWaitingTime +
                "\nmin waiting time for one proces: " + minWaitingTime +
                "\nstandard waiting time devation: " + standardDeviation + "\n");
    }

}
