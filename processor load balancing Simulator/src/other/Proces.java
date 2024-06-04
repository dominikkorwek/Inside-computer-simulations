package other;

import java.util.Random;

public class Proces{
    private final int power;
    private int handleTime;

    public Proces() {
        Random random = new Random();

        int minPower = parameters.MIN_PROCES_POWER.getValue();
        int maxPower = parameters.MAX_PROCES_POWER.getValue();
        int maxTime = parameters.MAX_HANDLE_PROCES_TIME.getValue();
        int minTime = parameters.MIN_HANDLE_PROCES_TIME.getValue();

        power = random.nextInt(maxPower - minPower) + minPower;
        handleTime = random.nextInt(maxTime - minTime) + maxTime;
    }

    private Proces(int power, int handleTime){
        this.power = power;
        this.handleTime = handleTime;
    }

    public int getPower() {
        return power;
    }

    public int getHandleTime() {
        return handleTime;
    }
    public int handleTime(int time){
       handleTime -= time;

       if (handleTime > 0) return 0;
       else return handleTime * -1;

    }

    @Override
    public Proces clone() {
            return new Proces(power,handleTime);
    }
}