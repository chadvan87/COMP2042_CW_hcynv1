package coursework.view;


import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class LeaderBoard {
    static String lineSeparator = System.getProperty("line.separator"); //This variable use for displaying Leaderboard
    /**
     * private Object instance (apply Singleton pattern)
     */
    private static LeaderBoard instance;
    /**
     * private constructor(apply singleton)
     */
    private LeaderBoard() {}
    /**
     * other class can access to Object instance
     * @return instance of Object
     */
    public static LeaderBoard getLeaderBoard(){
        if(instance == null){
            instance = new  LeaderBoard();

        }
        return instance;
    }
    /**
     * sortHighScore() function will sort the highscore.dat in order
     * and store it in leaderboard.dat
     */
    public void sortHighScore() {
        ArrayList<String> str = new ArrayList<>();
        String line = "";
        //Try to read the high score file
        try{
            BufferedReader reader = new BufferedReader(new FileReader("highscore.dat"));
            while((line=reader.readLine())!=null){
            str.add(line);
        }
            reader.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        //Sort the high score file and store it in leaderboard.dat
        Collections.sort(str);
        str.sort((o1, o2) -> Integer.compare( //Sort the score not the name
                Integer.parseInt(o2.substring(o2.indexOf(":") + 1)),
                Integer.parseInt(o1.substring(o1.indexOf(":") + 1))));
        try{
            FileWriter writer = new FileWriter("leaderboard.dat"); //Creating or Overwriting the file
            for(String s: str){
                writer.write(s);
                writer.write("\r\n");
            }

            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    /**
     * DrawLeaderBoard() will draw the leaderboard when User click the button
     * I am using JTextArea to display the text file but I set it to uneditable so players cannot edit
     */
    public void DrawLeaderBoard(){
        /**
         * Using JTextArea to display the leaderboard
         */
        JFrame f= new JFrame();
        JTextArea t=new JTextArea();
        t.setBounds(10,30, 240,200);
        t.setEditable(false); //This line of code will prevent the user from edit the text in JTextArea
        t.setFont(t.getFont().deriveFont(18f)); //set text size
        t.setForeground(Color.white); //set text font
        t.setBackground(Color.BLACK);  //set back ground
        f.add(t);  // add JTextArea to JFrame
        f.setSize(300,300);
        f.setLayout(null);
        f.setTitle("LeaderBoard");
        f.setVisible(true);

        File file = new File("leaderboard.dat");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder cont = new StringBuilder();
            String text;

            while ((text = reader.readLine()) != null) {
                cont.append(text).append(lineSeparator);
            }
            t.setText(cont.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
