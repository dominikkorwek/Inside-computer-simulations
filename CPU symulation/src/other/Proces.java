package other;

import java.util.Random;

public class Proces implements Cloneable{
    private final int _maxLength = (int) parameters.MAX_PROCES_LENGTH.getValue();
    private final int _minLength = (int) parameters.MIN_PROCES_LENGTH.getValue();
     private final double _shutDownProcesTime = parameters.TIME_SWAP_BETWEEN_PROCESSES.getValue();
     private final double _starvingTime = parameters.STARVING_TIME.getValue();

    public static int processesNumber = 1;
    private final int _number;
    private final double _birthTime;
    private double _waitingTime;
    private double _procesLength;
    private boolean _starving = false;


    public Proces(double currentTime) {
        _number = processesNumber++;
        _birthTime = currentTime;
        _waitingTime = 0;
        _procesLength = ProcesLength();
    }

    private int ProcesLength() {
        Random random = new Random();
        return random.nextInt(_maxLength-_minLength) + _minLength;
    }

    public void update(double timeStamp){
        _waitingTime = Math.round((_waitingTime + timeStamp) * 100.0) / 100.0;
        if(_waitingTime>_starvingTime)
            _starving = true;
    }

    public void handleProces(double timeStamp){
        _procesLength = Math.round((_procesLength - timeStamp) * 100.0) / 100.0;
    }

    @Override
    public String toString(){
        String info =_number + "." +
                " birth: " + _birthTime +
                " waiting: " + _waitingTime;
        if (_procesLength > 0 )
            info += " length: " + _procesLength;

        return info;
    }
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public double getProcesLength(){
        return _procesLength;
    }
    public double getShutDownProcesTime(){
        return _shutDownProcesTime;
    }

    public void setProcesLength(double _procesLength) {
        this._procesLength = _procesLength;
    }

    public boolean is_starving() {
        return _starving;
    }

    public double get_waitingTime() {
        return _waitingTime;
    }
}
