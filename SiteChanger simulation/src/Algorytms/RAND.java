package Algorytms;

import other.Proces;

import java.util.Random;

public class RAND extends SiteChanger{
    int emptyFrames;
    int index;

    public RAND() {
        _name = "RAND";
        index = 0;
        emptyFrames = _frames.length;
    }

    @Override
    void HandleProces() {
        if (_procesQueue.isEmpty())
            return;

        Proces proces = _procesQueue.removeFirst();

        for (Proces frame : _frames)
            if (frame!=null && frame.equals(proces))
                return;

        if (emptyFrames > 0){
            emptyFrames--;
            _frames[index++] = proces;
        }else {
            Random random = new Random();
            index = random.nextInt(_frames.length);
            _frames[index] = proces;
        }
        _error++;
    }
}
