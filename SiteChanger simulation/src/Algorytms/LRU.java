package Algorytms;

import other.Frame;
import other.parameters;

public class LRU extends SiteChanger{
    private final int[] _siteUsed = new int[parameters.CAPACITY.getValue()];
    public LRU(){
        _name = "LRU";
        for(int i = 0; i < _frames.length; i++)
            _siteUsed[i] = 0;
    }

    @Override
    void HandleProces() {
        if(_frameQueue.isEmpty())
            return;

        Frame frame = _frameQueue.removeFirst();
        int max = -1;
        int index = -1;

        for(int i = 0 ; i < _siteUsed.length ; i++)
            _siteUsed[i]++;

        for (int i = 0; i< _frames.length;i++)
            if (_frames[i]!=null && _frames[i].equals(frame)) {
                _siteUsed[i] = 0;
                return;
            }

        for(int i = 0 ; i < _siteUsed.length ; i++){
            if (max < _siteUsed[i]){
                max = _siteUsed[i];
                index = i;
            }
        }

        _error++;
        _frames[index] = frame;
        _siteUsed[index] = 0;
    }
}