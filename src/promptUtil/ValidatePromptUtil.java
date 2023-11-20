package promptUtil;

import gameDifficulty.GameDifficulty;

import javax.naming.InsufficientResourcesException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ValidatePromptUtil {
    static Scanner scanner = new Scanner(System.in);
    static Pattern VALID_NUMBER = Pattern.compile("\\d+");
    private ValidatePromptUtil() {
        throw new AssertionError("Validation can not create an object");
    }

    private static int validateAttribute(String input){
        ArrayList<String> inputArray = new ArrayList<>(Arrays.asList(input.split("\\D")));
        ArrayList<String> sanitizedArray = new ArrayList<>();
        for(int i = 0; i < inputArray.size(); i++){
            if(VALID_NUMBER.matcher(inputArray.get(i)).matches()){
                sanitizedArray.add(inputArray.get(i));
            }
        }
        return sanitizedArray.isEmpty() ? 0 : Integer.parseInt(sanitizedArray.get(0));
    }

    private static String[] validateAttributes(String input){
        ArrayList<String> inputArray = new ArrayList<>(Arrays.asList(input.split("\\D")));
        ArrayList<String> sanitizedArray = new ArrayList<>();
        for(int i = 0; i < inputArray.size(); i++){
            if(VALID_NUMBER.matcher(inputArray.get(i)).matches()){
                sanitizedArray.add(inputArray.get(i));
            }
        }
        return sanitizedArray.toArray(new String[0]);
    }

    public static String validateDifficulty(String difficulty){
        while (!(difficulty.equals("beginner") ||
                difficulty.equals("intermediate") ||
                difficulty.equals("expert") ||
                difficulty.equals("custom"))
        ) {
            System.out.print("Invalid difficulty: Please enter above mentioned valid difficulty: ");
            difficulty = scanner.nextLine().toLowerCase().trim();
        }

        return difficulty;
    }

    public static GameDifficulty validateBoardAttributes(GameDifficulty difficultyType){
        String[] inputArray = validateAttributes(scanner.nextLine());


        while(inputArray.length < 3){
            System.out.print("Enter the valid number of attributes for the board: ");
            inputArray = validateAttributes(scanner.nextLine());
        }

        difficultyType.setWidth(Integer.parseInt(inputArray[0]));
        difficultyType.setHeight(Integer.parseInt(inputArray[1]));
        difficultyType.setMinesNumber(Integer.parseInt(inputArray[2]));

        while (difficultyType.getWidth() == 0) {
            System.out.print("Invalid width: Enter a width between 3 and 40: ");
            int formattedInput = validateAttribute(scanner.nextLine());
            if(formattedInput != 0){
                difficultyType.setWidth(formattedInput);
            }
        }
        while (difficultyType.getHeight() == 0) {
            System.out.print("Invalid height: Enter a height between 3 and 50: ");
            int formattedInput = validateAttribute(scanner.nextLine());
            if(formattedInput != 0){
                difficultyType.setHeight(formattedInput);
            }

        }

        while (difficultyType.getMinesNumber() == 0 || difficultyType.getMinesNumber() > (difficultyType.getHeight() * difficultyType.getWidth() / 3)) {
            System.out.print("Invalid mines number: Enter a mines number between 1 and " + (difficultyType.getHeight() * difficultyType.getWidth() / 3) + ": ");
            int formattedInput = validateAttribute(scanner.nextLine());
            if(formattedInput != 0){
                difficultyType.setMinesNumber(formattedInput);
            }
        }

        return difficultyType;
    }

    public static boolean validateUserCoordinate(String[][] closeBoard , ArrayList<String> command, GameDifficulty difficultyType ){
        boolean isValid = true;
        if(command.size() < 2) {
            System.out.println("Invalid coordinate: Please enter valid number of arguments");
            command.clear();
            return false;
        }

        int column = Integer.parseInt(command.get(0));
        int row = Integer.parseInt(command.get(1));

        if(Integer.parseInt(command.get(0)) < 0 || Integer.parseInt(command.get(0)) >= difficultyType.getWidth()){
            System.out.println("Invalid x value: The x value should be between 1 and " + difficultyType.getWidth());
            isValid = false;
        }else if(Integer.parseInt(command.get(1)) < 0 || Integer.parseInt(command.get(1)) >= difficultyType.getHeight()){
            System.out.println("Invalid y value: The y value should be between 1 and " + difficultyType.getHeight());
            isValid = false;
        }else if(command.size() == 2 && closeBoard[row][column].equals("F")){
            System.out.println("Invalid coordinate: You can not open flagged cell! First remove flag using -f option.");
            isValid = false;
        }else if(command.size() == 2 && VALID_NUMBER.matcher(closeBoard[row][column]).matches()){
            System.out.println("Invalid coordinate: You can not open already opened cell!");
            isValid = false;
        }

        if(!isValid){
            command.clear();
        }

        return isValid;
    }

    public static boolean validateFlagOption(String[][] closeBoard, ArrayList<String> command){
        boolean isValid = true;

        if(command.size() == 3){
            int column = Integer.parseInt(command.get(0));
            int row = Integer.parseInt(command.get(1));

            if(VALID_NUMBER.matcher(closeBoard[row][column]).matches()){
                System.out.println("Invalid flag option: You can not put a flag on revealed cells!");
                command.clear();
                isValid = false;
            }
        }

        return isValid;
    }



}
