//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package other;

import java.util.ArrayList;
import java.util.Random;

public class ProcesMaker implements Runnable {
    private final int maxProcesNumber = parameters.NUMBER_OF_PROCESSES.getValue();
    private final int newProcesPropability = parameters.NEW_PROCES_SPAWN_RATE.getValue();
    private final int newRealTimeProcesPropability = parameters.NEW_REALTIME_PROCES_PROPABILITY.getValue();
    public ArrayList<Proces> procesList;
    int curTime;
    private static ProcesMaker procesMaker = null;

    private ProcesMaker() {
        procesList = new ArrayList<>();
        curTime = 0;
    }

    public static ProcesMaker getInstance() {
        if (procesMaker == null)
           procesMaker = new ProcesMaker();

        return procesMaker;
    }

    public void makeProces() {
        if (maxProcesNumber >= Proces.processesNumber) {
            Random random = new Random();

            if (curTime++ >= newProcesPropability) {
                curTime = 0;

                Proces newProces;
                if (random.nextInt(100) < newRealTimeProcesPropability)
                    newProces = new RealTimeProces();
                 else
                    newProces = new Proces();

                procesList.add(newProces);
            }
        }
    }

    @SuppressWarnings("BusyWait")
    public void run() {
        while(maxProcesNumber >= Proces.processesNumber) {
            makeProces();

            try {
                Thread.sleep(parameters.LOOP_SLEEP.getValue());
            } catch (InterruptedException var2) {
                throw new RuntimeException(var2);
            }
        }

    }
}
