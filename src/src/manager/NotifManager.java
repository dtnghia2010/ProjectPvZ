package manager;

import Timer.timeLogic;
import notification.NotifPattern;
import scenes.Playing;

import java.awt.*;

public class NotifManager extends timeLogic {
    private NotifPattern notif;
    private boolean executed = false;
    private Playing playing;
    public NotifManager(Playing playing) {
        super();
        this.playing = playing;
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
        if(!executed) {
            stageClear(g);
        }
        stageCurrent(g);
    }
    public void stageClear(Graphics g) {
        g.drawImage(notif.getImage(),1024/2-200, 625/2-200, 400, 400, null);
    }

    public void stageCurrent(Graphics g) {
        Font font  = new Font("Arial", Font.BOLD, 20);
        g.setFont(font);
        g.setColor(Color.RED);
        int currentWave = playing.getWaveManager().getCurWave()+1;
        String currWave = "0";
        Integer.toString(currentWave);
        if(currentWave < 10 && currentWave > 0) {
            currWave += currentWave;
        }
        g.drawString("Wave " + currWave , 900, 620);
    }
}
