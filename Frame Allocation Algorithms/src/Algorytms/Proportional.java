package Algorytms;

import other.Frame;
import other.Procesor;

public class Proportional extends SiteChanger{
    public Proportional() {
        name = "Proportional Allocation";
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
    protected void handleRAM() {

    }
}
