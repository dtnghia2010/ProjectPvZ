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
}
