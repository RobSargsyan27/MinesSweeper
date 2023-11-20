package gameDifficulty;

public class GameDifficulty {
    private int width;
    private int height;
    private int minesNumber;

    public GameDifficulty(int width, int height, int minesNumber){
        setWidth(width);
        setHeight(height);
        setMinesNumber(minesNumber);
    }

    public GameDifficulty(){
        this.width = 0;
        this.height = 0;
        this.minesNumber = 0;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getMinesNumber() {
        return minesNumber;
    }

    public void setWidth(int width) {
        if(width < 41 && width > 0){
            this.width = width;
        }
    }

    public void setHeight(int height) {
        if(height < 51 && height > 0){
            this.height = height;
        }
    }

    public void setMinesNumber(int minesNumber) {
        if (minesNumber < 301 && minesNumber > 0 ) {
            this.minesNumber = minesNumber;
        }
    }
}
