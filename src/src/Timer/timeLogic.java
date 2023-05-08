package Timer;

public abstract class timeLogic {
    private int tickLimit;
    private int tick;
    public timeLogic() {
    }
    public void setTickLimit(int tickLimit) {
        this.tickLimit = tickLimit*60;
        this.tick = this.tickLimit;
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
    public int getTickLimit() {
        return tickLimit;
    }
    public void decreaseTickLimit() {
        tickLimit--;
    }
    public void decreaseTick() {
        tick--;
    }
}
