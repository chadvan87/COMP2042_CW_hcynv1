package coursework.view;



import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sounds {
    public Sounds(){}

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


}
