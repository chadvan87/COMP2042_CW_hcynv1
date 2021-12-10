package coursework.model;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class CrackTest {
    ClayBrick clayBrick = new ClayBrick(new Point(0,0), new Dimension(70,30));
    Crack crack = new Crack(clayBrick,1,40);
    @Test
    void draw() {
        crack.makeCrack(new Point(40,30),new Point(30,0));
        crack.draw();
        assertNotNull(crack);
    }

    @Test
    void reset() {
        crack.reset();
        assertFalse(clayBrick.isBroken());
    }

    @Test
    void makeCrack() {
        crack.makeCrack(new Point2D.Double(30.0,20.0), 10);
        assertNotNull(crack);
    }

    @Test
    void testMakeCrack() {
        crack.makeCrack(new Point(30,20), new Point(30,0));
        assertNotNull(crack);
    }
}