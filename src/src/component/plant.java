package component;

public class plant {
    private int HP;
    private int ID;
    private int tileNum;

    public int getTileNum() {
        return tileNum;
    }

    public void setTileNum(int tileNum) {
        this.tileNum = tileNum;
    }

    public int getID() {
        return ID;
    }

    public plant(int HP,int ID){
        this.HP = HP;
        this.ID = ID;
    }
    public int getHP(){
        return this.HP;
    }
}
