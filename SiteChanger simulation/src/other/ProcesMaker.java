package other;

import java.util.ArrayList;
import java.util.Random;
public class ProcesMaker implements Runnable{
    private final int maxProcesNumber = parameters.NUMBER_OF_PROCESSES.getValue();
    private final int newProcesPropability = parameters.NEW_PROCES_PROPABILITY.getValue();

    private final ArrayList<Proces> procesList = new ArrayList<>();
    private static volatile ProcesMaker procesMaker = null;

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
        if(maxProcesNumber< Proces._processesNumber)
            return;

        Random random = new Random();
        if(random.nextInt(100) < newProcesPropability){
            Proces newProces = new Proces();
            procesList.add(newProces);
        }
    }

    @SuppressWarnings("BusyWait")
    @Override
    public void run() {
        while(maxProcesNumber >= Proces._processesNumber){
            makeProces();
            try {
                Thread.sleep(parameters.LOOP_SLEEP.getValue());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ArrayList<Proces> getProcesList(){
        return procesList;
    }
}
