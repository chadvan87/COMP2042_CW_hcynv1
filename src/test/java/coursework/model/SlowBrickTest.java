package coursework.model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class SlowBrickTest {
    SlowBrick slowBrick = new SlowBrick(new Point(), new Dimension(2,2));

    @Test
    void impactSlowBrick() {
        slowBrick.impact(); //The SLow Brick will be impacted.
        assertEquals(0, slowBrick.getStrength()); //Check if the strength is 0
    }

    @Test
    void isSlowBrickBroken() {
        slowBrick.impact(); //The SLow Brick will be impacted.
        assertTrue(slowBrick.isBroken()); //Check to see whether its strength is zero.
    }

    @Test
    void repairSlowBrick() {
        slowBrick.impact(); //The SLow Brick will be impacted.
        slowBrick.repair(); //Repair the brick
        assertEquals(1, slowBrick.getStrength()); //Check to see whether its strength is  back to 1
    }

}