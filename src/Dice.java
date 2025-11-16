public class Dice {

    String[] typeChoices = {"attack", "defense", "accuracy", "speed", "wild"};
    String type;
    int value;

    public Dice(int typeIndex, int value) {
        this.type = typeChoices[typeIndex];
        this.value = value;
    }

}
