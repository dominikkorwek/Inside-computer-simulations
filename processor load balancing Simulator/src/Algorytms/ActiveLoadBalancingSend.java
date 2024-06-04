package Algorytms;

import other.Proces;
import other.Procesor;
import other.parameters;

import java.util.ArrayList;
import java.util.Random;

public class ActiveLoadBalancingSend extends ProcesorMover{
    private final int p = parameters.MAX_PROCESOR_LOAD.getValue();
    private final int r = parameters.MIN_PROCESOR_LOAD.getValue();
    private final Random random = new Random();

    public ActiveLoadBalancingSend() {
        name = "Active Load Balancing Send";
    }

    @Override
    protected void handleNewProces(Procesor procesor, Proces proces) {
        Procesor otherProcesor = procesor;
        ArrayList<Procesor> notSearched = new ArrayList<>(procesors);
        notSearched.remove(procesor);

        if (procesor.getProcesorLoad() <= p) {
            procesor.addProces(proces);

            if (procesor.getProcesorLoad() <= r){
                while(otherProcesor == procesor) {
                    otherProcesor = procesors.get(random.nextInt(procesors.size()));
                    questions++;
                }

                while (otherProcesor.getProcesorLoad() > p && procesor.getProcesorLoad() <= p/2){
                    procesor.addProces(otherProcesor.remProces());
                    migration++;
                }
            }
            return;
        }


        do{
            otherProcesor = notSearched.get(random.nextInt(notSearched.size()));
            notSearched.remove(otherProcesor);
            questions++;
        }while(otherProcesor.getProcesorLoad() > p && !notSearched.isEmpty()); //do skutku? xdddd

        otherProcesor.addProces(proces);
        migration++;
    }
}
