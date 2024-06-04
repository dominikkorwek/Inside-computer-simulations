package other;

import java.util.Arrays;
import java.util.Comparator;

public class ProcesorMaker {
    private final int numberOfProcesors;
    public Procesor[] procesors;
    private static volatile ProcesorMaker ProcesorMaker = null;


    private ProcesorMaker(){
        numberOfProcesors = parameters.NUMBER_OF_PROCESORS.getValue();
        procesors = new Procesor[numberOfProcesors];

        makeProcesors();
        procesors = Arrays.stream(procesors)
                .sorted(Comparator.comparingInt(Procesor::localFramesSize))
                .toArray(Procesor[]::new);
    }
    public static ProcesorMaker getInstance() {
        if (ProcesorMaker == null) {
            synchronized (ProcesorMaker.class) {
                if (ProcesorMaker == null) {
                    ProcesorMaker = new ProcesorMaker();
                }
            }
        }
        return ProcesorMaker;
    }

    public void makeProcesors(){
        for(int i = 0; i< numberOfProcesors; i++)
            procesors[i] = new Procesor(i);
    }
    public boolean generateFrames() {
        boolean end = false;

        for(int i = 0; i< numberOfProcesors; i++)
             end |= procesors[i].genererateFrame();

        return end;
    }

    public Procesor[] getProcesors() {
        return procesors;
    }
}
