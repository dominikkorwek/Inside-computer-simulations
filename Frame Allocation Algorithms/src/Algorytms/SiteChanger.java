package Algorytms;

import java.util.*;
import other.*;

import static java.lang.StringTemplate.STR;

public abstract class SiteChanger{
    protected final int numberOfProcesors;
    protected final int numberOfRAM;
    protected final ProcesorMaker procesorMaker;
    protected final ArrayList<Frame>[] RAM;
    private final int[] NOFIP;
    protected final Procesor[] procesors;
    protected String name;

    public SiteChanger() {
        numberOfProcesors = parameters.NUMBER_OF_PROCESORS.getValue();
        RAM = new ArrayList[numberOfProcesors];
        procesorMaker = ProcesorMaker.getInstance();
        NOFIP = new int[numberOfProcesors];
        procesors = new Procesor[numberOfProcesors];
        numberOfRAM = parameters.CAPACITY.getValue();


        for (int i = 0; i < RAM.length; i++)
            RAM[i] = new ArrayList<>();
        setRAM();

        for (int i = 0; i<numberOfProcesors ; i ++){
            procesors[i] = procesorMaker.getProcesors()[i].clone();
            NOFIP[i] = 0;
        }
    }

    public void run(boolean run){
        if (!run)
            return;
        handleNewProces();
    }

    private void handleNewProces(){
        for(int i = 0 ;i < numberOfProcesors; i++){
            Procesor p = procesorMaker.getProcesors()[i];
            if (procesors[i].isHibernated())
                continue;

            if(NOFIP[i] < p.getNewFrames().size()) {
                Frame f = p.getNewFrames().get(NOFIP[i]);
                NOFIP[i]++;
                LRU(f , i);
            }
            else {
                procesors[i].PPF(false);
                procesors[i].WSS(-1);
            }//dla globalnego zostawic
        }
        handleRAM();
    }
    protected abstract void setRAM();
    protected abstract void handleRAM();

    private void LRU(Frame frame, int procesorIndex){
        if (RAM[procesorIndex].isEmpty()){
            procesors[procesorIndex].PPF(false);
            procesors[procesorIndex].WSS(frame.getSiteNumber());
            return;
        }

        int maxAge = -1;
        int index = -1;

        for(int i = 0 ; i < RAM[procesorIndex].size() ; i++){
            if (maxAge < RAM[procesorIndex].get(i).getOld()){
                maxAge = RAM[procesorIndex].get(i).getOld();
                index = i;
            }
            RAM[procesorIndex].get(i).makeOlder();
        }

        for (Frame value : RAM[procesorIndex])
            if (value != null && value.equals(frame)) {
                value.resetOld();
                procesors[procesorIndex].PPF(false);
                procesors[procesorIndex].WSS(value.getSiteNumber());
                return;
            }
        RAM[procesorIndex].remove(index);
        RAM[procesorIndex].add(frame);
        procesors[procesorIndex].PPF(true);
        procesors[procesorIndex].WSS(frame.getSiteNumber());
    }

    public void getInfo(){
        System.out.println("=============================================");
        System.out.println(STR."name: \{name}");
        int allErrors = 0;
        for (int i = 0 ; i < numberOfProcesors; i++) {
            Procesor procesor = procesors[i];
            allErrors += procesor.getErrors();
            System.out.println(STR."Procesor \{i+1} errors : \{procesor.getErrors()}");
        }

        System.out.println(STR."All errors: \{allErrors}");
    }
}