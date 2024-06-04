package other;

public enum parameters {
    NUMBER_OF_FRAME(50000),
    NEW_SEQUENCE_PROPABILITY(100),
    MAX_FRAME_NUMBER(8),
    TIME_STAMP(1),
    DELAY_AFTER_FIRST_FRAME_MADE(500),
    CAPACITY(5),
    LOCAL_PROPABILITY(5);
    private final int value;

    parameters(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}
