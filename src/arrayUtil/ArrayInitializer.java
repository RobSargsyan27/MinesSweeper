package arrayUtil;

import java.util.Arrays;

import gameDifficulty.GameDifficulty;

public class ArrayInitializer {
    private ArrayInitializer() {
        throw new AssertionError("The Array Initializer can not have an instance.");
    }

    private static String[][] initBoard(GameDifficulty difficultyType, String type) {
        int width = difficultyType.getWidth();
        int height = difficultyType.getHeight();
        String[][] board = new String[height][width];

        if (type.equals("open")) {
            for (int i = 0; i < board.length; i++) {
                Arrays.fill(board[i], " ");
            }
        } else if (type.equals("close")) {
            for (int i = 0; i < board.length; i++) {
                Arrays.fill(board[i], "#");
            }
        }

        return board;
    }

    private static void setBoardMines(String[][] board, GameDifficulty difficultyType) {
        int width = difficultyType.getWidth();
        int height = difficultyType.getHeight();
        int minesNumber = difficultyType.getMinesNumber();

        for (int i = 0; i < minesNumber; i++) {
            int randomColumn = (int) (Math.random() * width);
            int randomRow = (int) (Math.random() * height);

            while (board[randomRow][randomColumn].equals("X")) {
                randomColumn = (int) (Math.random() * width);
                randomRow = (int) (Math.random() * height);
            }
            board[randomRow][randomColumn] = "X";
        }
    }

    public static void setBoardNumbers(String[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (!board[i][j].equals("X")) {
                    int[] coordinate = new int[]{i, j};

                    assignNumberToCell(board, coordinate);
                }
            }
        }
    }

    private static void assignNumberToCell(String[][] board, int[] coordinate) {
        int minesNumber = 0;
        int row = coordinate[0];
        int column = coordinate[1];

        for (int i = (row - 1); i < (row + 2); i++) {
            if (i > -1 && i < board.length) { // Boarder validation
                for (int j = (column - 1); j < (column + 2); j++) {
                    if (j > -1 && j < board[i].length) { //Boarder validation
                        if ((row != i || column != j) && board[i][j].equals("X")) {
                            minesNumber++;
                        }
                    }
                }
            }
        }

        board[row][column] = String.valueOf(minesNumber);
    }

    public static String[][] getInitialOpenBoard(GameDifficulty difficultyType) {
        //Initialise open board with empty cells
        String[][] openBoard = initBoard(difficultyType, "open");

        //Assign mines to random cells
        setBoardMines(openBoard, difficultyType);

        //Assign empty cells according to neighbouring mines
        setBoardNumbers(openBoard);

        return openBoard;
    }

    public static String[][] getInitialCloseBoard(GameDifficulty difficultyType) {
        //Initialise close board with #
        return initBoard(difficultyType, "close");

    }
}