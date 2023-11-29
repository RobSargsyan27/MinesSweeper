package User;

public class User {
    private String userName;
    private String bestGame;
    private int gamesPlayed;
    private int winsCount;

    public User(String userName){
        this(userName, "00:00", 0, 0);
    }

    public User(String userName, String bestGame, int gamesPlayed, int winsCount){
        this.userName = userName;
        this.bestGame = bestGame;
        this.gamesPlayed = gamesPlayed;
        this.winsCount = winsCount;
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
}
