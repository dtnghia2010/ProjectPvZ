package notification;

import java.awt.*;

public class MenuNotif implements NotifPattern {
    private Image img;
    private Toolkit t = Toolkit.getDefaultToolkit();
    public MenuNotif() {
    }
    @Override
    public void importImg(int type) {
        System.out.println("no available notification for Menu");
    }

    @Override
    public Image getImage() {
        return null;
    }

    @Override
    public int timeNotif() {
        return 3;
    }

/*    @Override
    public void importAnimation(int type) {

    }*/

/*    @Override
    public void refresh() {

    }

    public int getTransition() {
        return 0;
    }

    public Image[] getAnimation() {
        return new Image[0];
    }

    @Override
    public Image currentAnimationFrame() {
        return null;
    }

    @Override
    public boolean isNotifTest() {
        return false;
    }

    @Override
    public void reset() {

    }*/
}
