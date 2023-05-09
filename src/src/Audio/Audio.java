package Audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.InputStream;

public class Audio {
    private static Clip[] clips = new Clip[11];
    static {
        try {
            for(int i = 0;i<10;i++){
                clips[i] = AudioSystem.getClip();
            }
            InputStream InputStream1 = Audio.class.getResourceAsStream("/Audio/Roof.wav");
            AudioInputStream audioInputStream1 = AudioSystem.getAudioInputStream(InputStream1);
            clips[0].open(audioInputStream1);
            InputStream InputStream2 = Audio.class.getResourceAsStream("/Audio/splat.wav");
            AudioInputStream audioInputStream2 = AudioSystem.getAudioInputStream(InputStream2);
            clips[1].open(audioInputStream2);
            InputStream InputStream3 = Audio.class.getResourceAsStream("/Audio/tapPlantBar.wav");
            AudioInputStream audioInputStream3 = AudioSystem.getAudioInputStream(InputStream3);
            clips[2].open(audioInputStream3);
            InputStream InputStream4 = Audio.class.getResourceAsStream("/Audio/tapGrass.wav");
            AudioInputStream audioInputStream4 = AudioSystem.getAudioInputStream(InputStream4);
            clips[3].open(audioInputStream4);
            InputStream InputStream5 = Audio.class.getResourceAsStream("/Audio/readysetplant.wav");
            AudioInputStream audioInputStream5 = AudioSystem.getAudioInputStream(InputStream5);
            clips[4].open(audioInputStream5);
            InputStream InputStream6 = Audio.class.getResourceAsStream("/Audio/ZombieEat.wav");
            AudioInputStream audioInputStream6 = AudioSystem.getAudioInputStream(InputStream6);
            clips[5].open(audioInputStream6);
            InputStream InputStream7 = Audio.class.getResourceAsStream("/Audio/PlantDeath.wav");
            AudioInputStream audioInputStream7 = AudioSystem.getAudioInputStream(InputStream7);
            clips[6].open(audioInputStream7);
            InputStream InputStream8 = Audio.class.getResourceAsStream("/Audio/Cherry_enlarge.wav");
            AudioInputStream audioInputStream8 = AudioSystem.getAudioInputStream(InputStream8);
            clips[7].open(audioInputStream8);
            InputStream InputStream9 = Audio.class.getResourceAsStream("/Audio/Explode.wav");
            AudioInputStream audioInputStream9 = AudioSystem.getAudioInputStream(InputStream9);
            clips[8].open(audioInputStream9);
            InputStream InputStream10 = Audio.class.getResourceAsStream("/Audio/plantNotAvailable.wav");
            AudioInputStream audioInputStream10 = AudioSystem.getAudioInputStream(InputStream10);
            clips[9].open(audioInputStream10);
        } catch(Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot open audio!"); //show error dialog
        }
    }
    public static void roofStage(){
        clips[0].start();
        clips[0].loop(Clip.LOOP_CONTINUOUSLY);
    }
    public static void splat() {
        if(clips[1] != null){
            clips[1].stop();
        }
        clips[1].setFramePosition(0);
        clips[1].start();
    }
    public static void tapPlantBar(){
        clips[2].setFramePosition(0);
        clips[2].start();
    }
    public static void tapGrass(){
        clips[3].setFramePosition(0);
        clips[3].start();
    }
    public static void readySetPlant(){
        clips[4].setFramePosition(0);
        clips[4].start();
    }
    public static void zombieEat(){
        clips[5].setFramePosition(0);
        clips[5].start();
    }
    public static void zombieDeath(){
        clips[5].stop();
    }
    public static void plantDeath(){
        clips[5].stop();
        clips[5].setFramePosition(0);
        clips[6].setFramePosition(0);
        clips[6].start();
    }
    public static void prepareToExplode(){
        clips[7].setFramePosition(0);
        clips[7].start();
    }
    public static void Explode(){
        clips[7].stop();
        clips[8].setFramePosition(0);
        clips[8].start();
    }
    public static void plantNotAvailable(){
        clips[9].setFramePosition(0);
        clips[9].start();
    }
}
