package entity;

public class Dice {

    enum DieType {
        ATTACK,
        DEFENSE,
        FOCUS,
        SPEED
    }

    DieType type;
    int value;

    // For random dice creation
    public Dice(int randomType, int randomValue) {
        this.type =  DieType.values()[randomType];
        this.value = randomValue;
    }

    public Dice(DieType type, int value) {
        this.type = type;
        this.value = value;
    }

}
