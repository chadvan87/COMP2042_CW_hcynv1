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
public class FastBrick extends Brick {

    private static final String NAME = "Fast Brick";
    private static final Color INNER_COLOR = new Color(233,1,6);
    private static final Color BORDER_COLOR = new Color(241,97,91);
    private static final int FAST_STRENGTH = 1;
    /**
     * Constructor of Fast Brick
     * @param point determine the brick's X and Y coordinates.
     * @param size is the size of the brick
     */
    public FastBrick(Point point, Dimension size){
        super(NAME,point,size, BORDER_COLOR, INNER_COLOR, FAST_STRENGTH);
    }

    @Override
    /**
     * setImpact() is called when the brick has an impact
     * @param point the brick's X and Y coordinates
     * @param dir direction in which the brick is contacted and shattered
     * @return broken brick
     */
    public boolean setImpact(Point2D point , int dir){
        if(super.isBroken())
            return false;
        else {
            impact();
            SpeedUp();
            FastPlayer(); //call the speed up function to increase
        }
        return super.isBroken();
    }

    /**
     * SpeedUp() will increase the ball speed
     * when player hit the red brick
     */
    private void SpeedUp() { //speed up the ball
        if (super.isBroken()){
            getInstance().setScore(getInstance().getScore()+5); //This will +15 points when ball hit fast brick
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Levels.getWall().setBallXSpeed(3);
                    Levels.getWall().setBallYSpeed(-3);
                }
            }, 3000); //delay 3 sec
            Levels.getWall().setBallXSpeed(4);
            Levels.getWall().setBallYSpeed(4);
        }
    }
    /**
     * FastPlayer() will increase paddle speed and change it to red
     * when player hit the red brick
     */
    public void FastPlayer(){ //speed up the Paddle(Player)
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Player.setDefMoveAmount(5); //default speed of the paddle
                Player.setInnerColor(Color.GREEN); //default color of the paddle
            }
        }, 3000); //delay 3 sec

        Player.setInnerColor(Color.RED);
        Player.setDefMoveAmount(11);

    }
    /**
     * makeBrickFace() create the Fast brick
     * @param pos is the brick's X and Y coordinates.
     * @param size is the brick's size
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }
    /**
     * getBrick() will return the fast brick's face
     */
    @Override
    public Shape getBrick() {
        return super.getBrickFace();
    }
}