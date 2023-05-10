package manager;

import Audio.Audio;
import scenes.Playing;

import java.awt.*;
import java.awt.event.KeyEvent;

public class KeyBoardManager {
    private Playing playing;
    private int plantPickedByKeyBoard = 0;
    private boolean isKeyPressForTheFirstTime = true;
    public KeyBoardManager(Playing playing){
        this.playing = playing;
    }

    public void setPlantPickedByKeyBoard(int plantPickedByKeyBoard) {
        this.plantPickedByKeyBoard = plantPickedByKeyBoard;
    }
    public void changeStatusToKeyBoard(KeyEvent e){
        if(isKeyPressForTheFirstTime && playing.getMouseMotionManager().isControlledByMouse()){
            playing.getPlantManager().setSelected(true);
            playing.getPlantManager().setTimeToPlant(false);
            isKeyPressForTheFirstTime = false;
        }
        if(playing.isStartWaveForCD() && playing.getMouseMotionManager().isControlledByMouse()){
            playing.getPlantManager().setSelected(true);
            playing.getPlantManager().setTimeToPlant(false);
        }
        playing.getMouseMotionManager().setControlledByMouse(false);
    }

    public void pickPlantByKeyBoard(){
        switch (plantPickedByKeyBoard){
            case 0:
                if(playing.isStartWaveForCD()){
                    playing.getBarManager().sunFlower();
                }
                break;
            case 1:
                playing.getBarManager().peaShooter();
                break;
            case 2:
                playing.getBarManager().wall_nut();
                break;
            case 3:
                playing.getBarManager().shadowPea();
                break;
            case 4:
                playing.getBarManager().cherryBomb();
                break;
        }
    }
    public void keyBoardChoosePlant(KeyEvent e){
        if(plantPickedByKeyBoard > 0 && plantPickedByKeyBoard <4){
            if(e.getKeyCode() == KeyEvent.VK_LEFT){
                plantPickedByKeyBoard--;
                playing.getMouseMotionManager().setPlantPickedByMouse(plantPickedByKeyBoard);
            } else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                plantPickedByKeyBoard++;
                playing.getMouseMotionManager().setPlantPickedByMouse(plantPickedByKeyBoard);
            }
        } else if(plantPickedByKeyBoard==0){
            if(e.getKeyCode() == KeyEvent.VK_LEFT){
                plantPickedByKeyBoard = 4;
                playing.getMouseMotionManager().setPlantPickedByMouse(plantPickedByKeyBoard);
            } else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                plantPickedByKeyBoard++;
                playing.getMouseMotionManager().setPlantPickedByMouse(plantPickedByKeyBoard);
            }
        } else if (plantPickedByKeyBoard==4) {
            if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                plantPickedByKeyBoard = 0;
                playing.getMouseMotionManager().setPlantPickedByMouse(plantPickedByKeyBoard);
            } else if(e.getKeyCode() == KeyEvent.VK_LEFT){
                plantPickedByKeyBoard--;
                playing.getMouseMotionManager().setPlantPickedByMouse(plantPickedByKeyBoard);
            }
        }
        pickPlantByKeyBoard();
    }
    public void drawPlantSelectedByKeyBoard(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(playing.getBarManager().getPickedPlant(), (int)playing.getBarManager().getPickPlant()[plantPickedByKeyBoard].getBounds().getX(),(int)playing.getBarManager().getPickPlant()[plantPickedByKeyBoard].getBounds().getY(),(int)playing.getBarManager().getPickPlant()[plantPickedByKeyBoard].getBounds().getWidth(),(int)playing.getBarManager().getPickPlant()[plantPickedByKeyBoard].getBounds().getHeight(),null);
    }
    public void keyBoardSelectPlant(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            playing.getMouseMotionManager().setControlledByMouse(false);
            playing.getPlantManager().setSelected(true);
            Audio.tapPlantBar();
        }
    }
    public void returnToSelectPlantByKeyBoard(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_SHIFT){
            playing.getPlantManager().setSelected(false);
            playing.getPlantManager().setTimeToPlant(true);
            playing.getBarManager().setPlantLocked(false);
        }
    }
    public void startGame(KeyEvent e){
        if(!playing.isStartWaveForCD()){
            if(e.getKeyCode() == KeyEvent.VK_SPACE){
                playing.setStartWave(true);
                playing.setStartWaveForCD(true);
                playing.getWaveManager().readyNewWave();
            }
        }
    }
}
