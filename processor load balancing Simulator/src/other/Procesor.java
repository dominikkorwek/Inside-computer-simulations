package other;

import java.util.*;

public class Procesor{
    private final int term;
    private int procesorLoad;
    private final List<Proces> procesesInProcesor = new ArrayList<>();
    private final List<Integer> LoadInTime = new ArrayList<>();

    public Procesor() {
        Random random = new Random();

        procesorLoad = 0;

        int maxFrequency = parameters.MAX_FREQUENCY.getValue();
        int minFrequency = parameters.MIN_FREQUENCY.getValue();

        double time = parameters.TIME_IN_ONE_LOOP.getValue();
        double pomTerm = time/(random.nextInt(maxFrequency - minFrequency) + minFrequency);

        term = pomTerm < 1 ? 1 : (int) pomTerm;
    }
    private Procesor(int term,int procesorLoad){
        this.term = term;
        this.procesorLoad = procesorLoad;
    }

    public void addProces(Proces proces){
        procesesInProcesor.add(proces);
        procesorLoad += proces.getPower();
    }
    public void handleProceses(int time){
        while (time > 0 && !procesesInProcesor.isEmpty()){
             Proces proces = procesesInProcesor.getFirst();
             time = proces.handleTime(time);
             if (proces.getHandleTime() <= 0) {
                 procesorLoad -= proces.getPower();
                 procesesInProcesor.removeFirst();
             }
        }
    }
    public void countLoadInTime(){
        LoadInTime.add(procesorLoad);
    }

    public Proces remProces(){
        Proces p = procesesInProcesor.removeLast();
        procesorLoad -= p.getPower();
        return p;
    }

    public int getTerm() {
        return term;
    }

    public int getProcesorLoad() {
        return procesorLoad;
    }

    public int getLoadInTime() {
        int sum = 0;
        for (Integer i : LoadInTime)
            sum += i;

        return sum/LoadInTime.size();
    }

    @Override
    public Procesor clone() {
        return new Procesor(term, procesorLoad);
    }
}
