package timerUtil;

public abstract class TimerUtil {
    private TimerUtil() {}

    public static int[] getInterval(long startTime, long endTime){
        int gameInterval =(int) (endTime - startTime) / 1000;
        int minutes = gameInterval / 60;
        int seconds = gameInterval % 60;

        return new int[]{minutes, seconds};
    }
}
