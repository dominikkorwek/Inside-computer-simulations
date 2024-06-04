package Algorytms;

import java.util.*;
import other.*;
public abstract class SiteChanger{
    private final int _maxFrameNumber = parameters.NUMBER_OF_FRAME.getValue();
    private final int _capacity = parameters.CAPACITY.getValue();
    private final sequenceMaker _sequenceMaker = sequenceMaker.getInstance();

    protected Frame[] _frames;
    protected final List<Frame> _frameQueue;
    protected  int _error;
    protected int _numberOfsequences;
    protected int _numberOfFrames;
    protected String _name;

    public SiteChanger() {
        _numberOfsequences = 0;
        _numberOfFrames = 0;
        _error = 0;
        _frames = new Frame[_capacity];
        _frameQueue = new ArrayList<>();
    }
    public boolean run(){
        if(_maxFrameNumber >= _numberOfFrames + 1 || !_frameQueue.isEmpty() ) {
            getProcesFromQueue();
            HandleProces();
            return true;
        }

        return false;
    }
    public void getProcesFromQueue(){
        while (_numberOfsequences < _sequenceMaker.getProcesList().size()){
            try {
            sequence sequence = (sequence) _sequenceMaker.getProcesList().get(_numberOfsequences++).clone();
            _frameQueue.addAll(sequence.getFrameList());
            _numberOfFrames += sequence._framesNumber;

            } catch (CloneNotSupportedException e) {
               throw new RuntimeException(e);
            }
        }
    }

    abstract void HandleProces();

    public String toString(){
        String v = "name: " + _name + "\n";
        v += "number of errors: " + _error + "\n";
        return v;
    }
}