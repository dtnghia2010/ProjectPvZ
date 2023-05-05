package manager;

import notification.NotifPattern;

import java.awt.*;

public class NotifManager {
    private NotifPattern notif;
    private int tickLimit;
    private boolean executed = false;
    public void setNotif(NotifPattern notif) {
        this.notif = notif;
        tickLimit = notif.timeNotif() * 60;
    }

    public void updates() {
        if(tickLimit >= 0) {
            executed = false;
            tickLimit--;
            if(tickLimit <= 0) {
                executed = true;
            }
        }
    }
    public boolean isTimeForNotif() {
        return tickLimit > 0;
    }
    //draw
    public void showNotif(Graphics g) {
        updates();
        if(isTimeForNotif() && !executed) {
            stageClear(g);
        }
    }
    public void stageClear(Graphics g) {
        g.drawImage(notif.getImage(),1024/2-200, 625/2-200, 400, 400, null);
    }


}
