package coursework.view;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Nguyen Ha Van on 11/12/2021
 * @author Van
 * @since 11/12/2021
 *
 */
public class Sounds {

    /**
     * private Object instance (apply Singleton pattern)
     */
    private static Sounds instance;
    /**
     * private constructor(apply singleton)
     */
    private Sounds() {}
    /**
     * other class can access to Object instance
     * @return instance of Object
     */
    public static Sounds getSoundInstance(){
        if(instance == null){
            instance = new Sounds();

        }
        return instance;
    }

    /**
     * playSound() will play sound one time
     * @param file is the file that will play sound
     */
    public void playSound(String file){
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(file)));
            clip.start();
        } catch (LineUnavailableException e) {
            System.out.println("Audio Error");
        } catch (IOException e) {
            System.out.println("File Not Found");
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Wrong File Type");
        }
    }
    /**
     * playInLoop() will play sound in a loop
     * @param filename is the file that will play sound in a loop
     */
    public void playInLoop(String filename) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(filename)));
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (LineUnavailableException e) {
            System.out.println("Audio Error");
        } catch (IOException e) {
            System.out.println("File Not Found");
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Wrong File Type");
        }
    }


}
