package manager;

import Audio.Audio;
import scenes.Playing;

import java.awt.*;
import java.awt.event.KeyEvent;

public class KeyBoardManager {
    private Playing playing;
    private int plantPickedByKeyBoard = 0;
    private boolean isKeyPressForTheFirstTime = true;
    private boolean isPlantBeingForbidden = false;
    private int tileSelectedByKeyBoard = 0;
    public boolean isPlantBeingForbidden() {
        return isPlantBeingForbidden;
    }

    public void setPlantBeingForbidden(boolean plantBeingForbidden) {
        isPlantBeingForbidden = plantBeingForbidden;
    }

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
                    playing.getPlantManager().setForbidden(false);
                    playing.getBarManager().sunFlower();
                } else {
                    playing.getPlantManager().plantForbiddenFromStart();
                }
                break;
            case 1:
                playing.getPlantManager().setForbidden(false);
                playing.getBarManager().peaShooter();
                break;
            case 2:
                playing.getPlantManager().setForbidden(false);
                playing.getBarManager().wall_nut();
                break;
            case 3:
                playing.getPlantManager().setForbidden(false);
                playing.getBarManager().shadowPea();
                break;
            case 4:
                playing.getPlantManager().setForbidden(false);
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

    public void setTileSelectedByKeyBoard(int tileSelectedByKeyBoard) {
        this.tileSelectedByKeyBoard = tileSelectedByKeyBoard;
    }

    public void keyBoardSelectPlant(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            playing.getMouseMotionManager().setControlledByMouse(false);
            if(!playing.getPlantManager().isForbidden()){
                Audio.tapPlantBar();
            } else {
                Audio.plantNotAvailable();
            }
        }
    }
    public int getTileSelectedByKeyBoard() {
        return tileSelectedByKeyBoard;
    }

    public void tileSelectedByKeyBoard(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_A){
            tileSelectedByKeyBoard--;
            playing.getMouseMotionManager().setTileSelectedByMouse(tileSelectedByKeyBoard);
            playing.getTileManager().setInTile(true);
        } else if(e.getKeyCode() == KeyEvent.VK_D){
            tileSelectedByKeyBoard++;
            playing.getMouseMotionManager().setTileSelectedByMouse(tileSelectedByKeyBoard);
            playing.getTileManager().setInTile(true);
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            tileSelectedByKeyBoard = tileSelectedByKeyBoard -9;
            playing.getMouseMotionManager().setTileSelectedByMouse(tileSelectedByKeyBoard);
            playing.getTileManager().setInTile(true);
        } else if(e.getKeyCode() == KeyEvent.VK_S){
            tileSelectedByKeyBoard = tileSelectedByKeyBoard +9;
            playing.getMouseMotionManager().setTileSelectedByMouse(tileSelectedByKeyBoard);
            playing.getTileManager().setInTile(true);
        }
        if(tileSelectedByKeyBoard <0){
            tileSelectedByKeyBoard = tileSelectedByKeyBoard +9;
            playing.getMouseMotionManager().setTileSelectedByMouse(tileSelectedByKeyBoard);
        } else if(tileSelectedByKeyBoard > 44){
            tileSelectedByKeyBoard = tileSelectedByKeyBoard -9;
            playing.getMouseMotionManager().setTileSelectedByMouse(tileSelectedByKeyBoard);
        }
        if(playing.getPlantManager().isSelected()){
            plant(e);
        }
    }
    public void plant(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(playing.getPlantManager().isTimeToPlant()){
                playing.getPlantManager().setTimeToPlant(false);
            } else {
                playing.getPlantManager().plantCreateByKeyBoard(tileSelectedByKeyBoard);
            }
        }
    }
    public void startGame(KeyEvent e){
        if(!playing.isStartWaveForCD()){
            if(e.getKeyCode() == KeyEvent.VK_SPACE){
                playing.setStartWave(true);
                playing.setStartWaveForCD(true);
                pickPlantByKeyBoard();
                playing.getWaveManager().readyNewWave();
                playing.getPlantManager().setForbidden(false);
            }
        }
    }
}