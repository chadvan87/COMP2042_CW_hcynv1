package coursework.model;

import java.awt.*;
import java.awt.Point;

/**
 * Created by Nguyen Ha Van on 11/12/2021
 * @author Van
 * @since 11/12/2021
 *
 */
public class ClayBrick extends Brick {

    private static final String NAME = "Clay Brick";
    private static final Color DEF_INNER = new Color(178, 34, 34).darker();
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int CLAY_STRENGTH = 1;

    /**
     * Constructor of the ClayBrick class
     * @param point determine the brick's X and Y coordinates.
     * @param size is the size of the brick
     */
    public ClayBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
    }
    /**
     * makeBrickFace() create the clay brick
     * @param pos is the brick's X and Y coordinates.
     * @param size is the brick's size
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }
    /**
     * getBrick() will return the clay brick's face
     */
    @Override
    public Shape getBrick() {
        return super.brickFace;
    }


}
