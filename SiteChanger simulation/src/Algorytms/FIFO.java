package Algorytms;

import other.Proces;

public class FIFO extends SiteChanger{
    int _index;

    public FIFO() {
        _name = "FIFO";
        _index = 0;
    }

    @Override
    void HandleProces() {
        if(_procesQueue.isEmpty())
            return;

        Proces proces = _procesQueue.removeFirst();

        for (Proces frame : _frames)
            if (frame!=null && frame.equals(proces))
                return;

        _error++;
        _frames[_index] = proces;
        _index = (++_index)% _frames.length;

    }
}
