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

        boolean toPlay = true;
        while(toPlay){
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
            boolean isFirstTurn = true;
            boolean toRestart = false;
            int flagCount = 0;
            int moves = 0;
            String timeOnGame = "";
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
                    toPlay = false;
                    break;
                }else if(command[0].equals("restart")){
                    toRestart = true;
                    break;
                }else{
                    if(isFirstTurn && command.length == 2 ){
                        ArrayChecker.checkBoardMine(initialOpenBoard, command, difficultyType);
                    }
                    isFirstTurn = false;

                    ArrayChecker.checkCoordinate(initialCloseBoard, initialOpenBoard, command);
                }

                //Determine the current game timer
                int[] timer = TimerUtil.getInterval(startTime, System.currentTimeMillis());
                timeOnGame = timer[0] + ":" + timer[1];
                BoardPrinter.printBoard(initialCloseBoard, timer);

                //Determine whether to continue the game
                if(ArrayValidator.validateArray(initialCloseBoard, initialOpenBoard).equals("winner")
                        || ArrayValidator.validateArray(initialCloseBoard, initialOpenBoard).equals("loser")){
                    isActive = false;
                    playerStatusAfterGame = ArrayValidator.validateArray(initialCloseBoard, initialOpenBoard);
                }
            }

            if(toPlay && !toRestart){
                //Print the result
                BoardPrinter.printResult(timeOnGame, moves, playerStatusAfterGame);

                //Prompt the user for another game
                toPlay = UserPromptUtil.promptContinueGame();
            }
        }
    }
}
