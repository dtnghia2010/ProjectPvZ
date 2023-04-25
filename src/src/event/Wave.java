package event;

public class Wave {
    private int amType0, amType1, amType2;
    public Wave(int amType0, int amType1, int amType2) {
        this.amType0 = amType0;
        this.amType1 = amType1;
        this.amType2 = amType2;
    }

    public int amountType(int type) {
        switch (type) {
            case 0:
                return amType0;
            case 1:
                return amType1;
            case 2:
                return amType2;
        }
        return -1;
    }
    public void recudeWave(int type) {
        switch (type) {
            case 0:
                amType0--;
                break;
            case 1:
                amType1--;
                break;
            case 2:
                amType2--;
                break;
        }
    }

}
