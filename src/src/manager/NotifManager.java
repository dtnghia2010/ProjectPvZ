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
    private boolean executed = false, endCDWave = false;
    private Playing playing;
    private timeStage clearStageTime;
    private timeCDWave waveCDTime;
    public NotifManager(Playing playing) {
        notifs = new NotifPattern[2];
        this.playing = playing;
        setUpNotif();
//        setNotif(new PlayingNotif(0));
//        setNotif(new PlayingNotif(1));

    }

    public void setUpNotif() {
        notifs[0] = new PlayingNotif(0, 4);
        clearStageTime = new timeStage(this.notifs[0].timeNotif(), 1);
        notifs[1] = new PlayingNotif(1, playing.getWaveManager().getCoolDownWave());
        waveCDTime = new timeCDWave(this.notifs[1].timeNotif(), 1) {
        };
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
        /// TODO: draw count down wave
        stageCurrent(g); //wave current notif
    }

    public void countWave(Graphics g) {
        Font font = new Font("Arial", Font.BOLD, 20);
        g.setFont(font);
        g.setColor(Color.RED);
        int time = playing.getWaveManager().getCoolDownWave() - waveCDTime.getCurrentSec();
        g.drawString("Count down: " + time, 100, 620);
    }
    public void stageClear(Graphics g) {
        g.drawImage(notifs[0].getImage(), 1024 / 2 - 200, 625 / 2 - 200, 400, 400, null);
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
        clearStageTime.resetTime();
        waveCDTime.resetTime();
    }

    public timeCDWave getWaveCDTime() {
        return waveCDTime;
    }
}
