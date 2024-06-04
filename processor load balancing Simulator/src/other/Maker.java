package other;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Maker{
    private final ArrayList<Procesor> procesors = new ArrayList<>();
    private final List<Proces> procesesWaitingToADD = new ArrayList<>();
    private static Maker instance = null;


    private Maker(){
        for (int i = 0; i < parameters.NUMBER_OF_PROCESORS.getValue(); i++) {
            procesors.add(new Procesor());
        }

        for (int i = 0; i < parameters.NUMBER_OF_PROCESES.getValue(); i++){
            procesesWaitingToADD.add(new Proces());
        }
    }
    public static Maker getInstance(){
        if (instance == null)
            instance = new Maker();
        return instance;
    }

    public ArrayList<Procesor> getProcesors(){
        ArrayList<Procesor> list = new ArrayList<>();
        for (Procesor p : procesors)
            list.add(p.clone());
        return list;
    }

    public Proces getNewProces(int index){
        Proces p = procesesWaitingToADD.get(index);
        assert p != null;
        return p.clone();
    }

    public int size() {
        return procesesWaitingToADD.size();
    }
}
