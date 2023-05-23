package manager;

import Projectile.ProjectileLogic;
import Projectile.ProjectileOfHouseOwner;
import component.Tile;
import scenes.Playing;
import HouseOwner.HouseOwner;
import zombie.Zombie;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Random;
import javax.imageio.ImageIO;

public class HouseOwnerManager {
    private ProjectileOfHouseOwner projectileOfHouseOwner;
    private HouseOwner houseOwner;
    private  TileManager tileManager;
    private Image[] zImages;
    private Playing playing;
    private Toolkit t = Toolkit.getDefaultToolkit();
    private Random random = new Random();
    private static int realTimeCounter = 0;
    private static boolean isReset = false;
    private int counter = 0;
    private BufferedImage houseOwnerImage;
    private int x;
    private int y;
    private int speed;

    //public static int getRealTimeCounter() {
    //return realTimeCounter;

    public HouseOwnerManager(Playing playing) {
        this.playing = playing;
        houseOwner = new HouseOwner(260, 470, 100);
        importImg();
        projectileOfHouseOwner = new ProjectileOfHouseOwner();
    }

    public HouseOwnerManager(String imageUrl, int x, int y, int speed){
        try {
            URL url = new URL(imageUrl);
            this.houseOwnerImage = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.x = x;
        this.y = y;
        this.speed = speed;
//         projectileManager = new ProjectileLogic();
    }


    public void moveUp () {
        this.y -= this.speed;
    }

    public void moveDown () {
        this.y += this.speed;
    }

    public void importImg() {
        zImages = new Image[1];
        try {
            Image houseOwnerImg = ImageIO.read(getClass().getResource("/HouseOwner/HouseOwner.png"));
            zImages[0] = houseOwnerImg.getScaledInstance(120, 150, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("ERROR-importImg()-HouseOwner");
        }
    }


    public void draw(Graphics g) {
        synchronized (houseOwner) {
            if (houseOwner.isAlived()) {
                g.drawImage(zImages[0], (int) houseOwner.getX(), (int) houseOwner.getY(), null);
            }
        }
    }



    public void move() {
        if (y <= 0) {
            y = houseOwner.getHeight() - 80;
        } else if (y >= houseOwner.getHeight() - 80) {
            y = 0;
        }
        y -= speed;
    }

    public void mouseClicked(int x, int y) {
        if (x >= 200 && x <= 360) {
            for (int i = 0; i < playing.getTileManager().getTilesOfHouseOwner().length; i++) {
                Tile tileOfHouseOwner = playing.getTileManager().getTilesOfHouseOwner()[i];

                Rectangle r = new Rectangle(
                        (int) tileOfHouseOwner.getBound().getX(),
                        (int) tileOfHouseOwner.getBound().getY(),
                        (int) tileOfHouseOwner.getBound().getWidth(),
                        (int) tileOfHouseOwner.getBound().getHeight()
                );

                if (r.contains(x, y)) {
                    int newX = (int) tileOfHouseOwner.getBound().getX() + 30;
                    int newY = (int) tileOfHouseOwner.getBound().getY() + (int) (tileOfHouseOwner.getBound().getHeight() / 2) - (int) (houseOwner.getHeight() / 2 +20);
                    houseOwner.setLocation(newX, newY); // Gán vị trí mới cho HouseOwner
                    System.out.println("MoveHouseOwner");
                }
            }
        } else {
            System.out.println("ngoài phạm vi");
        }
    }




    public void setHouseOwnerDangered(Tile tile){
        // Đă trạng thái nguy hiểm
        Rectangle HouseOwner = tile.getBound();
            if(HouseOwner.contains(houseOwner.getX(),houseOwner.getY())){
                houseOwner.setDangered(true);
            }
    }
    public void setHouseOwnerIdle(Tile tile){
        // Đặt trạng thái bình tĩnh
        Rectangle HouseOwner = tile.getBound();
            if(HouseOwner.contains(houseOwner.getX(),houseOwner.getY())){
                houseOwner.setDangered(false);
            }
    }
    public void houseOwnerAttack(ProjectileOfHouseOwner projectileOfHouseOwner, ZombieManager zombieManager) {
        // Kiểm tra nếu đến thời điểm tấn công của HouseOwner
        if (projectileOfHouseOwner.getRealTimeCounter() == 90) {
            // Lấy tọa độ y của HouseOwner
            int houseOwnerY = (int) houseOwner.getY();

            // Lặp qua danh sách zombie để kiểm tra tọa độ y
            synchronized (zombieManager.getZombies()) {
                Iterator<Zombie> iterator = zombieManager.getZombies().iterator();
                while (iterator.hasNext()) {
                    Zombie zombie = iterator.next();
                    int zombieY = (int) zombie.Y();
                    int zombieX = (int) zombie.X();

                    // Kiểm tra nếu tọa độ y của HouseOwner gần bằng tọa độ y của zombie
                    if (Math.abs(houseOwnerY-zombieY) <= 30 && zombieX <=950) {
                        // Tạo một đạn mới và thêm vào ProjectileManager
                        System.out.println("create projectile of HouseOwner");
                        projectileOfHouseOwner.projectileCreated(houseOwner);
                        break; // Nếu đã tìm thấy zombie thích hợp, thoát khỏi vòng lặp
                    }
                }
            }
        }
        projectileOfHouseOwner.isResetTime();
    }

    public void alertHouseOwner(TileManager tileManager, ZombieManager zombieManager) {
        //Cảnh báo cho chủ nhà khi Zombie gần đến
        int houseOwnerX = (int) houseOwner.getX();

        for (int i = 0; i < tileManager.getTilesOfHouseOwner().length; i++) {
            Rectangle r = tileManager.getTilesOfHouseOwner()[i].getBound();
            Iterator<Zombie> iterator = zombieManager.getZombies().iterator();

            while (iterator.hasNext()) {
                Zombie zombie = iterator.next();
                int zombieX = (int)zombie.X();
                int zombieY = (int)zombie.Y();

                if (Math.abs(zombieX - houseOwnerX) <= 10 && Math.abs(zombieY - houseOwner.getY()) <= 10 && r.contains(zombieX, zombieY + 70)) {
                    setHouseOwnerDangered(tileManager.getTilesOfHouseOwner()[i]);
                } else {
                    setHouseOwnerIdle(tileManager.getTilesOfHouseOwner()[i]);
                }
            }
        }
    }
    public boolean isHouseOwnerAttack(int start, int end, TileManager tileManager, ZombieManager zombieManager){
        for(int i = start;i<end;i++){
            Rectangle r = tileManager.getTilesOfHouseOwner()[i].getBound();
            Iterator<Zombie> iterator = zombieManager.getZombies().iterator();
            while (iterator.hasNext()){
                Zombie zombie = iterator.next();
                Rectangle rZombie = new Rectangle((int)zombie.X(),(int)zombie.Y()+70,zombie.getWidth(),zombie.getHeight()-70);
                if(r.intersects(rZombie)){
                    return true;
                }
            }
        }
        return false;
    }

    public int getX() {
        return x;
    }
    public int getY(){
        return y;
    }
}



