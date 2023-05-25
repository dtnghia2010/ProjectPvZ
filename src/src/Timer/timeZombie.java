package Timer;

public class timeZombie extends timeLogic {
    public timeZombie(int tickLimit, int type) {
        super(tickLimit, type);
    }

    @Override
    public void refresh() {
        if(getTick() < getTickLimit()) {
            setTick(getTick()+1);
        }
    }

    @Override
    public void resetTime() {
        setTick(0);
    }
}
