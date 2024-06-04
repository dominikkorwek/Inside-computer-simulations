package Algorytms;

import other.Frame;
import other.Procesor;
import other.parameters;

import java.util.*;

public class Zone extends SiteChanger{
    private int hibernatedAmount = 0;

    public Zone() {
        name = "Zone model";
    }

    @Override
    protected void setRAM() {
        int freeRAM = numberOfRAM;
        int S = 0;
        for (Procesor p : procesorMaker.getProcesors()){
            S += p.localFramesSize();
        }

        for(int i = 0; i < numberOfProcesors - 1; i++){
            int size = (int) (((double)(numberOfRAM * (procesorMaker.getProcesors()[i].localFramesSize()))/ S)+ 0.5);
            if (size == 0)
                size = 1;
            freeRAM -= size;

            for (int j = 0 ;j < size; j++)
                RAM[i].add(new Frame());
        }
        for (int j = 0 ;j < freeRAM; j++)
            RAM[numberOfProcesors - 1].add(new Frame());
    }

    @Override
    public void getInfo() {
        super.getInfo();
        System.out.println(STR."amount of hibernation: \{hibernatedAmount}");
    }

    private  int time = 0;
    private final int timeFrame = parameters.TIMEFRAME.getValue()/2;

    @Override
    protected void handleRAM() {
        time++;
        if (time%timeFrame != 0){
            return;
        }
        time = 0;

        int SumWSS = 0;
        int freeSpace = numberOfRAM;

        List<Set<Integer>> allWSS = new ArrayList<>();
        for (int i = 0; i < numberOfProcesors; i++){
            allWSS.add(procesors[i].WSS());
            SumWSS += allWSS.get(i).size();
            RAM[i].clear();

            if (procesors[i].isHibernated()){
                procesors[i].setHibernated(false);
            }
        }


        while (SumWSS > numberOfRAM) {
            int max = -1;
            int index = -1;

            for (int i = 0; i < numberOfProcesors; i++)
                if (allWSS.get(i).size() > max) {
                    max = allWSS.get(i).size();
                    index = i;
                }

            procesors[index].setHibernated(true);
            hibernatedAmount++;
            SumWSS -= allWSS.get(index).size();
            allWSS.get(index).clear();
        }

        for (int i = 0; i < numberOfProcesors; i++){
            for (Integer j : allWSS.get(i)){
                RAM[i].add(new Frame(j));
            }
            freeSpace -= allWSS.get(i).size();
        }

        while (freeSpace > 0){
            int maks = 0;
            int indeks = -1;
            for (int i = 0; i < numberOfProcesors; i++){
                int cPPF = procesors[i].PPF();
                if (cPPF > maks && !procesors[i].isHibernated()){
                    maks = cPPF;
                    indeks = i;
                }
            }
            RAM[indeks].add(new Frame());
            freeSpace--;
        }
    }
}
