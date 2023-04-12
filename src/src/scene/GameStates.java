package scene;

public enum GameStates {
    Menu, Playing, Lose, Setting;
    public static GameStates gameStates = GameStates.Menu;
    public static void setGameStates(GameStates state) {
        gameStates = state;
        System.out.println("Set GameState succeed");
    }
}
