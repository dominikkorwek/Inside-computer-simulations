//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package other;

import java.util.Random;

public class RealTimeProces extends Proces {
    private final int _minDemandTime;
    private final int _maxDemandTime;
    private int _demandTime;
    public boolean starving;

    public RealTimeProces() {
        Random random = new Random();

        starving = false;
        _minDemandTime = parameters.MIN_DEMAND_TIME_FOR_REALTIME.getValue();
        _maxDemandTime = parameters.MAX_DEMAND_TIME_FOR_REALTIME.getValue();
        _demandTime = random.nextInt(_maxDemandTime - _minDemandTime) + _minDemandTime;
    }

    public boolean update(int stamp) {
        _demandTime -= stamp;
        return _demandTime > 0;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String toString() {
        String var = super.toString();
        return var + " d:" + _demandTime + " " + starving;
    }

    public int getDemandTime() {
        return _demandTime;
    }
}
