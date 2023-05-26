package Timer;

public abstract class timeLogic {
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
    public abstract void refresh();

    public abstract void resetTime();
    public boolean isTime() {
        return tick >= tickLimit;
    }

    public int getTickLimit() {
        return tickLimit;
    }
    public int getTick() {
        return tick;
    }
    public void decreaseTick() {
        tick--;
    }
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

    public void setCurrentSec(int currentSec) {
        this.currentSec = currentSec;
    }
}
