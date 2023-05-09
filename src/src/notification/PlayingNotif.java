package notification;

import java.awt.*;

public class PlayingNotif implements NotifPattern {
    private Image image;
    private int time;
    private Toolkit t = Toolkit.getDefaultToolkit();

    public PlayingNotif(int type) {
        switch (type) {
            case 0:
                importImg(0);
                time = 4;
                break;
            case 1:
                break;
            case 2:
                break;
            default:
                System.out.println("undefined notif type!");
                break;
        }
    }

    @Override
    public void importImg(int type) {
        try {
            switch (type) {
                case 0:
                    image = t.getImage(getClass().getResource("/notification/stage_clear.png"));
                    break;
                case 1:
                    break;
                case 2:
                    break;
                default:
                    System.out.println("undefined notif type!");
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("ERROR - PlayingNotif - importImg");
        }
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public int timeNotif() {
        return time;
    }
}
