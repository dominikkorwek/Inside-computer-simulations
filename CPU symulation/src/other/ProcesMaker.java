package other;

import java.util.ArrayList;
import java.util.Random;


public class ProcesMaker implements Runnable{
    private final int maxProcesNumber =(int) parameters.NUMBER_OF_PROCESSES.getValue();
    private final int newProcesPropability = (int) parameters.NEW_PROCES_PROPABILITY.getValue();
    protected double curTime = 0;
    public ArrayList<Proces> procesList = new ArrayList<>();
    private static ProcesMaker procesMaker = null;

    private ProcesMaker(){}
    public static ProcesMaker getInstance() {
        if (procesMaker == null) {
            synchronized (ProcesMaker.class) {
                if (procesMaker == null) {
                    procesMaker = new ProcesMaker();
                }
            }
        }
        return procesMaker;
    }

    public void makeProces(){
        if(maxProcesNumber< Proces.processesNumber)
            return;

        Random random = new Random();
        if (random.nextInt(100) > newProcesPropability)
            return;

        Proces newProces = new Proces(curTime);
        procesList.add(newProces);
    }


    @Override
    public void run() {
        while(maxProcesNumber>= Proces.processesNumber){
            makeProces();
            try {
                Thread.sleep((long) parameters.LOOP_SLEEP.getValue());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
