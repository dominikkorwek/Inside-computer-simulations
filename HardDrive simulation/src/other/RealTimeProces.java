//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package other;

import java.util.Random;

public class RealTimeProces extends Proces {
    private final int minDemandTime = parameters.MIN_DEMAND_TIME.getValue();
    private final int maxDemandTime = parameters.MAX_DEMAND_TIME.getValue();
    public int demandTime;
    public boolean starving;

    public RealTimeProces() {
        Random random = new Random();

        starving = false;
        demandTime = random.nextInt(maxDemandTime - minDemandTime) + minDemandTime;
    }

    public boolean update(int stamp) {
        demandTime -= stamp;
        return demandTime > 0;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String toString() {
        String var10000 = super.toString();
        return var10000 + " d:" + demandTime + " " + starving;
    }
}
