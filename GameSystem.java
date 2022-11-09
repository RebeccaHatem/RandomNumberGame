package RandomNumberGame;

import java.util.Scanner;

class GameSystem {

    public static void main(String[] args) {
    /*
         Hello, welcome to my guessing game!
         In this game the computer will generate a random number and
         you and your friends will compete to win by guessing it! The
         project will be completed in four phases. By the Final Phase
         the program will be a fun, challenging, and effective. Good Luck!
    Phases:
        Phase 1 - Plan project
        Phase 2 - Build procedural Alpha Version
        Phase 3 - Add appropriate classes
        Final Phase - Refactor Procedural Alpha Project to OOP Beta
    Sources:
        Used class code examples and
        https://stackoverflow.com/questions/4269302/how-do-you-append-to-a-text-file-instead-of-overwriting-it-in-java
     */
        Game game = new Game();
        Scanner scan = new Scanner(System.in);
        ScoreReaderAndWriter sreader = new ScoreReaderAndWriter();

        System.out.print("Please enter your nickname: ");
        String playerName = scan.nextLine();


        //Receives input from user and separates the month, day, and year into separate variables.
        System.out.print("Please enter your DOB (MM/DD/YYYY): ");
            String playerDOB = scan.nextLine();
            //Used to prevent an error
            playerDOB.trim();
            int month = Integer.parseInt(playerDOB.substring(0,2));
            int day = Integer.parseInt(playerDOB.substring(3,5));
            int year = Integer.parseInt(playerDOB.substring(6));

        System.out.print("Hello " + playerName + ", ");

        //Displays the top scores
        System.out.println(sreader.toString());
        //Calls the game so the player can begin guessing numbers
        game.gameMechanics();
        int totalScore = game.playerScore;
        while (game.playerScore > 0) {
            System.out.println("Round Score: " + game.playerScore + "\nCurrent score: " + totalScore);
            //Allows the player to leave the game and submit their total score, or continue to the next round
            System.out.print("Would you like to continue? (y/n): ");
            if (scan.nextLine().toLowerCase().startsWith("y")) {
                game = new Game();
                //Calls the game so the player can begin the next round
                game.gameMechanics();
                //Keeps track of the players total score (adding up score each round)
                totalScore += game.playerScore;
            } else {
                break;
            }
        }
        System.out.println("Total Score: " + totalScore);
        //Adds players score to TopScorers file
        ScoreReaderAndWriter.writeScore(playerName, totalScore);
        //Displays the top scores
        System.out.println(sreader.toString());
    }
}