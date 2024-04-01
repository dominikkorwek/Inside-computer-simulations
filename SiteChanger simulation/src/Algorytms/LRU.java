package Algorytms;

import other.Proces;

import java.util.LinkedList;
import java.util.Queue;

public class LRU extends SiteChanger{
    private final Queue<Integer> _siteUsed = new LinkedList<>();
    public LRU(){
        _name = "LRU";
        for(int i = 0; i < _frames.length; i++)
            _siteUsed.add(i);
    }

    @Override
    @SuppressWarnings("DataFlowIssue")
    void HandleProces() {
        if(_procesQueue.isEmpty())
            return;

        Proces proces = _procesQueue.removeFirst();

        for(int i = 0; i < _frames.length; i++)
            if(_frames[i]!=null && _frames[i].equals(proces)){
                _siteUsed.remove(i);
                _siteUsed.add(i);
                return;
            }

        _error++;
        int index = _siteUsed.poll();
        _frames[index] = proces;
        _siteUsed.add(index);
    }
}
