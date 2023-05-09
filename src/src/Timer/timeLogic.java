package Timer;

public class timeLogic {
    private int tickLimit;
    private int tick;
    public timeLogic(int tickLimit) {
        setTickLimit(tickLimit);
    }
    private void setTickLimit(int tickLimit) {
        this.tickLimit = tickLimit*60;
        this.tick = this.tickLimit;
    }
    public void updates() {
        if(tick < tickLimit) {
            tick++;
//            System.out.println(tick);
        }
    }
    public boolean isTime() {
        return tick >= tickLimit;
    }
    public void resetTime() {
        tick = 0;
    }
    public int getTickLimit() {
        return tickLimit;
    }
    public int getTick() {
        return tick;
    }
    public void decreaseTickLimit() {
        tickLimit--;
    }
    public void decreaseTick() {
        tick--;
    }
    public void increaseTick() {tick++;}
    public void setTick(int newTick) {
        this.tick = newTick;
    }
}
