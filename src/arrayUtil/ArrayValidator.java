package arrayUtil;

public class ArrayValidator {

    private ArrayValidator() {
        throw new AssertionError("The Array Initializer can not have an instance.");
    }

    private static boolean checkWinner(String[][] closedBoard, String[][] openBoard){
        int hiddenCell = 0;
        for(int i = 0; i < closedBoard.length; i++){
            for (int j = 0; j < closedBoard[i].length; j++){
                if(closedBoard[i][j].equals("#") && !openBoard[i][j].equals("X")){
                    hiddenCell++;
                }
            }
        }

        return hiddenCell == 0;
    }

    private static boolean checkLoser(String[][] closedBoard){
        for(int i = 0; i < closedBoard.length; i++){
            for(int j = 0; j < closedBoard[i].length; j++){
                if(closedBoard[i][j].equals("X")){
                    return true;
                }
            }
        }

        return false;
    }

    public static String validateArray(String[][] closedBoard, String[][] openBoard){
        if(checkWinner(closedBoard, openBoard)){
            return "winner";
        }else if(checkLoser(closedBoard)){
            return "loser";
        }

        return "";
    }
}
