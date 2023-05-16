package Audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.net.URL;

public class Audio {
    private static Clip clip;
    static URL soundURL[] = new URL[12];

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
        } catch(Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot open audio!"); //show error dialog
        }
    }
    public static void inputAudio(int i){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e){

        }
    }
    public static void roofStage(){
        inputAudio(0);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public static void stopAudio(){
        inputAudio(0);
        clip.stop();
    }
    public static void splat() {
        inputAudio(1);
        clip.setFramePosition(0);
        clip.start();
    }
    public static void tapPlantBar(){
        inputAudio(2);
        clip.setFramePosition(0);
        clip.start();
    }
    public static void tapGrass(){
        inputAudio(3);
        clip.setFramePosition(0);
        clip.start();
    }
    public static void readySetPlant(){
        inputAudio(4);
        clip.setFramePosition(0);
        clip.start();
    }
    public static void zombieEat(){
        inputAudio(5);
        clip.setFramePosition(0);
        clip.start();
    }
    public static void zombieDeath(){

    }
    public static void plantDeath(){
        inputAudio(6);
        clip.setFramePosition(0);
        clip.start();
    }
    public static void prepareToExplode(){
        inputAudio(7);
        clip.setFramePosition(0);
        clip.start();
    }
    public static void Explode(){
        inputAudio(7);
        clip.stop();
        inputAudio(8);
        clip.setFramePosition(0);
        clip.start();
    }
    public static void plantNotAvailable(){
        inputAudio(9);
        clip.setFramePosition(0);
        clip.start();
    }
    public static void sunCollected(){
        inputAudio(10);
        clip.setFramePosition(0);
        clip.start();
    }
    public static void zombieGroaning() {
        inputAudio(11);
        clip.setFramePosition(0);
        clip.start();
    }

}
