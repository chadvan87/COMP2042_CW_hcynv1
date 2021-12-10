package coursework.model;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class SteelBrickTest {
    //Create Objects for testing
    SteelBrick sBrick = new SteelBrick(new Point(0,0), new Dimension(60,40));
    Rectangle b2Face = new Rectangle(new Point(0, 0), new Dimension(60, 40));
    //Start testing all methods in SteelBrick
    @Test
    void makeBrickFace() {
        Point pos = new Point(0,0);
        Dimension size = new Dimension(60,40);
        assertEquals(b2Face, sBrick.makeBrickFace(pos, size));
    }

    @Test
    void getBrick() {
        Point2D down = new Point2D.Double();
        down.setLocation(300.0, 435.0);
        int up = 30;
        assertFalse(sBrick.setImpact(down, up));
    }

    @Test
    void setImpact() {
        Point2D down = new Point2D.Double();
        down.setLocation(300.0, 435.0);
        int up = 30;
        assertTrue(sBrick.setImpact(down, up));
    }

}