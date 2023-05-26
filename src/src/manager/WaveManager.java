package manager;

import Timer.timeLogic;
import Timer.timeZombie;
import event.Wave;
import scenes.Playing;

public class WaveManager {
    private Playing playing;
    private Wave[] waves;
    private boolean endWaves = false, hordeActive = false;
    private int curZom = 0, curWave = -1;
    private int waveNum = 4, hordeNum = 30, coolDownWave = 10;
    private timeZombie zomSpawnTime;
    private static WaveManager instance = null;

    private WaveManager(Playing playing) {
        zomSpawnTime = new timeZombie(1,0);
        this.playing = playing;
        waves = new Wave[waveNum];
        initWaves();
    }

    public static WaveManager createWaveManager(Playing playing) {
        if(instance == null) {
            instance = new WaveManager(playing);
            System.out.println("You've created a WaveManager");
        } else {
            System.out.println("Cannot create another WaveManager");
        }
        return instance;
    }

    private void initWaves() {
        waves[0] = new Wave(5,5,5);
        waves[1] = new Wave(10,10,10);
        waves[2] = new Wave(15,15,15);
        waves[3] = new Wave(20,20,20);
    }

    public void readyNewWave() {
        if(curWave <= 0) {
            curWave++;
        }
        if(curWave < waves.length-1) {
            if(curZom > 2) {
                curWave++;
                curZom = 0;
            }
        } else {
            endWaves = true;
        }
    }

    public int getNextZombie() {
//        zombieSpawnTick = 0;
        zomSpawnTime.resetTime();
        if (waves[curWave].amountType(curZom) > 0) {
            waves[curWave].recudeWave(curZom);
            if(waves[curWave].amountType(curZom) == 0) {
                curZom++;
                return curZom-1;
            }
            return curZom;
        }
        return -1;
    }

    public boolean isTimeForNewZombie() {
        return zomSpawnTime.isTime();
    }
/*    public boolean isTimeForNewWave() {
        return waveTime.isTime();
    }*/
    public boolean isThereMoreZombieInWave() {
//        System.out.println("curWave:" +  curWave);
        if(curWave <= 0 || curWave >= waveNum) {

        } else {
            return waves[curWave].amountType(curZom) > 0;
        }
        return false;
    }

    public void updates() {
        zomSpawnTime.refresh();
        if(playing.getZombieManager().allZombieDead() && !isThereMoreZombieInWave()) {
            hordeActive = false;
        }
    }
    public boolean isEndWaves() {
        return endWaves;
    }

    public boolean hordeDead() {
        return !hordeActive;
    }

    public void createHorde() {
        hordeActive = true;
        playing.getZombieManager().createHorde(hordeNum);
        hordeNum += 20;
    }
    public int getCurWave() {
//        System.out.println("curWave " + curWave);
        return curWave;
    }
    public int getCoolDownWave() {
        return coolDownWave;
    }
}
