package Algorytms;

import other.Proces;
import other.Procesor;
import other.parameters;

import java.util.ArrayList;
import java.util.Random;

public class LoadWithRandomSend extends ProcesorMover{
    private final int Maxtries = parameters.TRY_TO_FIND_OTHER_PROCESOR.getValue();
    private final int p = parameters.MAX_PROCESOR_LOAD.getValue();
    private final Random random = new Random();

    public LoadWithRandomSend() {
        name = "Load with Random Send";
    }

    @Override
    protected void handleNewProces(Procesor procesor, Proces proces) {
        Procesor otherProcesor;
        ArrayList<Procesor> notSearched = new ArrayList<>(procesors);
        notSearched.remove(procesor);

        int i = 0;
        do {
            otherProcesor = notSearched.get(random.nextInt(notSearched.size()));
            notSearched.remove(otherProcesor);
            questions++;
            i++;
            if (i == Maxtries)
                break;
        }while(otherProcesor.getProcesorLoad() > p && !notSearched.isEmpty());

        if (otherProcesor.getProcesorLoad() <= p) {
            migration++;
            otherProcesor.addProces(proces);
        }
        else procesor.addProces(proces);
    }
}
