package Algorytms;

import other.Proces;

import java.util.ArrayList;
import java.util.Arrays;

public class OPT extends SiteChanger{
    private final ArrayList<Proces> pomFrame = new ArrayList<>();
    int emptyFrames;
    int siteNumber;

    public OPT() {
        _name = "OPT";
        emptyFrames = _frames.length;
        siteNumber = 0;
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

        if (emptyFrames > 0) {
            emptyFrames--;
            _frames[siteNumber++] = proces;
            return;
        }
        pomFrame.clear();
        pomFrame.addAll(Arrays.asList(_frames));

        int i = 0;
        while(pomFrame.size() != 1 && _procesQueue.size() - 1 >= i){
            Proces pomProces = _procesQueue.get(i++);
            pomFrame.remove(pomProces);
        }

        Proces procesToSwap = pomFrame.getFirst();

        for(i = 0; i < _frames.length; i++)
            if(_frames[i] == procesToSwap) {
                _frames[i] = proces;
                return;
            }
    }
}
