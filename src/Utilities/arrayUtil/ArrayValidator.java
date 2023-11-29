package Utilities.arrayUtil;

public abstract class ArrayValidator {

    private ArrayValidator() {}

    private static boolean checkWinner(String[][] closedBoard, String[][] openBoard){
        for(int i = 0; i < closedBoard.length; i++){
            for (int j = 0; j < closedBoard[i].length; j++){
                if((closedBoard[i][j].equals("#") || closedBoard[i][j].equals("F"))  && !openBoard[i][j].equals("X")){
                    return false;
                }
            }
        }

        return true;
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
