public class Main {

    public static void main(String[] args) {
        Player player = new Player();
        Enemy enemy = new Enemy();
        Battle battle = new Battle(player, enemy);

//        for (int i = 0; i < 10; i++) {
//            battle.startRound();
//        }
    }
}
