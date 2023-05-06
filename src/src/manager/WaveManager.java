package manager;

import Timer.timeLogic;
import event.Wave;
import scenes.Playing;

public class WaveManager extends timeLogic {
    private Playing playing;
    private Wave[] waves;
    private boolean endWaves = false, hordeActive = true;
/*    private int zombieSpawnTickLimit = 60;
    private int zombieSpawnTick = zombieSpawnTickLimit;*/
    private int curZom = 0, curWave = 0;
    private int waveNum = 4, hordeNum = 10;
    public WaveManager(Playing playing) {
        super();
        setTickLimit(1);
        this.playing = playing;
        waves = new Wave[waveNum];
        initWaves();
    }

    private void initWaves() {
        waves[0] = new Wave(2,2,1);
        waves[1] = new Wave(4,3,2);
        waves[2] = new Wave(6,4,3);
        waves[3] = new Wave(5,5,6);
    }

    public void readyNewWave() {
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
        resetTime();
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

/*    public boolean isTimeForNewZombie() {
        return zombieSpawnTick >= zombieSpawnTickLimit;
    }*/

    public boolean isThereMoreZombieInWave() {
//        System.out.println("curWave:" +  curWave);
        return waves[curWave].amountType(curZom) > 0;
    }

    @Override
    public void updates() {
        super.updates();
        if(hordeActive == true && !isThereMoreZombieInWave()) {
            hordeActive = false;
            System.out.println("execute");
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
        hordeNum += 5;
    }
}
