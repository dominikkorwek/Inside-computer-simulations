package Algorytms;

import other.Proces;
import other.Procesor;
import other.parameters;

import java.util.ArrayList;
import java.util.Random;

public class AboveLoadSend extends ProcesorMover{
    private final int p = parameters.MAX_PROCESOR_LOAD.getValue();
    private final Random random = new Random();

    public AboveLoadSend() {
        name = "Above Load Send";
    }

    @Override
    protected void handleNewProces(Procesor procesor, Proces proces) {
        Procesor otherProcesor;
        ArrayList<Procesor> notSearched = new ArrayList<>(procesors);
        notSearched.remove(procesor);

        if (procesor.getProcesorLoad() <= p) {
            procesor.addProces(proces);
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
