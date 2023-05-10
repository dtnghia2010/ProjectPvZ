package manager;

import scenes.Playing;

import java.awt.*;

public class MouseMotionManager {
    private Playing playing;
    private int plantPickedByMouse = 0;

    public void setPlantPickedByMouse(int plantPickedByMouse) {
        this.plantPickedByMouse = plantPickedByMouse;
    }

    private boolean isMouseMoveForFirstTime = true;
    private boolean isControlledByMouse = false;
    public MouseMotionManager(Playing playing){
        this.playing = playing;
    }
    public boolean isControlledByMouse() {
        return isControlledByMouse;
    }

    public void setControlledByMouse(boolean controlledByMouse) {
        isControlledByMouse = controlledByMouse;
    }
    public void changeStatusToMouse(int x, int y, World w){
        Rectangle world = new Rectangle(w.getX(),w.getY(),w.getWidth(),w.getHeight());
        if(world.contains(x,y)){
            if(isMouseMoveForFirstTime){
                returnToSelectPlantByMouse();
                isMouseMoveForFirstTime = false;
            }
            isControlledByMouse = true;
        }
    }
    public void mouseTrackPlantBar(int x, int y){
        if(!playing.getPlantManager().isSelected()){
            for(int i = 0;i<playing.getBarManager().getPickPlant().length;i++){
                Rectangle r = playing.getBarManager().getPickPlant()[i].getBounds();
                if(r.contains(x,y)){
                    if(playing.getTileManager().isInTile()){
                        playing.getTileManager().setInTile(false);
                        playing.getPlantManager().setSelected(false);
                    }
                    plantPickedByMouse = i;
                    playing.getKeyBoardManager().setPlantPickedByKeyBoard(plantPickedByMouse);
                }
            }
        }
    }
    public void drawPlantSelectedByMouse(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(playing.getBarManager().getPickedPlant(), (int)playing.getBarManager().getPickPlant()[plantPickedByMouse].getBounds().getX(),(int)playing.getBarManager().getPickPlant()[plantPickedByMouse].getBounds().getY(),(int)playing.getBarManager().getPickPlant()[plantPickedByMouse].getBounds().getWidth(),(int)playing.getBarManager().getPickPlant()[plantPickedByMouse].getBounds().getHeight(),null);
    }
    public void returnToSelectPlantByMouse(){
        playing.getPlantManager().setSelected(false);
        playing.getPlantManager().setTimeToPlant(true);
        playing.getBarManager().setPlantLocked(false);
    }
}
