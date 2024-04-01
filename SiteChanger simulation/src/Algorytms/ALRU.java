package Algorytms;

import other.Proces;

import java.util.LinkedList;
import java.util.Queue;


public class ALRU extends SiteChanger{
    private final Queue<BitSite> _siteUsed = new LinkedList<>();

    public ALRU(){
        _name = "ALRU";
        for(int i = 0; i < _frames.length; i++)
            _siteUsed.add(new BitSite(i));
    }

    @Override
    void HandleProces() {
        if(_procesQueue.isEmpty())
            return;

        Proces proces = _procesQueue.removeFirst();

        for(int i = 0; i < _frames.length; i++)
            if(_frames[i]!=null &&_frames[i].equals(proces)){
                BitSite site = new BitSite(i);
                _siteUsed.remove(site);
                _siteUsed.add(site);
                return;
            }

        int index = -1;
        while(index == -1){
            BitSite site = _siteUsed.poll();
            assert site != null;

            if(site.referenceBit){
                site.referenceBit = false;
                _siteUsed.add(site);
            }
            else index = site.index;
        }

        _frames[index] = proces;
        _siteUsed.add(new BitSite(index));
        _error++;
    }

    private static class BitSite{
        int index;
        boolean referenceBit;

        public BitSite(int site) {
            this.index = site;
            referenceBit = true;
        }

        @Override
        public boolean equals(Object o){
            if(this == o) return true;
            if(o instanceof BitSite){
                return index ==((BitSite) o).index;
            }

            return false;
        }

    }
}
