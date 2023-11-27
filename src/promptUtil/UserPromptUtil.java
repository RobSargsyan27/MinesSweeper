package promptUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;
import gameDifficulty.GameDifficulty;

public abstract class UserPromptUtil {
    static Scanner scanner = new Scanner(System.in);
    static final Pattern VALID_NUMBER = Pattern.compile("\\d+");

    private UserPromptUtil() {}

    public static GameDifficulty promptBoardValues() {
        GameDifficulty difficultyType = new GameDifficulty();

        System.out.print("Enter the difficulty of the game: ");
        String difficulty = ValidatePromptUtil.validateDifficulty(scanner.nextLine().toLowerCase().trim());

        switch (difficulty) {
            case "beginner":
                difficultyType = new GameDifficulty(9, 9, 10);
                break;
            case "intermediate":
                difficultyType = new GameDifficulty(16, 16, 40);
                break;
            case "expert":
                difficultyType = new GameDifficulty(30, 16, 99);
                break;
            case "custom":
                System.out.print("Enter the width, height and mines number for the board (x y m): ");
                difficultyType = ValidatePromptUtil.validateBoardAttributes(difficultyType);
                break;
        }
        return difficultyType;
    }

    public static String[] promptActionValues(String[][] closedBoard, GameDifficulty difficultyType, int flagCount) {
        boolean isValid = false;
        ArrayList<String> sanitizedInput = new ArrayList<>();

        while(!isValid){
            //Prompt the user for the input
            System.out.print("Enter the coordinate for the cell (x y): ");
            String input = scanner.nextLine().trim();

            //Check if the user wants to quit or restart the game
            if(input.equals("q")){
                return new String[]{"quit"};
            }else if(input.equals("r")){
                return new String[]{"restart"};
            }

            //Sanitize the coordinate and check if the user wants to put a flag
            String[] inputSplit = input.split("\\D");
            boolean hasFlag = input.endsWith("-f");

            for(int i = 0; i < inputSplit.length; i++){
                if(VALID_NUMBER.matcher(inputSplit[i]).matches() && sanitizedInput.size() < 2){
                    int zeroIndexValue = Integer.parseInt(inputSplit[i]) - 1;
                    sanitizedInput.add(String.valueOf(zeroIndexValue));
                }
            }

            //Add third value in an array if the user want to put flag
            if(hasFlag){
                sanitizedInput.add("flag");
            }

            //Validate the given coordinates according to the board width and height
            //Validate the flag option for the given cell
            isValid = ValidatePromptUtil.validateUserCoordinate(closedBoard, sanitizedInput, difficultyType)
                    && ValidatePromptUtil.validateFlagOption(closedBoard, sanitizedInput, flagCount);

        }
        return sanitizedInput.toArray(new String[0]);
    }

    public static boolean promptContinueGame(){
        System.out.print("Do you want to play again? ");
        String input = scanner.nextLine();
        String command = ValidatePromptUtil.validateContinueGame(input);

        while(command.equals("null")){
            System.out.print("Invalid input: Please answer either yes or no: ");
            input = scanner.nextLine();
            command = ValidatePromptUtil.validateContinueGame(input);
        }

        return command.equals("yes");
    }
}