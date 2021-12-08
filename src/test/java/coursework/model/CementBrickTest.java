package coursework.model;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class CementBrickTest {
    //Create Objects for testing
    CementBrick cBrick = new CementBrick(new Point(0,0), new Dimension(50,30));
    Rectangle bFace = new Rectangle(new Point(0, 0), new Dimension(50, 30));
    //Start testing all functions in CementBrick
    @org.junit.jupiter.api.Test
    void makeBrickFace() {
        Point pos = new Point(0,0);
        Dimension size = new Dimension(50,30);
        assertEquals(bFace, cBrick.makeBrickFace(pos, size));
    }

    @org.junit.jupiter.api.Test
    void setImpact() {
        Point2D down = new Point2D.Double();
        down.setLocation(300.0, 435.0);
        int up = 30;
        assertFalse(cBrick.setImpact(down, up));
    }

    @org.junit.jupiter.api.Test
    void getBrick() {
        assertEquals(bFace, cBrick.getBrick());
    }

    @org.junit.jupiter.api.Test
    void repair() {
        cBrick.repair();
        assertFalse(cBrick.isBroken());
    }
}