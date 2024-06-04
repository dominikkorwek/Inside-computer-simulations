package other;

public class Frame {
    private final int siteNumber;
    private int old;
    private final boolean emptySlot;

    public Frame(int siteNumber) {
        this.siteNumber = siteNumber;
        old = 0;
        emptySlot = false;
    }
    public Frame(){
        siteNumber = -1;
        old = Integer.MAX_VALUE;
        emptySlot = true;
    }

    public boolean equals(Object o){
        if(o == this) return true;
        if (!(o instanceof Frame) || o.getClass() != getClass()) return false;
        return ((Frame) o).getSiteNumber() == getSiteNumber();
    }

    public int getSiteNumber() {
        return siteNumber;
    }

    @Override
    public Frame clone(){
        return new Frame(siteNumber);
    }

    public int getOld() {
        return old;
    }
    public void resetOld(){
        old = 0;
    }
    public void makeOlder() {
        if (!emptySlot)
            old++;
    }
}
