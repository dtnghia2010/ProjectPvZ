package component;

public class Plant {
    private double plantHP;
    private  int plantID;
    private int tileHold;
    public Plant(double plantHP, int plantID){
        this.plantHP = plantHP;
        this.plantID = plantID;
    }
    public void setTileHold(int tileHold) {
        this.tileHold = tileHold;
    }

    public int getTileHold() {
        return tileHold;
    }

    public int getPlantID() {
        return plantID;
    }

    public void setPlantID(int plantID) {
        this.plantID = plantID;
    }

    public double getPlantHP() {
        return plantHP;
    }

    public void setPlantHP(double plantHP) {
        this.plantHP = plantHP;
    }
}