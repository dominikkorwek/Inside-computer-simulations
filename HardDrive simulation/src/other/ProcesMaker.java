//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package other;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProcesMaker {
    private final int _maxProcesNumber;
    private final int _newProcesPropability;
    private final int _newRealTimeProcesPropability;
    public List<Proces> procesList;
    private int _curTime;
    private final Runnable _addToIndex;
    private Proces _newProces;
    private final int _HDDcapacity;
    private static ProcesMaker procesMaker = null;


    public static ProcesMaker getInstance() {
        if (procesMaker == null)
            procesMaker = new ProcesMaker();

        return procesMaker;
    }


    private ProcesMaker() {
        procesList = new ArrayList<>();
        _curTime = 0;
        _addToIndex =  switch (parameters.SIMULATION_TYPE.getValue()){
            case 0 ->  this::fillAllSpace;
            case 1 ->  this::fillOneSpace;
            case 2 ->  this::fillCorners;
            case 3 ->  this::fillSorted;
            case 4 -> this::fillSortedAndRevSorted;
            default -> throw new IllegalStateException("Unexpected value: " + parameters.SIMULATION_TYPE.getValue());
        };
        _maxProcesNumber = parameters.NUMBER_OF_PROCESSES.getValue();
        _newProcesPropability = parameters.NEW_PROCES_SPAWN_RATE.getValue();
        _newRealTimeProcesPropability = parameters.NEW_REALTIME_PROCES_PROPABILITY.getValue();
        _HDDcapacity = parameters.CAPACITY.getValue();
    }

    private void fillAllSpace() {
        Random random = new Random();
        _newProces.space = random.nextInt(_HDDcapacity - 1) + 1;
    }
    private void fillOneSpace(){
        Random random = new Random();
        _newProces.space = random.nextInt(Math.min(50, _HDDcapacity /10 ))  + 1;
    }
    private void fillCorners(){
        Random random = new Random();
        int side = random.nextInt(2);
        if (side == 0)
            _newProces.space = random.nextInt(Math.min(25, _HDDcapacity /20 )) + 1;
        else
            _newProces.space = _HDDcapacity - 1 - random.nextInt(Math.min(25, _HDDcapacity /20 )) ;
    }
    private int index = 1;
    private int whereToGo = 10;

    private void fillSorted(){
        _newProces.space = index;
        index += whereToGo;
        if(index >= _HDDcapacity)
            index = 1;
    }

    private void fillSortedAndRevSorted() {
        _newProces.space = index;
        index += whereToGo;

        if(index >= _HDDcapacity || index <= 0) {
            whereToGo *= -1;
            index += (whereToGo + whereToGo);
        }
    }

    public void makeProces() {

        if (_maxProcesNumber >= Proces.amountOfProceses) {
            Random random = new Random();

            if (_curTime++ >= _newProcesPropability) {
                _curTime = 0;

                if (random.nextInt(100) < _newRealTimeProcesPropability)
                    _newProces = new RealTimeProces();
                else
                    _newProces = new Proces();

                _addToIndex.run();

                procesList.add(_newProces);
            }
        }
    }

}
