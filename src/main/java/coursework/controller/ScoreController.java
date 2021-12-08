package coursework.controller;

import javax.swing.*;
import java.io.*;

public class ScoreController {
   //Create variables for score and high score
    private int score;
    private String highScore = "Nobody:0";
    private static final String HIGHSCORE_FILE="all_highscore.dat"; //file to store all users' high scores
    //Create getter and setter
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
    //Method for checking high score
    //get highscore
    public String GetHighScore()  {

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
    public void CheckHighScore() {
        GetHighScore();
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
