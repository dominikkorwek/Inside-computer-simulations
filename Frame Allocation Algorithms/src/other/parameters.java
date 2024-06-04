package other;

public enum parameters {
    NUMBER_OF_FRAMES(50000),
    NUMBER_OF_PROCESORS(7),
    MAX_FRAME_NUMBER(15),
    MIN_FRAME_NUMBER(4),
    MAX_FRAME_MAKE_PROPABILITY(90),
    MIN_FRAME_MAKE_PROPABILITY(40),
    TIMEFRAME(10),
    L(3),
    U(8),
    CAPACITY(30);


    private final int value;
    parameters(int value){
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}