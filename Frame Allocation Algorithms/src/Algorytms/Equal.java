package Algorytms;

import other.Frame;

public class Equal extends SiteChanger{
    public Equal() {
        name = "Equal Allocation";
    }

    @Override
    protected void setRAM() {
        int freeRAM = numberOfRAM;
        for(int i = 0; i < numberOfProcesors - 1; i++){
            int size = numberOfRAM/numberOfProcesors;
            freeRAM-=size;
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
