package printerUtil;

public final class BoardPrinter {
    private BoardPrinter(){
        throw new AssertionError("Utility can not create an object");
    }

    public static String padding(String word, int length){
        String formattedString = word;

        for(int i = 0; i < (length - word.length()); i++){
            formattedString += " ";
        }

        return formattedString;
    }

    private static String padding(int leftPadding, String word, int rightPadding){
        String formattedString = "";

        for(int i = 0; i < leftPadding; i++){
            formattedString += " ";
        }

        formattedString += word;

        for(int i = 0; i < (rightPadding - word.length()); i++){
            formattedString += " ";
        }

        return formattedString;
    }

    private static String printTime(int[] timer, int line){
        int minutes = timer[0];
        int seconds = timer[1];

        if(line == 1 || line == 3){
            return padding(10, "------------------------------", "------------------------------".length());
        }else if(line == 2){
            String formattedMinutes = minutes < 10 ? "0" + minutes : String.valueOf(minutes);
            String formattedSeconds = seconds < 10 ? "0" + seconds : String.valueOf(seconds);

            return padding(10, "| Timer (mm:ss) |  " + formattedMinutes + ":" + formattedSeconds, "-----------------------------".length()) + "|";
        }

        return "";
    }

    private static void printResultLoser(String time, int moves ){
        String result =
                padding("", 50) + "------------------------------------------------------------\n" +
                        padding("", 50) + "| " + padding(time, 56) + " |\n" +
                        padding("", 50) + "------------------------------------------------------------\n" +
                        padding("", 50) + "| " + padding(String.valueOf(moves), 56) + " |\n" +
                        padding("", 50) + "------------------------------------------------------------\n";

        System.out.println(result);
    }

    private static void printResultWinner(String time, int moves ){
        String result =
                padding("", 20) + "------------------------------------------------------------\n" +
                        padding("", 20) + "| Timer: " + padding(time, 50 ) + "|\n" +
                        padding("", 20) + "------------------------------------------------------------\n" +
                        padding("", 20) + "| Moves: " + padding(String.valueOf(moves), 50) + "|\n" +
                        padding("", 20) + "------------------------------------------------------------\n";

        System.out.println(result);
    }

    public static void printBoard(String[][] board, int[] timer) {
        //Print the horizontal coordinates
        for (int i = 0; i < board[0].length; i++) {
            int index = i + 1;
            if (i == (board[0].length - 1)) {
                System.out.println(index);
            } else if (i == 0) {
                System.out.print(padding(54, String.valueOf(index), 3));
            } else {
                System.out.print(padding(String.valueOf(index), 3));
            }
        }

        //Print the separate line
        String separateLine = "";
        for (int i = 0; i < board[0].length; i++) {
            if (i == (board[0].length - 1)) {
                separateLine += "------";
            } else {
                separateLine += "---";
            }
        }
        System.out.println(padding(50, separateLine, separateLine.length()));

        //Print the board
        for (int i = 0; i < board.length; i++) {
            //Print the vertical coordinates with each row and the timer if needed
            int index = i + 1;
            if(i > 0 && i < 5 && (timer[0] != 0 || timer[1] != 0)){
                System.out.print(printTime(timer, i - 5));
                System.out.print(padding(10, String.valueOf(index), 2) + "| ");
            }else{
                System.out.print(padding(50, String.valueOf(index), 2) + "| ");
            }

            //Print the board itself
            for (int j = 0; j < board[i].length; j++) {
                if (j == (board[i].length - 1)) {
                    System.out.println(board[i][j]);
                } else {
                    System.out.print(padding(board[i][j], 3));
                }
            }
        }
    }

    public static void printResult(String timeOnGame, int moves, String playerStatusAfterGame){
        GreetingPrinter.printBoarder();
        if(playerStatusAfterGame.equals("loser")){
            GreetingPrinter.printLoser();
            BoardPrinter.printResultLoser(timeOnGame,moves);
        }else if (playerStatusAfterGame.equals("winner")){
            GreetingPrinter.printWinner();
            BoardPrinter.printResultWinner(timeOnGame, moves);
        }
    }
}
