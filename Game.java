package RandomNumberGame;
import java.util.Random;
import java.util.Scanner;

public class Game {
    /**
     * Game mechanics
     * Computer generates random number and player attempts to guess it
     * Tells player if guess was too low, too high, or invalid
     * Returns total score to be added to CSV file or stores while another round commences
     */

    public Game() {}
    Scanner scan = new Scanner(System.in);

    //Sets limits for and generates random number
    Random random = new Random();
    int upperbound = 16;
    int randomNum = random.nextInt(upperbound);

    int playerScore = 100;
    int firstGuess = 0;


    protected int gameMechanics() {
        while (firstGuess != randomNum) {
        System.out.print("Enter a number between 1 and 15: ");
        //Validates user input, if invalid gives player a score of 0 and kicks them out.
        try {
            firstGuess = scan.nextInt();
            if (firstGuess > 15 || firstGuess < 1) {
                System.out.println("Invalid number\n");
                playerScore = 1;
                break;
            }
        } catch (Exception InputMismatchException) {
            System.out.println("\n" + InputMismatchException + "\nInvalid number\n");
            playerScore = 1;
            break;
        }
        //Tells the user if their guess was too low, too high, or correct
            if (playerScore > 0) {
                if (firstGuess < randomNum) {
                    System.out.println("\nToo low, try again");
                    playerScore -= 20;
                } else if (firstGuess > randomNum) {
                    System.out.println("\nToo high, try again");
                    playerScore -= 20;
                } else
                    System.out.println("Good job!\n");
            } else {
                //Ends game when player runs out of points
                System.out.println("Sorry, you lose! The computers number was: " + randomNum + "\n");
                break;
            }
        }
        return playerScore;
    }
}