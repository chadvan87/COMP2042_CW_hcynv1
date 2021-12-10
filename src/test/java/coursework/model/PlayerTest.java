package coursework.model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
//Testing important methods for movements and impacts in Player class
class PlayerTest {
    //Create Objects for testing
    Rectangle rec = new Rectangle(20,30,40 , 20);
    Point p = new Point(30,30 );
    Player player = new Player(p,20,10,rec);

    //Testing all the functions in Player class
    @Test
    void impact() {
        //Test impact function
        Ball b = new RubberBall(p);
        player.impact(b);
        assertTrue(player.getPlayerFace().contains(p));
    }

    @Test
    void move() {
        //Testing Move function
        player.move();
        assertEquals(new Point(20,30 ), player.getPlayerFace().getBounds().getLocation());
    }

    @Test
    void moveLeft() {
        //Testing moveLeft function
        player.setDefMoveAmount(7);
        player.moveLeft();
        assertEquals(-7, player.getMoveAmount()); //Move Amount is 7 so moveLeft() will make it -7
    }

    @Test
    void moveRight() {
        player.setDefMoveAmount(8);
        player.moveRight();
        assertEquals(8, player.getMoveAmount());  //Move Amount is 8 so moveRight() will make it 8
    }

    @Test
    void stop() {
        player.setDefMoveAmount(8);
        player.moveRight();
        player.moveLeft();
        //Try to move left and right and the use the stop method to set move amount
        player.stop();
        assertEquals(0, player.getMoveAmount());  //The stop method will make move amount 0
    }

    @Test
    void moveTo() {
        Point p = new Point(60,60);
        player.moveTo(p);
        int x = 65;
        int y = 60 ;
        Point ans = new Point(50, 60);
        assertEquals(ans,player.getPlayerFace().getBounds().getLocation());
    }
}