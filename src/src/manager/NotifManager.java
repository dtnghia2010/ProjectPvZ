package manager;

import Timer.timeLogic;
import notification.NotifPattern;

import java.awt.*;

public class NotifManager extends timeLogic {
    private NotifPattern notif;
    private boolean executed = false;
    public NotifManager() {
        super();
    }
    public void setNotif(NotifPattern notif) {
        this.notif = notif;
        setTickLimit(notif.timeNotif());
    }

    @Override
    public void updates() {
        if(getTickLimit() >= 0) {
            executed = false;
            decreaseTickLimit();
            if(getTickLimit() <= 0) {
                executed = true;
            }
        }
    }

    /*    public void updates() {
            if(tickLimit >= 0) {
                executed = false;
                tickLimit--;
                if(tickLimit <= 0) {
                    executed = true;
                }
            }
        }*/
    //draw
    public void showNotif(Graphics g) {
        updates();
        if(isTime() && !executed) {
            stageClear(g);
        }
    }
    public void stageClear(Graphics g) {
        g.drawImage(notif.getImage(),1024/2-200, 625/2-200, 400, 400, null);
    }


}
