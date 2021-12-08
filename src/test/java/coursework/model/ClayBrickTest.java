package coursework.model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ClayBrickTest {
    //Create Objects for testing
    ClayBrick clayBrick = new ClayBrick(new Point(0,0), new Dimension(40,20));
    Rectangle b1Face = new Rectangle(new Point(0, 0), new Dimension(40, 20));
    @Test
    void makeBrickFace() {
        Point pos = new Point(0,0);
        Dimension size = new Dimension(40,20);
        assertEquals(b1Face, clayBrick.makeBrickFace(pos, size));
    }

    @Test
    void getBrick() {
        assertEquals(b1Face, clayBrick.getBrick());
    }
}