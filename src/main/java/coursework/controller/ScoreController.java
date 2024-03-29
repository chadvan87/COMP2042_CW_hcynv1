package coursework.controller;

import javax.swing.*;
import java.io.*;

/**
 * Created by Nguyen Ha Van on 11/12/2021
 * @author Van
 * @since 11/12/2021
 *
 */
public class ScoreController {
    /**
     * Create variables for score and high score
     */
    private int score;
    private String highScore = "Nobody:0";
    private static final String HIGHSCORE_FILE="all_highscore.dat"; //file to store all users' high scores
    /**
     * Create getter and setter
     */
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public String getHighScore() {
        return highScore;
    }
    public void setHighScore(String highScore) {
        this.highScore = highScore;
    }
    /**
     * private Object instance (apply Singleton pattern)
     */
    private static ScoreController instance;
    /**
     * private constructor(apply singleton)
     */
    private ScoreController() {}
    /**
     * other class can access to Object instance
     * @return instance of Object
     */
    public static ScoreController getInstance(){
        if(instance == null){
            instance = new ScoreController();

        }
        return instance;
    }
    private String last=null, line;
    /**
     * GetHighScore() will read the high score base on the format Name : Score.
     * If the all_highscore file has many lines it will display the last line on screen
     * to show the current highest score
     */
    public String GetHighScore() {

        FileReader readFile = null;
        BufferedReader reader = null;
        try {
            readFile = new FileReader(HIGHSCORE_FILE);
            reader = new BufferedReader(readFile);
            while ((line = reader.readLine()) != null) {
                if (line != null) {
                    last = line;
                    highScore=last;
                }
            }
            return  highScore; //return the last line
        }
        catch (Exception e)
        {
            return  highScore="Nobody:0";
        }
        finally
        {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * CheckHighScore() will check if the current score is higher than last score
     *  and then it will display a pop up ask for user name
     * ,store that name into a file
     */
    public void CheckHighScore() {
        GetHighScore(); //This is to keep track of high score after user close the game
        if (score > Integer.parseInt((highScore.split(":")[1]))) {

            String name = JOptionPane.showInputDialog("You set a new highScore. What 's your name?");
            highScore = name + ":" + score;

            File scoreFile = new File(HIGHSCORE_FILE);
            if (!scoreFile.exists()) {
                try {
                    scoreFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            FileWriter writeFile = null;
            BufferedWriter writer = null;
            try {
                writeFile = new FileWriter(scoreFile,true);
                writer = new BufferedWriter(writeFile);
                writer.write(this.highScore);
                writer.newLine();
            }
            catch (Exception e) {
                System.out.println("Some Error happened");
            }
            finally {
                try {
                    if (writer != null)
                        writer.close();
                }
                catch (Exception e) { System.out.println("Some Error happend");}
            }
        }
    }

}
