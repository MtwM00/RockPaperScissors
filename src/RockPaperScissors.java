import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class RockPaperScissors {

    public static void main(String[] args) {

        File file = new File("./rating.txt");

        System.out.println("Enter your name:");
        Scanner scannerConsole = new Scanner(System.in);
        String playerName = scannerConsole.nextLine();
        int playerScore = 0;

        //Checking if player name already exists in the file

        try (Scanner scannerFile = new Scanner(file);) {
            while (scannerFile.hasNext()) {
                String nameInFile = scannerFile.nextLine();
                if (nameInFile.contains(playerName)) {
                    String playerScoreString = nameInFile.substring(nameInFile.length() - 3);
                    playerScore = Integer.parseInt(playerScoreString);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Hello, " + playerName);

        //Getting users options

        String optionsLine = scannerConsole.nextLine();
        List<String> options = new ArrayList<>();

        //Checking if user entered list of options

        if (optionsLine.equals("")) {
            options.add("rock");
            options.add("paper");
            options.add("scissors");
        } else {
            String[] arrayOfOptions = optionsLine.split(",");
            options = Arrays.asList(arrayOfOptions);
        }

        System.out.println("Okay, let's start");

        Random random = new Random();

        String playerChoice = scannerConsole.next();
        List<String> losesTo = new ArrayList<>();

        //Game running loop

        while (!playerChoice.equals("!exit")) {

            if (checkIfContains(playerChoice, options)) {

                String computerChoice = options.get(random.nextInt(options.size()));
                Collections.rotate(options, options.size() - options.indexOf(playerChoice));

                for (int i = 1; i <= options.size() / 2; i++) {
                    losesTo.add(options.get(i));
                }
                if (playerChoice.equals(computerChoice)) {
                    System.out.println("There is a draw (" + computerChoice + ")");
                    playerScore += 50;
                } else if (checkIfContains(computerChoice, losesTo)) {
                    System.out.println("Sorry, but the computer chose " + computerChoice);
                } else {
                    System.out.println("Well done. The computer chose " + computerChoice + " and failed");
                    playerScore += 100;
                }

            } else if (playerChoice.equals("!rating")) {
                System.out.println("Your rating: " + playerScore);
            } else {
                System.out.println("Invalid input");
            }
            losesTo.clear();
            playerChoice = scannerConsole.next();
        }
        System.out.println("Bye!");
    }

    public static boolean checkIfContains(String input, List<String> listOfOptions) {

        for (String option : listOfOptions) {
            if (input.equals(option)) {

                return true;
            }
        }
        return false;
    }

}


