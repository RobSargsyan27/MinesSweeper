package printerUtil;

public class GreetingPrinter {
    private GreetingPrinter() {
        throw new AssertionError("Can not create a utility object");
    }

    public static void printLogo() {
        String intro = "__        __   _                             _           __  __ _                                                   \n" +
                "\\ \\      / /__| | ___ ___  _ __ ___   ___   | |_ ___    |  \\/  (_)_ __   ___  _____      _____  ___ _ __   ___ _ __ \n" +
                " \\ \\ /\\ / / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\  | __/ _ \\   | |\\/| | | '_ \\ / _ \\/ __\\ \\ /\\ / / _ \\/ _ \\ '_ \\ / _ \\ '__|\n" +
                "  \\ V  V /  __/ | (_| (_) | | | | | |  __/  | || (_) |  | |  | | | | | |  __/\\__ \\\\ V  V /  __/  __/ |_) |  __/ |   \n" +
                "   \\_/\\_/ \\___|_|\\___\\___/|_| |_| |_|\\___|   \\__\\___/   |_|  |_|_|_| |_|\\___||___/ \\_/\\_/ \\___|\\___| .__/ \\___|_|   \n" +
                "                                                                                                   |_|   ";

        System.out.println(intro);
    }

    public static void printWinner(){
        String winner = "\n" +
                " __      __                                      \n" +
                "/\\ \\  __/\\ \\  __                                 \n" +
                "\\ \\ \\/\\ \\ \\ \\/\\_\\    ___     ___      __   _ __  \n" +
                " \\ \\ \\ \\ \\ \\ \\/\\ \\ /' _ `\\ /' _ `\\  /'__`\\/\\`'__\\\n" +
                "  \\ \\ \\_/ \\_\\ \\ \\ \\/\\ \\/\\ \\/\\ \\/\\ \\/\\  __/\\ \\ \\/ \n" +
                "   \\ `\\___x___/\\ \\_\\ \\_\\ \\_\\ \\_\\ \\_\\ \\____\\\\ \\_\\ \n" +
                "    '\\/__//__/  \\/_/\\/_/\\/_/\\/_/\\/_/\\/____/ \\/_/ \n" +
                "                                                 ";

        System.out.println(winner);
    }

    public static void printLoser(){
        String loser = " __                                      \n" +
                "/\\ \\                                     \n" +
                "\\ \\ \\        ___     ____     __   _ __  \n" +
                " \\ \\ \\  __  / __`\\  /',__\\  /'__`\\/\\`'__\\\n" +
                "  \\ \\ \\L\\ \\/\\ \\L\\ \\/\\__, `\\/\\  __/\\ \\ \\/ \n" +
                "   \\ \\____/\\ \\____/\\/\\____/\\ \\____\\\\ \\_\\ \n" +
                "    \\/___/  \\/___/  \\/___/  \\/____/ \\/_/";

        System.out.println(loser);
    }

    public static void printBoarder(){
        String line = "-----------------------------------------------------------------------------------------------------------------------------";
        System.out.println(line);
    }

    public static void printRules(){
        String difficulty =
                "---------------------------------------\t\t\t-----------------------------------------------------------------------------\n" +
                "|          Choose Difficulty          |\t\t\t|                                   Rules                                   |\n" +
                "---------------------------------------\t\t\t-----------------------------------------------------------------------------\n" +
                "|              Beginner               |\t\t\t| Random mines are put on a board and the user need to finish the game      |\n" +
                "---------------------------------------\t\t\t| without pushing on any mine. The cells around the mines identify how many |\n" +
                "|            Intermediate             |\t\t\t| mines are near that cell. Each number identifies the mines near           |\n" +
                "---------------------------------------\t\t\t| its radius.                                                               |\n" +
                "|               Expert                |\t\t\t-----------------------------------------------------------------------------\n" +
                "---------------------------------------\t\t\t| Select the cell using coordinate of the cell (x, y)                       |\n" +
                "|               Custom                |\t\t\t-----------------------------------------------------------------------------\n" +
                "---------------------------------------\t\t\t| Use the \" -f \" option to put or remove flag from the selected             |\n" +
                "                                       \t\t\t| cell (e.g 3 6 -f)                                                         |\n" +
                "                                       \t\t\t-----------------------------------------------------------------------------\n" +
                "                                       \t\t\t| Use the \" q \" command to quit the game                                    |\n" +
                "                                       \t\t\t-----------------------------------------------------------------------------\n" +
                "                                       \t\t\t| Use the \" r \" command to restart the game                                 |\n" +
                "                                       \t\t\t-----------------------------------------------------------------------------\n" +
                "                                       \t\t\t| \" 0 \" means that the cell is empty                                        |\n" +
                "                                       \t\t\t-----------------------------------------------------------------------------\n";

        System.out.println("\n\n" + difficulty);
    }
}
