package coursework.model;
import static coursework.controller.ScoreController.getInstance;


import java.awt.*;
import java.awt.geom.Point2D;

public class SlowBrick extends Brick {

    private static final String NAME = "Slow Brick";
    private static final Color INNER_COLOR = new Color(246,239,83);
    private static final Color BORDER_COLOR = new Color(217,181,33);
    private static final int SLOW_STRENGTH = 1;

    public SlowBrick(Point point, Dimension size){
        super(NAME,point,size, BORDER_COLOR, INNER_COLOR, SLOW_STRENGTH);
    }

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
     * This method will set the speed of the ball to 1
     * when player hit the  brick
     */
    private void SlowDown() {
        if (super.isBroken()){
            getInstance().setScore(getInstance().getScore()+8); //This will +18 points when ball hit fast brick
            Levels.getWall().setBallXSpeed(1);
            Levels.getWall().setBallYSpeed(1);
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