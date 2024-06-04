package Algorytms;

import other.Frame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OPT extends SiteChanger{
    private final List<Frame> pomFrame = new ArrayList<>();


    public OPT() {
        _name = "OPT";
    }

    @Override
    void HandleProces() {
        if(_frameQueue.isEmpty())
            return;

        Frame frame = _frameQueue.removeFirst();

        for (int i = 0;i< _frames.length;i++)
            if (_frames[i]!=null && _frames[i].equals(frame))
                return;
            else if (_frames[i] == null){
                _frames[i] = frame;
                _error++;
                return;
            }

        _error++;

        pomFrame.clear();
        pomFrame.addAll(Arrays.asList(_frames));

        int i = 0;
        while(pomFrame.size() != 1 && _frameQueue.size() - 1 >= i){
            Frame pomProces = _frameQueue.get(i++);
            pomFrame.remove(pomProces);
        }

        Frame frameToSwap = pomFrame.getFirst();

        for(i = 0; i < _frames.length; i++)
            if(_frames[i] == frameToSwap) {
                _frames[i] = frame;
                return;
            }
    }
}
