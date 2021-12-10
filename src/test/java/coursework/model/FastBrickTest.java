package coursework.model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class FastBrickTest {
    FastBrick fastBrick = new FastBrick(new Point(), new Dimension(1,1));

    @Test
    void impactFastBrick() {
        fastBrick.impact(); //The Fast Brick will be impacted.
        assertEquals(0, fastBrick.getStrength()); //Check if the strength is 0
    }

    @Test
    void isFastBrick() {
        fastBrick.impact(); //The Fast  Brick will be impacted.
        assertTrue(fastBrick.isBroken()); //Check to see whether its strength is zero.
    }

    @Test
    void repairFastBrick() {
        fastBrick.impact(); //The  Fast  Brick will be impacted.
        fastBrick.repair(); //Repair the brick
        assertEquals(1,fastBrick.getStrength()); //Check to see whether its strength is  back to 1
    }


}