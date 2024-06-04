package other;

public enum parameters {
    NUMBER_OF_PROCESORS(80), //N
    NUMBER_OF_PROCESES(100000),
    MAX_PROCESOR_LOAD(70), //p
    MIN_PROCESOR_LOAD(20), //r
    TRY_TO_FIND_OTHER_PROCESOR(50), //z
    MAX_FREQUENCY(122),
    MIN_FREQUENCY(5),
    MAX_HANDLE_PROCES_TIME(100),
    MIN_HANDLE_PROCES_TIME(80),
    TIME_TO_HANDLE_PER_LOOP(86),
    MAX_PROCES_POWER(23),
    MIN_PROCES_POWER(5),
    INDEX_TO_START_COUNTING_LOAD(1000),
    TIME_IN_ONE_LOOP(100);
    private final int value;

    parameters(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}