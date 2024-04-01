//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package other;

public enum parameters {
    NUMBER_OF_PROCESSES(500),
    NEW_PROCES_SPAWN_RATE(1),
    MIN_DEMAND_TIME(90),
    MAX_DEMAND_TIME(100),
    LOOP_SLEEP(1),
    TIME_STAMP(1),
    CAPACITY(300),
    EDForSCAN(1),
    NEW_REALTIME_PROCES_PROPABILITY(10);

    private final int value;

    parameters(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
