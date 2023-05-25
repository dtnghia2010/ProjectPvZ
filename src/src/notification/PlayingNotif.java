package notification;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class PlayingNotif implements NotifPattern {
    private Image image;
    private Image[] CDSkull = new Image[259];
    private int time;
/*    private int countCDSkull = 0;
    private int transCDSkull = 0;
    private boolean notifTest = false;*/
    private Toolkit t = Toolkit.getDefaultToolkit();
    private int type;

    public PlayingNotif(int type, int time) {
        this.type = type;
        switch (this.type) {
            case 0:
                //clear stage notif
                importImg(type);
                this.time = time;
                break;
            case 1:
                //count down wave notif
//                importAnimation(type);
                this.time = time; //won't need time for NotifManager --> already have one in waveManager
                break;
            case 2:
                //horde appear
                break;
            default:
                System.out.println("undefined notif type!");
                break;
        }
    }

    @Override
    public int timeNotif() {
        return time;
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

/*    public void refresh() {
//        System.out.println("1");
        countCDSkull++;
        if(countCDSkull%10 == 0) {
            transCDSkull++;
        }
        if(transCDSkull == 259) {
            reset();
        }
    }*/

/*    @Override
    public void importAnimation(int type) {
        try {
            switch (type) {
                case 0:
                    break;
                case 1:
                    importCDSkull();
                    break;
                case 2:
                    break;
                default:
                    System.out.println("underfined notif type!");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("ERROR - PlayingNotif - importAnimation");
        }
    }*/

/*    public void importCDSkull() {
        for(int i = 1; i <= 259; i++) {
            CDSkull[i-1] = t.getImage(getClass().getResource("/animation/countDownWave/countDownWave (" + i + ").png"));
        }
    }*/

    @Override
    public Image getImage() {
        return image;
    }

/*    public Image[] getCDSkull() {
        return CDSkull;
    }*/

/*
    public int getTransition() {
        switch (type) {
            case 0:
                break;
            case 1:
                return getTransCDSkull();
            case 2:
                break;
            default:
                System.err.println("Undefined type!");
                break;
        }
        return -1;
    }

    @Override
    public Image[] getAnimation() {
        switch (type) {
            case 0:
                break;
            case 1:
                return getCDSkull();
            case 2:
                break;
        }
        return null;
    }
*/
/*
    public int getTransCDSkull() {
        return transCDSkull;
    }*/

/*
    public Image currentAnimationFrame() {
        switch (type) {
            case 0:
                break;
            case 1:
                return CDSkull[transCDSkull];
            case 2:
                break;
        }
        return null;
    }

    public boolean isNotifTest() {
        return notifTest;
    }

    @Override
    public void reset() {
        transCDSkull = 0;
        countCDSkull = 0;
        notifTest = true;
    }
*/


}
