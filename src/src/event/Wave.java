package event;

public class Wave {
    private int zType1, zType2;
    public Wave(int zType1, int zType2) {
        this.zType1 = zType1;
        this.zType2 = zType2;
    }

    public int type1() {
        return zType1;
    }

    public int type2() {
        return zType2;
    }
    public void recudeWave() {
        zType1--;
    }

}
