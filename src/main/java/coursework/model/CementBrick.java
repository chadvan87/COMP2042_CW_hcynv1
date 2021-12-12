package coursework.model;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

import static coursework.controller.ScoreController.getInstance;

/**
 * Created by Nguyen Ha Van on 11/12/2021
 * @author Van
 * @since 11/12/2021
 *
 */
public class CementBrick extends Brick {


    private static final String NAME = "Cement Brick";
    private static final Color DEF_INNER = new Color(147, 147, 147);
    private static final Color DEF_BORDER = new Color(217, 199, 175);
    private static final int CEMENT_STRENGTH = 2;

    private Crack crack;
    private Shape brickFace;

    /**
     * Constructor of Cement Brick
     * @param point determine the brick's X and Y coordinates.
     * @param size is the size of the brick
     */
    public CementBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CEMENT_STRENGTH);
        crack = new Crack(this, DEF_CRACK_DEPTH, DEF_STEPS);
        brickFace = super.brickFace;
    }
    /**
     * makeBrickFace() create the cement brick
     * @param pos is the brick's X and Y coordinates.
     * @param size is the brick's size
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }
    /**
     * setImpact() is called when the brick has an impact
     * @param point the brick's X and Y coordinates
     * @param dir direction in which the brick is contacted and shattered
     * @return broken brick
     */
    @Override
    public boolean setImpact(Point2D point, int dir) {
        if(super.isBroken())
            return false;
        super.impact();
        if(!super.isBroken()){
            crack.makeCrack(point,dir);
            getInstance().setScore(getInstance().getScore()+10); //Plus 20 points when player break Cement Brick
            updateBrick();
            return false;
        }
        return true;
    }

    /**
     * getBrick() will return the cement brick's face
     */
    @Override
    public Shape getBrick() {
        return brickFace;
    }
    /**
     * updateBrick() will break the brick if it gets hit more than 1 time.
     */
    private void updateBrick(){
        if(!super.isBroken()){
            GeneralPath gp = crack.draw();
            gp.append(super.brickFace,false);
            brickFace = gp;
        }
    }
    /**
     * repair() will reset the brick,
     * returned the brick to its original strength and not shattered
     */
    public void repair(){
        super.repair();
        crack.reset();
        brickFace = super.brickFace;
    }
}
