package other;

public enum parameters {
    NUMBER_OF_PROCESSES(10000),
    MAX_PROCES_LENGTH(100),
    MIN_PROCES_LENGTH(20),
    NEW_PROCES_PROPABILITY(70),
    TIME_STAMP(100),
    TIME_SWAP_BETWEEN_PROCESSES(5),
    LOOP_SLEEP(1),
    STARVING_TIME(150);
    private final double value;

    parameters(double value){
        this.value = value;
    }
    public double getValue(){
        return value;
    }
}
