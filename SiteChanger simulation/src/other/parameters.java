package other;

public enum parameters {
    NUMBER_OF_PROCESSES(50000),
    NEW_PROCES_PROPABILITY(100),
    MAX_SITE_NUMBER(8),
    LOOP_SLEEP(1),
    TIME_STAMP(1),
    DELAY_AFTER_FIRST_PROCES_MADE(2000),
    CAPACITY(5);
    private final int value;

    parameters(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}
