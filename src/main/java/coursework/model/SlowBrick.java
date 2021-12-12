package coursework.model;
import static coursework.controller.ScoreController.getInstance;


import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Nguyen Ha Van on 11/12/2021
 * @author Van
 * @since 11/12/2021
 *
 */
public class SlowBrick extends Brick {

    private static final String NAME = "Slow Brick";
    private static final Color INNER_COLOR = new Color(246,239,83);
    private static final Color BORDER_COLOR = new Color(217,181,33);
    private static final int SLOW_STRENGTH = 1;
    /**
     * Constructor of Slow Brick
     * @param point determine the brick's X and Y coordinates.
     * @param size is the size of the brick
     */
    public SlowBrick(Point point, Dimension size){
        super(NAME,point,size, BORDER_COLOR, INNER_COLOR, SLOW_STRENGTH);
    }
    /**
     * setImpact() is called when the brick has an impact
     * @param point the brick's X and Y coordinates
     * @param dir direction in which the brick is contacted and shattered
     * @return broken brick
     */
    @Override
    public boolean setImpact(Point2D point , int dir){
        if(super.isBroken())
            return false;
        else {
            impact();
            SlowDown(); //call the slow down function to increase
        }
        return super.isBroken();
    }

    /**
     * SlowDown() will set the speed of the ball to 1 and change it to yellow
     * when player hit the yellow brick
     */
    private void SlowDown() { //speed up the ball
        if (super.isBroken()){
            getInstance().setScore(getInstance().getScore()+7); //This will +17 points when ball hit fast brick
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Levels.getWall().setBallXSpeed(3);
                    Levels.getWall().setBallYSpeed(-3);
                    Player.setInnerColor(Color.GREEN);
                }
            }, 3000); //delay 3 sec
            Levels.getWall().setBallXSpeed(1);
            Levels.getWall().setBallYSpeed(1);
            Player.setInnerColor(Color.YELLOW); //change the paddle to yellow
        }
    }
    /**
     * makeBrickFace() create the Slow brick
     * @param pos is the brick's X and Y coordinates.
     * @param size is the brick's size
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }
    /**
     * getBrick() will return the Slow brick's face
     */
    @Override
    public Shape getBrick() {
        return super.getBrickFace();
    }
}