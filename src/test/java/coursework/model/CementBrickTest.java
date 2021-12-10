package coursework.model;

import java.awt.*;
import java.awt.geom.Point2D;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CementBrickTest {
    //Create Objects for testing
    CementBrick cBrick = new CementBrick(new Point(0,0), new Dimension(50,30));
    Rectangle bFace = new Rectangle(new Point(0, 0), new Dimension(50, 30));
    //Start testing all methods in CementBrick
    @Test
    void makeBrickFace() {
        Point pos = new Point(0,0);
        Dimension size = new Dimension(50,30);
        assertEquals(bFace, cBrick.makeBrickFace(pos, size));
    }

    @Test
    void setImpact() {
        Point2D down = new Point2D.Double();
        down.setLocation(300.0, 435.0);
        int up = 30;
        assertFalse(cBrick.setImpact(down, up));
    }

    @Test
    void getBrick() {
        assertEquals(bFace, cBrick.getBrick());
    }

    @Test
    void repair() {
        cBrick.repair();
        assertFalse(cBrick.isBroken());
    }
}