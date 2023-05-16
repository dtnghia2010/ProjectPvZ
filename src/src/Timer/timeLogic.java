package Timer;

public class timeLogic {
    private int tickLimit;
    private int tick;
    private int currentSec = 0;
    public boolean aSec = false;
    public int type, time;
    public timeLogic(int tickLimit, int type) {
        this.type = type;
        switch (type) {
            case 0:
                time = 60;
                break;
            case 1:
                time = 60;
                break;
        }
        setTickLimit(tickLimit);
    }
    private void setTickLimit(int tickLimit) {
        this.tickLimit = tickLimit*time;
        this.tick = this.tickLimit;
    }
    public void refresh() {
        if(tick < tickLimit) {
            tick++;
//            System.out.println(tick);
        }
        if(tick == time) {
            aSec = true;
        } else if (tick != time) {
            aSec = false;
        }
        if(tick % time == 0 && tick != 0) {
            currentSec++;
        }
    }
    public boolean isTime() {
        return tick >= tickLimit;
    }
    public void resetTime() {
        resetCurrentSec();
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
    public int getCurrentSec() {
        return currentSec;
    }
    public void resetCurrentSec() {
        System.out.println("reset Current Second");
        currentSec = 0;
    }
}
