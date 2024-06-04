package other;

public class Frame implements Cloneable{
    private final int _siteNumber;

    public Frame(int _siteNumber) {
        this._siteNumber = _siteNumber;
    }

    public boolean equals(Object o){
        if(o == this) return true;
        if (!(o instanceof Frame) || o.getClass() != getClass()) return false;
        return ((Frame) o).getSiteNumber() == getSiteNumber();
    }


    public int getSiteNumber() {
        return _siteNumber;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }


}
