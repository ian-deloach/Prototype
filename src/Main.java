public class Main {

    public static void main(String[] args) {
        Player player = new Player();
        Enemy enemy = new Enemy();
        Battle battle = new Battle(player, enemy);
        Graphics graphics = new Graphics();

        graphics.run();

//        battle.runBattle();

    }
}
