package coursework.model;
import static coursework.controller.ScoreController.getInstance;


import java.awt.*;
import java.awt.geom.Point2D;

public class FastBrick extends Brick {

    private static final String NAME = "Fast Brick";
    private static final Color INNER_COLOR = new Color(233,1,6);
    private static final Color BORDER_COLOR = new Color(241,97,91);
    private static final int FAST_STRENGTH = 1;

    public FastBrick(Point point, Dimension size){
        super(NAME,point,size, BORDER_COLOR, INNER_COLOR, FAST_STRENGTH);
    }

    @Override
    public boolean setImpact(Point2D point , int dir){
        if(super.isBroken())
            return false;
        else {
            impact();
            SpeedUp(); //call the speed up function to increase
        }
        return super.isBroken();
    }

    /**
     * This method will set the speed of the ball to 8
     * when player hit the red brick
     */
    private void SpeedUp() {
        if (super.isBroken()){
            getInstance().setScore(getInstance().getScore()+5); //This will +15 points when ball hit fast brick
            Levels.getWall().setBallXSpeed(7);
            Levels.getWall().setBallYSpeed(7);
        }
    }

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    @Override
    public Shape getBrick() {
        return super.getBrickFace();
    }
}