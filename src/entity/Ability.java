package entity;

public class Ability {
    private String name;
    private String effect;
    private int baseDamage;
    private int cost;
    private boolean isOffense;

    public Ability() {
        name = "Attack";
        effect = "A basic attack.";
        cost = 0;
        isOffense = true;
    }

    public Ability(String name, String effect, int baseDamage, int cost, boolean isOffense) {
        this.name = name;
        this.effect = effect;
        this.baseDamage = baseDamage;
        this.cost = cost;
        this.isOffense = isOffense;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isOffense() {
        return isOffense;
    }

    public void setOffense(boolean offense) {
        isOffense = offense;
    }
}