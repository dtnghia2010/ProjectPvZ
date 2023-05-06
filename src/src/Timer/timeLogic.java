package Timer;

public abstract class timeLogic {
    private int tickLimit;
    private int tick;
    public timeLogic(int tickLimit) {
        this.tickLimit = tickLimit;
        this.tick = tickLimit;
    }
    public void updates() {
        if(tick < tickLimit) {
            tick++;
        }
    }

    public boolean isTime() {
        return tick >= tickLimit;
    }
    public void resetTime() {
        tick = 0;
    }
}
