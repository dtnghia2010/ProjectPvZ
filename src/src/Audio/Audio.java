package Audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.InputStream;
import java.net.URL;

public class Audio {
    private static Clip clip;
    static URL soundURL[] = new URL[8];
    static {
        try {
            soundURL[0] = Audio.class.getResource("/Audio/music/Roof.wav"); //v
            soundURL[1] = Audio.class.getResource("/Audio/environment/splat.wav"); //v
            soundURL[2] = Audio.class.getResource("/Audio/environment/tapPlantBar.wav"); //v
            soundURL[3] = Audio.class.getResource("/Audio/environment/tapGrass.wav");
            soundURL[4] = Audio.class.getResource("/Audio/plant/readysetplant.wav");
            soundURL[5] = Audio.class.getResource("/Audio/zombie/ZombieEat.wav"); //v
            soundURL[6] = Audio.class.getResource("/Audio/plant/PlantDeath.wav");
            soundURL[7] = Audio.class.getResource("/Audio/zombie/Groaning.wav");
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
    public static void zombieGroaning() {
        inputAudio(7);
        clip.setFramePosition(0);
        clip.start();
    }

}