package other;
import java.util.Random;
public class Proces implements Cloneable{
    private final int _maxSiteNumber = parameters.MAX_SITE_NUMBER.getValue();
    public static int _processesNumber = 1;
    private final int _siteNumber;

    public Proces() {
        _processesNumber++;
        Random random = new Random();
        _siteNumber = random.nextInt(_maxSiteNumber) + 1;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public boolean equals(Object o){
        if(o == this) return true;
        if (!(o instanceof Proces) || o.getClass() != getClass()) return false;
        return ((Proces) o).getSiteNumber() == getSiteNumber();
    }

    @Override
    public String toString(){
        return _siteNumber+ " ";
    }

    public int getSiteNumber() {
        return _siteNumber;
    }
}