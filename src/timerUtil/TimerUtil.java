package timerUtil;

public class TimerUtil {
    private TimerUtil() {
        throw new AssertionError("Utility can not create an object");
    }

    public static int[] getInterval(long startTime, long endTime){
        int gameInterval =(int) (endTime - startTime) / 1000;
        int minutes = gameInterval / 60;
        int seconds = gameInterval % 60;

        return new int[]{minutes, seconds};
    }

}
