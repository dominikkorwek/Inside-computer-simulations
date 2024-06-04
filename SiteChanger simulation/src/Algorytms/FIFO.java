package Algorytms;

import other.Frame;

public class FIFO extends SiteChanger{
    int _index;

    public FIFO() {
        _name = "FIFO";
        _index = 0;
    }

    @Override
    void HandleProces() {
        if(_frameQueue.isEmpty())
            return;

        Frame frame = _frameQueue.removeFirst();

        for (Frame f : _frames)
            if (f != null && f.equals(frame))
                return;

        _error++;
        _frames[_index] = frame;
        _index = (++_index)% _frames.length;
    }
}