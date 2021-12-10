package coursework.controller;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ScoreControllerTest {
    @Test
    void TestGetHighScore() {
        ScoreController highScores = ScoreController.getInstance();
        //TestGetHighScore() will return the last line of all_highscore.dat.In this case, it is "John: 60".
        assertEquals("John:60", highScores.GetHighScore());
    }
}