package notification;

import java.awt.*;

public interface NotifPattern {
    void importImg(int type);
    Image getImage();
    int timeNotif();
}
