package Timer;

public class timeCDWave extends timeLogic {
    private boolean endCDWave = false;
    public timeCDWave(int tickLimit, int type) {
        super(tickLimit, type);
    }

    @Override
    public void refresh() {
        if(getTick() < getTickLimit()) {
            setTick(getTick()+1);
        }
        if(getTick() == time) {
            aSec = true;
        } else if (getTick() != time) {
            aSec = false;
        }
        if(getTick() % time == 0 && getTick() != 0) {
            setCurrentSec(getCurrentSec()+1);
        }
    }

    @Override
    public void resetTime() {
        resetCurrentSec();
        setEndCDWave(false);
        setTick(0);
    }

    public boolean isEndCDWave() {
        return endCDWave;
    }

    public void setEndCDWave(boolean endCDWave) {
        this.endCDWave = endCDWave;
    }
}
