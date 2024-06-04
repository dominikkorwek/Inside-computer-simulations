package Algorytms;

import other.Frame;
import other.Procesor;
import other.parameters;

public class SiteError extends SiteChanger{
    private final int L = parameters.L.getValue();
    private final int U = parameters.U.getValue();

    public SiteError() {
        name = "Page error rate control";
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
    private  int time = 0;
    private final int timeFrame = parameters.TIMEFRAME.getValue()/2;


    @Override
    protected void handleRAM() {
        time++;
        if (time%timeFrame != 0){
            return;
        }
        time = 0;

        int freeSpace = 0;

        for (int i = 0; i < numberOfProcesors; i++){
            Procesor p = procesors[i];
            int PPF = p.PPF();

            if (PPF < L && RAM[i].size() > 1){
                RAM[i].removeFirst();
                freeSpace++;
            }
            else if (U < PPF){
                if (freeSpace > 0){
                    RAM[i].add(new Frame());
                    freeSpace--;
                }
                else {
                    int min = Integer.MAX_VALUE;
                    int indeks = -1;

                    for (int j = 0; j < numberOfProcesors; j++){
                        Procesor candidate = procesors[j];
                        int cPPF = candidate.PPF();
                        if (cPPF < min && RAM[j].size() > 1){
                            min = cPPF;
                            indeks = j;
                        }
                    }
                    RAM[indeks].removeFirst();
                    RAM[i].add(new Frame());
                }
            }
        }

        while (freeSpace > 0){
            int maks = 0;
            int indeks = -1;
            for (int i = 0; i < numberOfProcesors; i++){
                int cPPF = procesors[i].PPF();
                if (cPPF > maks){
                    maks = cPPF;
                    indeks = i;
                }
            }
            RAM[indeks].add(new Frame());
            freeSpace--;
        }
    }
}
