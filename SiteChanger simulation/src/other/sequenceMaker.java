package other;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class sequenceMaker {
    private final int maxProcesNumber = parameters.NUMBER_OF_FRAME.getValue();
    private final int newProcesPropability = parameters.NEW_SEQUENCE_PROPABILITY.getValue();

    private final List<sequence> sequenceList = new ArrayList<>();
    private static volatile sequenceMaker sequenceMaker = null;
    private int _numberOfFrames;

    private sequenceMaker(){
        _numberOfFrames = 0;
    }
    public static sequenceMaker getInstance() {
        if (sequenceMaker == null) {
            synchronized (sequenceMaker.class) {
                if (sequenceMaker == null) {
                    sequenceMaker = new sequenceMaker();
                }
            }
        }
        return sequenceMaker;
    }


    public void makeProces(){

        Random random = new Random();
        if(random.nextInt(100) < newProcesPropability){
            sequence newSequence = new sequence();
            sequenceList.add(newSequence);
            _numberOfFrames += newSequence._framesNumber;
        }
    }


    public void run() {
        if(maxProcesNumber >= _numberOfFrames){
            makeProces();
        }
    }

    public List<sequence> getProcesList(){
        return sequenceList;
    }
}
