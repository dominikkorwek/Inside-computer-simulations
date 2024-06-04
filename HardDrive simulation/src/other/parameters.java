//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package other;

public enum parameters {
    NUMBER_OF_PROCESSES(500),
    NEW_PROCES_SPAWN_RATE(20),
    PIN_START_INDEX(50),
    MIN_DEMAND_TIME_FOR_REALTIME(90),
    MAX_DEMAND_TIME_FOR_REALTIME(100),
    TIME_STAMP(1),
    CAPACITY(300),
    SIMULATION_TYPE(2), //0 - random, 1 - spawn in one space, 2 - spawn in the corners,
    // 3 - spawn from left to right, 4 - spawn from left to right and then reversed
    NEW_REALTIME_PROCES_PROPABILITY(0);

    private final int value;

    parameters(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
