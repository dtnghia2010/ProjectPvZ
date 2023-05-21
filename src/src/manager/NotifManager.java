package manager;

import Timer.timeLogic;
import notification.NotifPattern;
import notification.PlayingNotif;
import scenes.Playing;

import java.awt.*;

public class NotifManager {
    private NotifPattern[] notifs;
    private boolean executed = false, endCDWave = false;
    private Playing playing;
    private timeLogic clearStageTime, waveCDTime;
    private Toolkit t = Toolkit.getDefaultToolkit();
    private Image noticeImg = t.getImage(getClass().getResource("/scene/NOTICE.png"));
    public NotifManager(Playing playing) {
        notifs = new NotifPattern[2];
        this.playing = playing;
        setUpNotif();
//        setNotif(new PlayingNotif(0));
//        setNotif(new PlayingNotif(1));

    }

    public void setUpNotif() {
        notifs[0] = new PlayingNotif(0, 4);
        clearStageTime = new timeLogic(this.notifs[0].timeNotif(), 1);
        notifs[1] = new PlayingNotif(1, playing.getWaveManager().getCoolDownWave());
        waveCDTime = new timeLogic(this.notifs[1].timeNotif(), 1);
        waveCDTime.resetTime();
    }

/*    public void setNotif(NotifPattern notif) {
        this.notif = notif;
        clearStageTime = new timeLogic(this.notif.timeNotif());
    }*/

    public void refresh() {
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
                refresh();
//                System.out.println("out stage");
                if(waveCDTime.isTime()) {
                    endCDWave = true;
//                    System.out.println("reset current secccc");
//                    waveCDTime.resetCurrentSec();
//                    resetEndCDWave();
                } else {
                    waveCDTime.refresh();
                }
                countWave(g);
                if (!executed)
                    playing.setStartWaveForCD(false);
                    stageClear(g); //stage clear notif
            }
        }
        /// TODO: draw count down wave
        stageCurrent(g); //wave current notif
    }

    public void countWave(Graphics g) {
//        g.setColor(Color.BLUE);
//        g.fillRect(77,580,167,35);
        g.drawImage(noticeImg, 77, 580, 156, 35, null);
        Font font = new Font("PvZ2 Regular", Font.BOLD, 22);
        g.setFont(font);
        g.setColor(Color.WHITE);
        int time = playing.getWaveManager().getCoolDownWave() - waveCDTime.getCurrentSec();
        g.drawString("Count down: " + time, 100, 604);
    }
    public void stageClear(Graphics g) {
        g.drawImage(notifs[0].getImage(), 1024 / 2 - 200, 625 / 2 - 200, 400, 400, null);
    }

    public void stageCurrent(Graphics g) {
//        g.setColor(Color.BLUE);
//        g.fillRect(873,580,120,35);
        g.drawImage(noticeImg, 873, 580, 120, 35, null);
        Font font = new Font("PvZ2 Regular", Font.BOLD, 22);
        g.setFont(font);
        g.setColor(Color.WHITE);
        int currentWave = playing.getWaveManager().getCurWave() + 1;
        int currWave = 0;
        Integer.toString(currentWave);
        if (currentWave < 10 && currentWave > 0) {
            currWave += currentWave;
        }
        g.drawString("Wave " + currWave, 907, 604);
    }

    public void reset() {
        //stage clear notif
        clearStageTime.setTick(clearStageTime.getTickLimit());
    }
    public void resetEndCDWave() {
        endCDWave = false;
        waveCDTime.resetTime();
//        waveCDTime.resetCurrentSec();
        System.out.println("reset current second");
    }

    public boolean isEndCDWave() {
        return endCDWave;
    }
}
