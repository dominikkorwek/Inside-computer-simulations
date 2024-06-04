package other;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
@SuppressWarnings("ALL")
public class sequence implements Cloneable{
    private final int _maxSiteNumber = parameters.MAX_FRAME_NUMBER.getValue();
    private final int _maxProcesNumber = parameters.NUMBER_OF_FRAME.getValue();
    private final int _localPropability = parameters.LOCAL_PROPABILITY.getValue();

    public int _framesNumber = 1;
    private final List<Frame> frameList = new LinkedList<>();

    public sequence() {
        Random random = new Random();
        if (random.nextInt(100) <= _localPropability)
            genererateFramesLocal();
        else generateFramesRandom();
    }

    private void generateFramesRandom(){
        Random random = new Random();
        int amount = 300;
        amount = Math.max(10,_maxProcesNumber/(amount)) + 10;
        amount =  Math.max(20,_maxProcesNumber/(amount*amount)) + 5;

        for (int i=0;i< amount; i++){
            int number = random.nextInt(_maxSiteNumber);
            frameList.add(new Frame(number));
        }
        _framesNumber += amount;
    }

    private void genererateFramesLocal(){
        Random random = new Random();
        int amount = 500;
        amount = Math.max(10,_maxProcesNumber/(amount)) + 10;
        amount =  Math.max(40,_maxProcesNumber/(amount)) + 5;

        int family = random.nextInt((parameters.CAPACITY.getValue()/4)) + 1;
        int core = random.nextInt(_maxSiteNumber) + 1;

        for (int i=0;i< amount; i++){
            int number = -1;
            while(number < 0 || number > _maxSiteNumber)
                number = core + random.nextInt(family * 2) - family;
            frameList.add(new Frame(number));
        }
        _framesNumber += amount;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public List<Frame> getFrameList() {
        return frameList;
    }
}