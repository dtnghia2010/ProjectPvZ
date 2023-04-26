package component;

public class plant {
    private int HP;
    private int ID;

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
