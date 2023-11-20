import arrayUtil.ArrayChecker;
import arrayUtil.ArrayInitializer;
import arrayUtil.ArrayValidator;
import printerUtil.BoardPrinter;
import printerUtil.GreetingPrinter;
import gameDifficulty.GameDifficulty;
import promptUtil.UserPromptUtil;
import timerUtil.TimerUtil;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //Print the Greeting and Rules of the game
        GreetingPrinter.printBoarder();
        GreetingPrinter.printLogo();
        GreetingPrinter.printRules();
        GreetingPrinter.printBoarder();

        //Prompt the user to enter difficulty of the game
        GameDifficulty difficultyType = UserPromptUtil.promptBoardValues();

        //Initialise open and close boards for the game
        String[][] initialOpenBoard = ArrayInitializer.getInitialOpenBoard(difficultyType);
        String[][] initialCloseBoard = ArrayInitializer.getInitialCloseBoard(difficultyType);

        //Print the initial close board
        BoardPrinter.printBoard(initialCloseBoard, new int[2]);
        BoardPrinter.printBoard(initialOpenBoard, new int[2]);

        //Start the actual game
        boolean isActive = true;
        boolean toRestart = false;
        boolean isFirstTurn = true;
        int flagCount = 0;
        int[] timer = new int[2];
        int moves = 0;
        String playerStatusAfterGame = "";

        //Get the current time for timer
        long startTime = System.currentTimeMillis();

        while (isActive) {
            //Count the total moves
            moves++;

            //Get the valid command from the user
            String[] command = UserPromptUtil.promptActionValues(initialCloseBoard,difficultyType);

            //Evaluate the given command
            if(command[0].equals("quit")){
                break;
            }else if(command[0].equals("restart")){
                toRestart = true;
            }else{
                if(isFirstTurn && command.length == 2 ){
                    ArrayChecker.checkBoardMine(initialOpenBoard, command, difficultyType);
                }
                isFirstTurn = false;

                ArrayChecker.checkCoordinate(initialCloseBoard, initialOpenBoard, command);
            }

            //Determine the current game timer
            timer = TimerUtil.getInterval(startTime, System.currentTimeMillis());
            BoardPrinter.printBoard(initialCloseBoard, timer);

            //Determine whether to continue the game
            if(ArrayValidator.validateArray(initialCloseBoard, initialOpenBoard).equals("winner")
                    || ArrayValidator.validateArray(initialCloseBoard, initialOpenBoard).equals("loser")){
                isActive = false;
                playerStatusAfterGame = ArrayValidator.validateArray(initialCloseBoard, initialOpenBoard);
            }
        }

        GreetingPrinter.printBoarder();
        String time = timer[0] + ":" + timer[1];
        if(playerStatusAfterGame.equals("loser")){
            GreetingPrinter.printLoser();
            BoardPrinter.printResultLoser(time,moves);
        }else if (playerStatusAfterGame.equals("winner")){
            GreetingPrinter.printWinner();
            BoardPrinter.printResultWinner(time, moves);
        }



    }
}
