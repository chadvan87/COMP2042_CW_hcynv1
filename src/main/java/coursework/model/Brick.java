package coursework.model;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Created by Nguyen Ha Van on 11/12/2021
 * @author Van
 * @since 11/12/2021
 *
 */
abstract public class Brick  {

    public static final int MIN_CRACK = 1;
    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;


    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;

    private static Random rnd;

    private String name;

    public Shape getBrickFace() {
        return brickFace;
    }

    Shape brickFace;

    private Color border;
    private Color inner;

    private int fullStrength;

    private int strength;

    private boolean broken;


    /**
     * Constructor of Brick class
     * @param name is the name of the brick
     * @param pos is the coordinate X and Y of brick
     * @param size is the size of the brick
     * @param border is the color of the brick border
     * @param inner is the color of the brick for the inner part
     * @param strength is the strength of the brick
     */
    public Brick(String name, Point pos,Dimension size,Color border,Color inner,int strength){
        rnd = new Random();
        broken = false;
        this.name = name;
        brickFace = makeBrickFace(pos,size);
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;

    }
    /**
     * Abstract method to display the brick on screen
     */
    protected abstract Shape makeBrickFace(Point pos,Dimension size);
    /**
     * setImpact is called when the brick has an impact
     * @param point the brick's X and Y coordinates
     * @param dir direction in which the brick is contacted and shattered
     * @return broken brick
     */
    public  boolean setImpact(Point2D point , int dir){
        if(broken)
            return false;
        impact();
        return  broken;
    }
    /**
     * abstract method to get brick type
     * @return brick type
     */
    public abstract Shape getBrick();


    /**
     * Getter in Brick class
     */
    public Color getBorderColor(){
        return  border;
    }
    public Color getInnerColor(){
        return inner;
    }

    /**
     * findImpact will display what happens when the ball hit the brick
     * @param b is the ball type
     * @return an integer to represent the force with which the ball and brick collide
     */
    public final int findImpact(Ball b){
        if(broken)
            return 0;
        int out  = 0;
        if(brickFace.contains(b.getRight()))
            out = LEFT_IMPACT;
        else if(brickFace.contains(b.getLeft()))
            out = RIGHT_IMPACT;
        else if(brickFace.contains(b.getUp()))
            out = DOWN_IMPACT;
        else if(brickFace.contains(b.getDown()))
            out = UP_IMPACT;
        return out;
    }
    /**
     * isBroken() will check if the brick broken or not
     * @return broken brick
     */
    public final boolean isBroken(){
        return broken;
    }
    /**
     * Repair will reset the brick,
     * ensuring that it is not fractured and retains all of its original strength.
     */
    public void repair() {
        broken = false;
        strength = fullStrength;
    }
    /**
     * impact() will reduce the strength of the brick after it has been struck;
     * if the strength is zero,
     * the brick will be shattered.
     */
    public void impact(){
        strength--;
        broken = (strength == 0);
    }
    /**
     * get method for random, encapsulating
     * @return random number
     */
    public static Random getRnd() {
        return rnd;
    }
    /**
     * Getter for the Brick Strength
     * @return strength
     */
    public int getStrength() {
        return strength;
    }

}





