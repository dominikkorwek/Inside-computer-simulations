package Algorytms;

import other.Frame;

import java.util.Random;

@SuppressWarnings("ALL")
public class RAND extends SiteChanger{
    private final Random random;

    public RAND() {
        _name = "RAND";
        random = new Random();
    }

    @Override
    void HandleProces() {
        if (_frameQueue.isEmpty())
            return;

        Frame frame = _frameQueue.removeFirst();

        for (int i = 0; i< _frames.length;i++)
            if (_frames[i]!=null && _frames[i].equals(frame))
                return;
            else if (_frames[i] == null) {
                _frames[i] = frame;
                _error++;
                return;
            }

        int index = random.nextInt(_frames.length);
        _frames[index] = frame;
        _error++;
    }
}
