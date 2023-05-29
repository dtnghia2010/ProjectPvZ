package manager;

import Timer.timeLogic;
import Timer.timeZombie;
import event.Wave;
import scenes.Playing;

public class WaveManager {
    private Playing playing;
    private Wave[] waves;
    private boolean endWaves = false, hordeActive = false;
    private int curZom = 0, curWave = 0;
    private int waveNum = 4, hordeNum = 20, coolDownWave = 10;
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
        waves[0] = new Wave(0,0,0);
        waves[1] = new Wave(8,5,1);
        waves[2] = new Wave(10,8,2);
        waves[3] = new Wave(20,15,5);
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
        } else if (curWave == waves.length-1) {
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
        //ini hordeNum = 20
        hordeActive = true;
        playing.getZombieManager().createHorde(hordeNum);
        hordeNum += 15;
    }
    public int getCurWave() {

//        System.out.println("curWave " + curWave);
        return curWave;
    }
    public int getCoolDownWave() {
        return coolDownWave;
    }
}
