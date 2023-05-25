package Timer;

public class timeStage extends timeLogic {
    private boolean executed = false;
    public timeStage(int tickLimit, int type) {
        super(tickLimit, type);
    }
    @Override
    public void refresh() {
        if (getTickLimit() >= getTick()) {
            executed = false;
            decreaseTick();
            if (getTick() <= 0) {
                executed = true;
            }
        }
    }

    @Override
    public void resetTime() {
        setTick(getTickLimit());
    }

    public boolean isExecuted() {
        return executed;
    }
}
