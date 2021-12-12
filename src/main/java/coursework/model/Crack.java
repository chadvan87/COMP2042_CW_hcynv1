package coursework.model;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;


/**
 * Created by Nguyen Ha Van on 11/12/2021
 * @author Van
 * @since 11/12/2021
 *
 */
public class Crack {

    private static final int CRACK_SECTIONS = 3;
    private static final double JUMP_PROBABILITY = 0.7;

    public static final int LEFT = 10;
    public static final int RIGHT = 20;
    public static final int UP = 30;
    public static final int DOWN = 40;
    public static final int VERTICAL = 100;
    public static final int HORIZONTAL = 200;

    private final Brick brick;
    private GeneralPath crack;

    private int crackDepth;
    private int steps;

    /**
     * Crack Constructor is used to create cracks in bricks that have been hit.
     * @param brick from Brick class
     * @param crackDepth crack depth of brick
     * @param steps steps of brick
     */
    public Crack(Brick brick, int crackDepth, int steps) {
        this.brick = brick;
        crack = new GeneralPath();
        this.crackDepth = crackDepth;
        this.steps = steps;

    }

    /**
     * draw() will display the crack of the brick on screen
     * @return crack
     */
    public GeneralPath draw() {

        return crack;
    }
    /**
     * Reset the cracking
     */
    public void reset() {
        crack.reset();
    }
    /**
     * MakeCrack() create a brick crack based on the ball striking the brick.
     * @param point is the point of brick that is being hit
     * @param direction is the direction of brick that is being hit
     */
    protected void makeCrack(Point2D point, int direction) {
        Rectangle bounds = brick.getBrickFace().getBounds();

        Point impact = new Point((int) point.getX(), (int) point.getY());
        Point start = new Point();
        Point end = new Point();


        switch (direction) {
            case LEFT:
                start.setLocation(bounds.x + bounds.width, bounds.y);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                Point tmp = makeRandomPoint(start, end, VERTICAL);
                makeCrack(impact, tmp);
                break;
            case RIGHT:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x, bounds.y + bounds.height);
                tmp = makeRandomPoint(start, end, VERTICAL);
                makeCrack(impact, tmp);
                break;
            case UP:
                start.setLocation(bounds.x, bounds.y + bounds.height);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                tmp = makeRandomPoint(start, end, HORIZONTAL);
                makeCrack(impact, tmp);
                break;
            case DOWN:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x + bounds.width, bounds.y);
                tmp = makeRandomPoint(start, end, HORIZONTAL);
                makeCrack(impact, tmp);
                break;
        }
    }
    /**
     * This makeCrack() will draw the brick's crack.
     * @param start is the start point of the crack brick
     * @param end is the end point of the crack of brick
     */
    protected void makeCrack(Point start, Point end) {

        GeneralPath path = new GeneralPath();


        path.moveTo(start.x, start.y);

        double w = (end.x - start.x) / (double) steps;
        double h = (end.y - start.y) / (double) steps;


        double x, y;

        for (int i = 1; i < steps; i++) {

            x = (i * w) + start.x;
            y = (i * h) + start.y + randomInBounds(crackDepth);

            if (inMiddle(i, CRACK_SECTIONS, steps))
                y += jumps(jump(), JUMP_PROBABILITY);

            path.lineTo(x, y);

        }

        path.lineTo(end.x, end.y);
        crack.append(path, true);
    }

    /**
     * jump method() will make the ball bounce
     * @return crackDepth
     */
    public int jump (){
        return crackDepth * 5;
    }
    /**
     * randomInBounds() will make the ball bounce in a random way
     * @param bound is bound of brick
     * @return random number of brick
     */
    private int randomInBounds(int bound) {
        int n = (bound * 2) + 1;
        return Brick.getRnd().nextInt(n) - bound;
    }
    /**
     * inMiddle() methods
     * @param i integer checking
     * @param steps steps of brick
     * @param divisions division of brick
     * @return checking the status of the ball
     */
    private boolean inMiddle(int i, int steps, int divisions) {
        int low = (steps / divisions);
        int up = low * (divisions - 1);

        return (i > low) && (i < up);
    }
    /**
     * jumps() will do nothing if Brick.getRnd().nextDouble() smaller than probality
     * @param bound is the bound of brick
     * @param probability is probability of brick, depends on method
     * @return the method randomInBounds
     */
    private int jumps(int bound, double probability) {

        if (Brick.getRnd().nextDouble() > probability)
            return randomInBounds(bound);
        return 0;

    }
    /**
     * makeRandomPoint will make random output
     * @param from is the start point
     * @param to is the end point
     * @param direction is the direction of point, horizontal or vertical
     * @return output position
     */
    private Point makeRandomPoint(Point from, Point to, int direction) {

        Point out = new Point();
        int pos;

        switch (direction) {
            case HORIZONTAL:
                pos = Brick.getRnd().nextInt(to.x - from.x) + from.x;
                out.setLocation(pos, to.y);
                break;
            case VERTICAL:
                pos = Brick.getRnd().nextInt(to.y - from.y) + from.y;
                out.setLocation(to.x, pos);
                break;
        }
        return out;
    }

}