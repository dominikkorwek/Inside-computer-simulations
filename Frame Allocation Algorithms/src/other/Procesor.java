package other;
import java.util.*;

public class Procesor{
    private final int numberOfFrames;
    private final int timeFrame;
    private final List<Frame> localFrames;
    private final int number;
    private static int framesNumber;
    private final List<Frame> newFrames;
    private final int newProcesPropability;
    private int errors;
    private final List<Integer> errorsInTimeList;
    private final List<Integer> numberInTimeList;
    private boolean Hibernated = false;

    public Procesor(int number) {
        this.number = number;
        framesNumber = 0;
        errors = 0;
        localFrames = new ArrayList<>();
        timeFrame = parameters.TIMEFRAME.getValue();
        numberOfFrames = parameters.NUMBER_OF_FRAMES.getValue();
        errorsInTimeList = new ArrayList<>();
        numberInTimeList = new ArrayList<>();
        newFrames = new ArrayList<>();

        Random random = new Random();
        int _maxFrameNumber = parameters.MAX_FRAME_NUMBER.getValue();
        int _minFrameNumber = parameters.MIN_FRAME_NUMBER.getValue();
        int _maxProcesPropability = parameters.MAX_FRAME_MAKE_PROPABILITY.getValue();
        int _minProcesPropability = parameters.MIN_FRAME_MAKE_PROPABILITY.getValue();
        int numberOfLocalFrame = random.nextInt(_maxFrameNumber - _minFrameNumber) + _minFrameNumber;

        for (int i = 0; i < numberOfLocalFrame; i++){
            Frame frame = new Frame(number*100 + random.nextInt(_maxFrameNumber));
            while (localFrames.contains(frame))
                frame= new Frame(number*100 + random.nextInt(_maxFrameNumber));

            localFrames.add(frame);
        }
        newProcesPropability = random.nextInt(_maxProcesPropability - _minProcesPropability) + _minProcesPropability;
        for (int i = 0; i< timeFrame; i++ ){
            errorsInTimeList.add(0);
            numberInTimeList.add(-1);
        }
    }


    public boolean genererateFrame() {
        Random random = new Random();

        if (Hibernated)
            return true;

        if (framesNumber >= numberOfFrames)
            return false;

        if(random.nextInt(100) > newProcesPropability)
            return true;


        Frame newFrame = (Frame) localFrames.get(random.nextInt(localFrames.size())).clone();
        newFrames.add(newFrame);
        framesNumber++;
        return true;
    }

    public void PPF(boolean error){
        if (error) {
            errorsInTimeList.add(1);
            errors++;
        }
        else
            errorsInTimeList.add(0);

        if (errorsInTimeList.size() > timeFrame*2)
            errorsInTimeList.removeFirst();
    }
    public void WSS(int n){
        numberInTimeList.add(n);
        if (errorsInTimeList.size() > timeFrame*2)
            errorsInTimeList.removeFirst();
    }


    @Override
    public Procesor clone(){
        return new Procesor(number);
    }
    public int localFramesSize(){
        return localFrames.size();
    }

    public int getErrors() {
        return errors;
    }

    public List<Frame> getNewFrames() {
        return newFrames;
    }

    public int PPF(){
        int suma = 0;
        for (int i =0 ; i< timeFrame; i++){
            suma += errorsInTimeList.get(errorsInTimeList.size() - i - 1);
        }

        return suma;
    }

    public Set<Integer> WSS(){
        Set<Integer> uniqueNumbers = new HashSet<>();
        for (int i =0 ; i< timeFrame; i++){
            int number = numberInTimeList.get(numberInTimeList.size() - i - 1);
            if (number != -1)
                uniqueNumbers.add(number);
        }
        return uniqueNumbers;
    }

    public void setHibernated(boolean hibernated) {
        Hibernated = hibernated;
    }

    public boolean isHibernated() {
        return Hibernated;
    }
}