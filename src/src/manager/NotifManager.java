package manager;

import Timer.timeCDWave;
import Timer.timeLogic;
import Timer.timeStage;
import notification.NotifPattern;
import notification.PlayingNotif;
import scenes.Playing;

import java.awt.*;

public class NotifManager {
    private NotifPattern[] notifs;
    private Playing playing;
    private timeStage clearStageTime;
    private timeCDWave waveCDTime;
    private static NotifManager instance = null;
    private Toolkit t = Toolkit.getDefaultToolkit();
    private Image noticeImg = t.getImage(getClass().getResource("/scene/NOTICE.png"));
    private NotifManager(Playing playing) {
        notifs = new NotifPattern[2];
        this.playing = playing;
        setUpNotif();
    }

    public static NotifManager createNotifManager(Playing playing) {
        if(instance == null) {
            instance = new NotifManager(playing);
        } else {
            System.out.println("Cannot create another NotifManager");
        }
        return instance;
    }

    public void setUpNotif() {
        notifs[0] = new PlayingNotif(0, 4);
        clearStageTime = new timeStage(this.notifs[0].timeNotif(), 1);
        notifs[1] = new PlayingNotif(1, playing.getWaveManager().getCoolDownWave());
        waveCDTime = new timeCDWave(this.notifs[1].timeNotif(), 1);
        waveCDTime.resetTime();
    }

    public void refresh() {
        clearStageTime.refresh();
    }

    //draw
    public void drawNotif(Graphics g) {
        if (!playing.isStartWave()) {
            if (!playing.getWaveManager().isThereMoreZombieInWave() && playing.getZombieManager().allZombieDead() && playing.isZombieApproaching()) {
                refresh();
                if(waveCDTime.isTime()) {
                    waveCDTime.setEndCDWave(true);
                } else {
                    waveCDTime.refresh();
                }
                countWave(g);
                if (!clearStageTime.isExecuted()) {
                    playing.setStartWaveForCD(false);
                    stageClear(g); //stage clear notif
                }
            }
        }
        stageCurrent(g); //wave current notif
    }

    public void countWave(Graphics g) {
//        g.setColor(Color.BLUE);
//        g.fillRect(77,580,167,35);
        g.drawImage(noticeImg, 77, 580, 190, 35, null);
        Font font = new Font("Arial", Font.BOLD, 22);
        g.setFont(font);
        g.setColor(Color.WHITE);
        int time = playing.getWaveManager().getCoolDownWave() - waveCDTime.getCurrentSec();
        g.drawString("Count down: " + time, 90, 604);
    }
    public void stageClear(Graphics g) {
        g.drawImage(notifs[0].getImage(), 1024 / 2 - 200, 625 / 2 - 200, 400, 400, null);
    }

    public void stageCurrent(Graphics g) {
//        g.setColor(Color.BLUE);
//        g.fillRect(873,580,120,35);
        g.drawImage(noticeImg, 873, 580, 120, 35, null);
        Font font = new Font("Arial", Font.BOLD, 22);
        g.setFont(font);
        g.setColor(Color.WHITE);
        int currentWave = playing.getWaveManager().getCurWave() + 1;
        int currWave = 0;
        Integer.toString(currentWave);
        if (currentWave < 10 && currentWave > 0) {
            currWave += currentWave;
        }
        g.drawString("Wave " + currWave, 897, 604);
    }

    public void reset() {
        clearStageTime.resetTime();
        waveCDTime.resetTime();
    }

    public timeCDWave getWaveCDTime() {
        return waveCDTime;
    }
}
