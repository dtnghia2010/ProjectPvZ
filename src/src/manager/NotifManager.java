package manager;

import Timer.timeLogic;
import notification.NotifPattern;
import scenes.Playing;

import java.awt.*;

public class NotifManager {
    private NotifPattern notif;
    private boolean executed = false;
    private Playing playing;
    private timeLogic clearStageTime;

    public NotifManager(Playing playing) {
        this.playing = playing;
    }

    public void setNotif(NotifPattern notif) {
        this.notif = notif;
        clearStageTime = new timeLogic(this.notif.timeNotif());
    }

    public void updates() {
        if (clearStageTime.getTickLimit() >= clearStageTime.getTick()) {
            executed = false;
            clearStageTime.decreaseTick();
            if (clearStageTime.getTick() <= 0) {
                executed = true;
            }
        }
    }

    //draw
    public void drawNotif(Graphics g) {
        if (!playing.isStartWave()) {

            if (!playing.getWaveManager().isThereMoreZombieInWave() && playing.getZombieManager().allZombieDead() && playing.isZombieApproaching()) {
                updates();
                if (!executed)
                    stageClear(g); //stage clear notif
            }
        }
        stageCurrent(g); //wave current notif
    }

    public void stageClear(Graphics g) {
        g.drawImage(notif.getImage(), 1024 / 2 - 200, 625 / 2 - 200, 400, 400, null);
    }

    public void stageCurrent(Graphics g) {
        Font font = new Font("Arial", Font.BOLD, 20);
        g.setFont(font);
        g.setColor(Color.RED);
        int currentWave = playing.getWaveManager().getCurWave() + 1;
        String currWave = "0";
        Integer.toString(currentWave);
        if (currentWave < 10 && currentWave > 0) {
            currWave += currentWave;
        }
        g.drawString("Wave " + currWave, 900, 620);
    }

    public void reset() {
        //stage clear notif
        clearStageTime.setTick(clearStageTime.getTickLimit());
    }
}
