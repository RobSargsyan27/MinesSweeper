package arrayUtil;

import gameDifficulty.GameDifficulty;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class ArrayChecker {
    static Pattern VALID_NUMBER = Pattern.compile("[1-8]");
    private ArrayChecker(){
        throw new AssertionError("The Array Initializer can not have an instance.");
    }

    private static int[][] getBombCoordinates(String[][] openBoard){
        ArrayList<int[]> bombsCoordinates = new ArrayList<>();

        for(int i = 0; i < openBoard.length; i++){
            for(int j = 0; j < openBoard[i].length; j++){
                if(openBoard[i][j].equals("X")){
                    bombsCoordinates.add(new int[]{i,j});
                }
            }
        }

        return bombsCoordinates.toArray(new int[1][2]);
    }

    private static void openEmptyCells(String[][] closeBoard, String[][] openBoard, String[] command){
        int column = Integer.parseInt(command[0]);
        int row = Integer.parseInt(command[1]);

        closeBoard[row][column] = "0";

        for(int i = (row - 1); i < (row + 2); i++){
            if(i > -1 && i < openBoard.length){
               for(int j = (column - 1); j < (column + 2); j++){
                   if(j > -1 && j < openBoard[i].length){
                       if(VALID_NUMBER.matcher(openBoard[i][j]).matches() && closeBoard[i][j].equals("#")){
                           String cellValue = openBoard[i][j];
                           closeBoard[i][j] = cellValue;
                       }else if(openBoard[i][j].equals("0") && closeBoard[i][j].equals("#")){
                           String[] recursiveCommand = new String[]{String.valueOf(j), String.valueOf(i)};
                           openEmptyCells(closeBoard, openBoard, recursiveCommand );
                       }
                   }
               }
            }
        }
    }
    public static void checkCoordinate(String[][] closeBoard, String[][] openBoard, String[] command){
        int column = Integer.parseInt(command[0]);
        int row = Integer.parseInt(command[1]);
        boolean hasFlag = command.length == 3;

        //Evaluate the function for command with flag option
        if(hasFlag){
            closeBoard[row][column] = closeBoard[row][column].equals("#") ? "F" : "#";
            return;
        }

        //Evaluate the function for command without flag option
        String cellValue;
        if(VALID_NUMBER.matcher(openBoard[row][column]).matches()){
            cellValue = "number";
        }else if(openBoard[row][column].equals("X")){
            cellValue = "bomb";
        }else{
            cellValue = "empty";
        }

        switch (cellValue){
            case "number":
                String numberInCell = openBoard[row][column];
                closeBoard[row][column] = numberInCell;
                break;
            case "bomb":
                int[][] bombsCoordinates = getBombCoordinates(openBoard);
                for(int i = 0; i < bombsCoordinates.length; i++){
                    int[] bombCoordinate = bombsCoordinates[i];
                    closeBoard[bombCoordinate[0]][bombCoordinate[1]] = "X";
                }
                break;
            case "empty":
                openEmptyCells(closeBoard, openBoard, command);
                break;
        }
    }

    public static void checkBoardMine(String[][] board, String[] safeCell, GameDifficulty difficultyType){
        int column = Integer.parseInt(safeCell[0]);
        int row = Integer.parseInt(safeCell[1]);

        if(board[row][column].equals("X")){
            int randomRow = (int) (Math.random() * difficultyType.getHeight());
            int randomColumn = (int) (Math.random() * difficultyType.getWidth());

            while(board[randomRow][randomColumn].equals("X") || row == randomRow && column == randomColumn ){
                randomRow = (int) (Math.random() * difficultyType.getHeight());
                randomColumn = (int) (Math.random() * difficultyType.getWidth());
            }

            board[randomRow][randomColumn] = "X";
            board[row][column] = "0";
            ArrayInitializer.setBoardNumbers(board);
        }
    }
}
