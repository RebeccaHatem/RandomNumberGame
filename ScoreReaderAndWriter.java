package RandomNumberGame;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Set;
import java.util.TreeMap;

public class ScoreReaderAndWriter {

    private final TreeMap<Integer,String> scores = new TreeMap<>();

    //Loads and opens TopScorers.csv
    protected ScoreReaderAndWriter() { loadFile("C:\\Users\\Godda\\IdeaProjects\\CLassWork\\Project\\src\\RandomNumberGame\\TopScorers.csv"); }

        public void loadFile (String filenameAndPath){
            /**
             * Reads and translates file
             * Catches any errors that occur
             */
            String row;
            int header = 0;

            try (BufferedReader reader = Files.newBufferedReader(Paths.get(filenameAndPath))) {
            //Reads and adds the first 5 lines of TopScoreres.csv to an ArrayList
                while (((row = reader.readLine()) != null)) {
                    if (header == 0) {
                        header++;
                    } else {
                        String[] fields = row.toUpperCase(Locale.ROOT).split("\\|");
                        scores.put(Integer.valueOf(fields[0]), fields[1]);
                    }
                }
            } catch (FileNotFoundException ignored) {
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

    @Override
    public String toString() {
        /**
         * Prints top scores in a well formatted style.
         */
        int counter = 0;
        String combined = "Top Scores:\n\t";
        //Orders the scores from highest to lowest
        Set<Integer> topScores = scores.descendingKeySet();
        //Formats the output
            for (Integer ts : topScores) {
                combined += ts + "|" + scores.get(ts) + "\n\t";
                //Prints the top 5 scores
                counter += 1;
                if (counter > 4) {
                    break;
                }
            }
            return combined;
        }
        protected static void writeScore(String playerName, int totalScore) {
            /**
             * Opens and writes results of the game in TopScorers.csv
             * Catches for any errors that occur
             */
            BufferedWriter bw = null;
            //Checks to see if the player hasn't lost, if so doesnt add to scoreboard
            if (totalScore > 0) {
                try {
                    String topScores = totalScore + "|" + playerName + "\n";
                    //Specify the file name and path here
                    File file = new File("C:\\Users\\Godda\\IdeaProjects\\CLassWork\\Project\\src\\RandomNumberGame\\TopScorers.csv");

                    FileWriter fw = new FileWriter(file, true);
                    bw = new BufferedWriter(fw);
                    //Writes the score and the player name in TopScorers.csv
                    bw.write(topScores);
                    System.out.println("Scores Added!");

                } catch (IOException ioe) {
                    ioe.printStackTrace();
                } finally {
                    try {
                        //Closes the file after reading
                        if (bw != null)
                            bw.close();
                    } catch (Exception ex) {
                        System.out.println("Error in closing the BufferedWriter" + ex);
                    }
                }
            } else {
                System.out.println("Better luck next time!");
            }
        }


}
