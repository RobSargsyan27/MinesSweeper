package User;

public class User {
    private String userName;
    private String bestGame;
    private int gamesPlayed;
    private int winsCount;

    private String[][][] savedGame;

    public User(String userName){
        this(userName, "00:00", 0, 0, new String[0][0][0]);
    }

    public User(String userName, String bestGame, int gamesPlayed, int winsCount, String[][][] savedGame){
        this.userName = userName;
        this.bestGame = bestGame;
        this.gamesPlayed = gamesPlayed;
        this.winsCount = winsCount;
        this.savedGame = savedGame;
    }

    public String getUserName() {
        return userName;
    }

    public String getBestGame() {
        return bestGame;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getWinsCount() {
        return winsCount;
    }

    public String[][][] getSavedGame() {
        return savedGame;
    }
}
