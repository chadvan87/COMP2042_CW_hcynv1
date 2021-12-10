package coursework.model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class WallTest {
    Wall wall = new Wall(new Rectangle(0,0,700,550),new Point(400,530));
    Player player = new Player(new Point(400,530),250,20,new Rectangle(0,0,700,550));
    RubberBall ball = new RubberBall(new Point(400,550));
    //Testing some important methods in Wall class
    @Test
    void move() {
        player.move();
        ball.move();
        assertNotNull(player);
        assertNotNull(ball);
    }

    @Test
    void findImpacts() {
        wall.findImpacts();
        assertEquals(0,ball.getSpeedY());
    }

    @Test
    void getBallCount() {
        assertEquals(3,wall.getBallCount());
    }

    @Test
    void isBallLost() {
        assertFalse(wall.isBallLost());
    }

    @Test
    void ballReset() {
        wall.ballReset();
        assertEquals(new Point(400,550),ball.getPosition());
    }

    @Test
    void wallReset() {
        CementBrick cBrick = new CementBrick(new Point(0,0), new Dimension(60,45));
        if(cBrick.isBroken()) {
            wall.wallReset();
        }

        assertEquals(0,wall.getBrickCount());
        assertEquals(3,wall.getBallCount());
    }

    @Test
    void isDone() {
        if(wall.getBrickCount() == 0){
            assertTrue(wall.isDone());
        }
    }
}