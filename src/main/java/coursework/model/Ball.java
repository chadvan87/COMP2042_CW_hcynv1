package coursework.model;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;


/**
 * Created by Nguyen Ha Van on 11/12/2021
 * @author Van
 * @since 11/12/2021
 *
 */
abstract public class Ball {

    private Shape ballFace;

    private Point2D center;

    private Point2D up;
    private Point2D down;


    private Point2D left;
    private Point2D right;


    private Color border;
    private Color inner;

    private int speedX;
    private int speedY;
    /**
     * Constructor of Ball class
     * @param center center of the ball
     * @param radiusA is radius of the ball horizontally
     * @param radiusB is radius of the ball vertically
     * @param inner is the color of the ball for the inner part
     * @param border is the color of ball border
     */
    public Ball(Point2D center,int radiusA,int radiusB,Color inner,Color border){
        this.center = center;

        up = new Point2D.Double();
        down = new Point2D.Double();
        left = new Point2D.Double();
        right = new Point2D.Double();

        up.setLocation(center.getX(),center.getY()-(radiusB / 2));
        down.setLocation(center.getX(),center.getY()+(radiusB / 2));

        left.setLocation(center.getX()-(radiusA /2),center.getY());
        right.setLocation(center.getX()+(radiusA /2),center.getY());


        ballFace = makeBall(center,radiusA,radiusB);
        this.border = border;
        this.inner  = inner;
        speedX = 0;
        speedY = 0;
    }
    /**
     * makeBall() will create Rubber Ball so player can play the game
     * @param center center of the ball
     * @param radiusA is radius of the ball horizontally
     * @param radiusB is radius of the ball vertically
     * @return return the ball
     */
    protected abstract Shape makeBall(Point2D center,int radiusA,int radiusB);
    /**
     * move() will display the Ball
     */
    public void move(){
        RectangularShape tmp = (RectangularShape) ballFace;
        center.setLocation((center.getX() + speedX),(center.getY() + speedY));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        setPoints(w,h);


        ballFace = tmp;
    }
    /**
     * setSpeed() changes the speed of the ball
     * @param x represents our ball's horizontal velocity.
     * @param y represents our ball's vertical velocity.
     */
    public void setSpeed(int x,int y){
        speedX = x;
        speedY = y;
    }
    /**
     * setXspeed() will set the ball's speed for moving horizontally
     * @param s is the speed of the ball on x axis
     */
    public void setXSpeed(int s){
        speedX = s;
    }
    /**
     * setYspeed() will set the ball's speed for moving vertically
     * @param s is the speed of the ball on y-axis
     */
    public void setYSpeed(int s){
        speedY = s;
    }
    /**
     * reverseX() will change the direction of the ball on X-axis
     */
    public void reverseX(){
        speedX *= -1;
    }
    /**
     * reverseY() will change the direction of the ball on Y-axis
     */
    public void reverseY(){
        speedY *= -1;
    }
    /**
     * Getter for variables in Ball class
     */
    public Color getBorderColor(){
        return border;
    }

    public Color getInnerColor(){
        return inner;
    }

    public Point2D getPosition(){
        return center;
    }

    public Shape getBallFace(){
        return ballFace;
    }
    /**
     * moveTo() will move the ball to the position p
     * @param p denotes x and y coordinates
     */
    public void moveTo(Point p){
        center.setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        ballFace = tmp;
    }
    /**
     * setPoints will set the X and Y point coordinates for the ball's width and height.
     */
    private void setPoints(double width,double height){
        up.setLocation(center.getX(),center.getY()-(height / 2));
        down.setLocation(center.getX(),center.getY()+(height / 2));

        left.setLocation(center.getX()-(width / 2),center.getY());
        right.setLocation(center.getX()+(width / 2),center.getY());
    }
    /**
     * Getter and setter for variables in Ball class to improve encapsulation
     */
    public int getSpeedX(){
        return speedX;
    }
    public int getSpeedY(){
        return speedY;
    }
    public Point2D getUp() {
        return up;
    }
    public void setUp(Point2D up) {
        this.up = up;
    }
    public Point2D getDown() {
        return down;
    }
    public Point2D getLeft() {
        return left;
    }
    public Point2D getRight() {
        return right;
    }

}
