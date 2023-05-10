package scenes;

public enum GameScenes {
    MENU, PLAYING, LOSE, PAUSE;
    public static GameScenes gameScenes = GameScenes.MENU;
    public static void setGameScenes(GameScenes scene) {
        gameScenes = scene;
    }
}