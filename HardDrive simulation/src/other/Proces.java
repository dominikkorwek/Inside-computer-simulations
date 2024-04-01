//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package other;

@SuppressWarnings("rawtypes")
public class Proces implements Cloneable, Comparable {
    public static int processesNumber = 1;
    private final int _number;
    public int _birthTime;
    private int _waitingTime;   //niepotrzebne
    public int space;

    public Proces() {
        _number = processesNumber++;
        _waitingTime = 0;
    }

    public void endProces(int timeStamp) {
        _waitingTime = timeStamp - _birthTime;
    }

    public String toString() {
        return _number + ". n: " + space;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public int compareTo(Object o) {
        return Integer.compare(space, ((Proces)o).space);
    }
}
