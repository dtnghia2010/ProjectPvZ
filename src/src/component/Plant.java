package component;

public class Plant {
    private double plantHP;

    public int getPlantID() {
        return plantID;
    }

    public void setPlantID(int plantID) {
        this.plantID = plantID;
    }

    private  int plantID;
    public Plant(double plantHP, int plantID){
        this.plantHP = plantHP;
        this.plantID = plantID;
    }
    public double getPlantHP() {
        return plantHP;
    }

    public void setPlantHP(double plantHP) {
        this.plantHP = plantHP;
    }
}