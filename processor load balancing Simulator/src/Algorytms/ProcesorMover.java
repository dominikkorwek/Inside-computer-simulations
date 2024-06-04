package Algorytms;

import other.Maker;
import other.Proces;
import other.Procesor;
import other.parameters;

import java.util.ArrayList;

import static java.lang.StringTemplate.STR;

public abstract class ProcesorMover {
    protected final ArrayList<Procesor> procesors;
    private final Maker maker;
    private int _curTime;
    protected int migration = 0;
    protected int questions = 0;
    private final int timeToHandle;
    private int index = 0;
    protected String name;

    public ProcesorMover() {
        _curTime = 0;
        maker =  Maker.getInstance();
        procesors = maker.getProcesors();
        timeToHandle = parameters.TIME_TO_HANDLE_PER_LOOP.getValue();
    }

    public boolean run(){
        if (maker.size() > index){
            _curTime++;
            addNewProceses();
            for (Procesor p : procesors){
                p.handleProceses(timeToHandle);
                if (index > parameters.INDEX_TO_START_COUNTING_LOAD.getValue())
                    p.countLoadInTime();
            }
            return true;
        }
        return false;
    }
    private void addNewProceses(){
        for (Procesor p : procesors){
            if (_curTime%p.getTerm() == 0 && maker.size() > index) {
                Proces proces = maker.getNewProces(index++);
                handleNewProces(p, proces);
            }
        }
    }
    protected abstract void handleNewProces(Procesor procesor, Proces proces);

    public void getInfo(){
        System.out.println(STR."name: \{name}");
        double averageLoad = 0;
        double standardDeviation = 0;
        for (Procesor p : procesors) {
            averageLoad += p.getLoadInTime();
        }

        averageLoad /= procesors.size();
        System.out.println(STR."Average Load: \{averageLoad}");

        for (Procesor p : procesors){
            standardDeviation += Math.pow(p.getLoadInTime() - averageLoad,2);
        }
        standardDeviation /= procesors.size() - 1;
        standardDeviation = Math.sqrt(standardDeviation);
        System.out.println(STR."Standard Devation: \{standardDeviation}");
        System.out.println(STR."Questions: \{questions}");
        System.out.println(STR."Migration: \{migration}");
        System.out.println("======================");
    }

}
