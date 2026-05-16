package entity;

public class Dice {

    public enum DieType {
        ATTACK,
        DEFENSE,
        ENERGY,
        SPEED
    }

    DieType type;
    private int value;

    // For random dice creation
    public Dice(int randomType, int randomValue) {
        this.type =  DieType.values()[randomType];
        this.value = randomValue;
    }

    public Dice(DieType type, int value) {
        this.type = type;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getType() {
        return type.toString();
    }

}
