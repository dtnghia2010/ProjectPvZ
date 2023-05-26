package Audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.net.URL;

public class Audio {
    private static Clip[] clips = new Clip[16];
    static URL soundURL[] = new URL[16];

    static {
        try {
            soundURL[0] = Audio.class.getResource("/Audio/Roof.wav");
            soundURL[1] = Audio.class.getResource("/Audio/splat.wav");
            soundURL[2] = Audio.class.getResource("/Audio/tapPlantBar.wav");
            soundURL[3] = Audio.class.getResource("/Audio/tapGrass.wav");
            soundURL[4] = Audio.class.getResource("/Audio/readysetplant.wav");
            soundURL[5] = Audio.class.getResource("/Audio/ZombieEat.wav");
            soundURL[6] = Audio.class.getResource("/Audio/PlantDeath.wav");
            soundURL[7] = Audio.class.getResource("/Audio/Cherry_enlarge.wav");
            soundURL[8] = Audio.class.getResource("/Audio/Explode.wav");
            soundURL[9] = Audio.class.getResource("/Audio/plantNotAvailable.wav");
            soundURL[10] = Audio.class.getResource("/Audio/sunCollected.wav");
            soundURL[11] = Audio.class.getResource("/Audio/Groaning.wav");
            soundURL[12] = Audio.class.getResource("/Audio/Win.wav");
            soundURL[13] = Audio.class.getResource("/Audio/Lose.wav");
            soundURL[14] = Audio.class.getResource("/Audio/Menu.wav");
            soundURL[15] = Audio.class.getResource("/Audio/Setting.wav");
        } catch(Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot open audio!"); //show error dialog
        }
    }
    public static void inputAudio(int i){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[i]);
            clips[i] = AudioSystem.getClip();
            clips[i].open(audioInputStream);
        } catch (Exception e){

        }
    }
    public static void roofStage(){
        inputAudio(0);
        clips[0].start();
        clips[0].loop(Clip.LOOP_CONTINUOUSLY);
    }
    public static void stopRoof(){
        if(clips[0] != null){
            clips[0].stop();
        }
    }
    public static void splat() {
        inputAudio(1);
        clips[1].setFramePosition(0);
        clips[1].start();
    }
    public static void tapPlantBar(){
        inputAudio(2);
        clips[2].setFramePosition(0);
        clips[2].start();
    }
    public static void tapGrass(){
        inputAudio(3);
        clips[3].setFramePosition(0);
        clips[3].start();
    }
    public static void readySetPlant(){
        inputAudio(4);
        clips[4].setFramePosition(0);
        clips[4].start();
    }
    public static void stopReadySetPlant(){
        if(clips[4] != null){
            clips[4].stop();
        }
    }
    public static void zombieEat(){
        inputAudio(5);
        clips[5].setFramePosition(0);
        clips[5].start();
    }
    public static void zombieDeath(){
    }
    public static void plantDeath(){
        inputAudio(6);
        clips[6].setFramePosition(0);
        clips[6].start();
    }
    public static void prepareToExplode(){
        inputAudio(7);
        clips[7].setFramePosition(0);
        clips[7].start();
    }
    public static void Explode(){
        inputAudio(7);
        clips[7].stop();
        inputAudio(8);
        clips[8].setFramePosition(0);
        clips[8].start();
    }
    public static void plantNotAvailable(){
        inputAudio(9);
        clips[9].setFramePosition(0);
        clips[9].start();
    }
    public static void sunCollected(){
        inputAudio(10);
        clips[10].setFramePosition(0);
        clips[10].start();
    }
    public static void zombieGroaning() {
        inputAudio(11);
        clips[11].setFramePosition(0);
        clips[11].start();
    }

    public static void win(){
        inputAudio(12);
        clips[12].setFramePosition(0);
        clips[12].start();
    }
    public static void stopWin(){
        if(clips[12] != null){
            clips[12].stop();
        }
    }
    public static void lose(){
        inputAudio(13);
        clips[13].setFramePosition(0);
        clips[13].start();
    }
    public static void stopLose(){
        if(clips[13] != null){
            clips[13].stop();
        }
    }
    public static void menu(){
        inputAudio(14);
        clips[14].setFramePosition(0);
        clips[14].start();
    }
    public static void stopMenu(){
        if(clips[14] != null){
            clips[14].stop();
        }
    }
    public static void setting(){
        inputAudio(15);
        clips[15].setFramePosition(0);
        clips[15].start();
    }
    public static void stopSetting(){
        if(clips[15] != null){
            clips[15].stop();
        }
    }
}
