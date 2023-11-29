package Utilities.printerUtil;

public abstract class BoardPrinter {
    private BoardPrinter(){}

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

    private static String printHeader(int[] timer, int flagCount){
        int minutes = timer[0];
        int seconds = timer[1];
        String formattedMinutes = timer[0] < 10 ? "0" + timer[0] : String.valueOf(timer[0]);
        String formattedSeconds = timer[1] < 10 ? "0" + timer[1] : String.valueOf(timer[1]);
        String header = "null";


        if((minutes != 0 || seconds!= 0) && flagCount > -1){
            header =
                    padding("", 50) + "--------------------------------------------------\n" +
                    padding("", 50) + padding("| Time | ", 10) + formattedMinutes + ":" + formattedSeconds + "                                  |\n" +
                    padding("", 50) + "--------------------------------------------------\n" +
                    padding("", 50) + padding("| Flags | ", 10) + padding(String.valueOf(flagCount), 2) + "                                     |\n" +
                    padding("", 50) + "--------------------------------------------------\n";
        }

        return header;
    }

    private static void printResultLoser(String time, int moves ){
        String result =
                padding("", 20) + "------------------------------------------------------------\n" +
                        padding("", 20) + "| Timer: " + padding(time, 50 ) + "|\n" +
                        padding("", 20) + "------------------------------------------------------------\n" +
                        padding("", 20) + "| Moves: " + padding(String.valueOf(moves), 50) + "|\n" +
                        padding("", 20) + "------------------------------------------------------------\n";

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

    public static void printBoard(String[][] board, int[] timer, int flagCount) {
        //The separate line
        String separateLine = "";
        for (int i = 0; i < board[0].length; i++) {
            if (i == (board[0].length - 1)) {
                separateLine += "------";
            } else {
                separateLine += "---";
            }
        }

        //Print the timer and flags count
        if(!printHeader(timer,flagCount).equals("null")){
            System.out.println(printHeader(timer, flagCount));
            System.out.println(padding(50, separateLine, separateLine.length()));
        }


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

        System.out.println(padding(50, separateLine, separateLine.length()));

        //Print the board
        for (int i = 0; i < board.length; i++) {
            //Print the vertical coordinates with each row and the timer if needed
            int index = i + 1;
            System.out.print(padding(50, String.valueOf(index), 2) + "| ");

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
