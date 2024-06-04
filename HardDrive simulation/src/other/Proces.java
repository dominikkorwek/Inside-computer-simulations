//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package other;

@SuppressWarnings("rawtypes")
public class Proces implements Cloneable, Comparable {
    public static int amountOfProceses = 1;
    private final int _number;
    public int _birthTime;
    private int _waitingTime;
    public int space;

    public Proces() {
        _number = amountOfProceses++;
        _waitingTime = 0;
    }

    public void endProces(int timeStamp) {
        _waitingTime = timeStamp - _birthTime;
    }
    @Override
    public String toString() {
        return _number + ". n: " + space;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public int getWaitingTime() {
        return _waitingTime;
    }
    @Override
    public int compareTo(Object o) {
        return Integer.compare(space, ((Proces)o).space);
    }
}
