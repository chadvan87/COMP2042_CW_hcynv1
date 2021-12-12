
package coursework.model;

import coursework.controller.ScoreController;
import coursework.view.Sounds;
import java.awt.*;
import java.awt.geom.Point2D;


import static coursework.controller.ScoreController.getInstance;

/**
 * Created by Nguyen Ha Van on 11/12/2021
 * @author Van
 * @since 11/12/2021
 *
 */
public class Wall {

    private Rectangle area;

    private Brick[] bricks;
    private Ball ball;

    public Ball getBall() {
        return ball;
    }


    public Player getPlayer() {
        return player;
    }



    private Player player;
    private int speedX;
    private int speedY;

    private Point startPoint;

    public Brick[] getBricks() {
        return bricks;
    }

    public void setBricks(Brick[] bricks) {
        this.bricks = bricks;
    }

    public void setBrickCount(int brickCount) {
        this.brickCount = brickCount;
    }



    private int brickCount;
    private int ballCount;
    private boolean ballLost;


    /**
     * Constructor of Wall
     * @param drawArea is the rectangle of the game screen
     * @param ballPos is the starting ball position
     */
    public Wall(Rectangle drawArea, Point ballPos){

        this.startPoint = new Point(ballPos);


        ballCount = 3;
        ballLost = false;

         speedX=3;
         speedY= -3;

        makeBall(ballPos);

        ball.setXSpeed(speedX);
        ball.setYSpeed(speedY);


        ball.setSpeed(speedX,speedY);

        player = new Player((Point) ballPos.clone(),150,10, drawArea);

        area = drawArea;


    }
    /**
     * makeBall() will make the ball that will be used in the game
     * @param ballPos is the starting ball position
     */
    private void makeBall(Point2D ballPos){
        ball = new RubberBall(ballPos);
    }

    /**
     * move() will move the player bar and ball
     */
    public void move(){
        player.move();
        ball.move();
    }
    /**
     * findImpacts() is used when the ball collides with a brick or the side and top borders.
     */
    public void findImpacts(){
        if(player.impact(ball)){
            ball.reverseY();
        }
        else if(impactWall()){
            /*for efficiency reverse is done into method impactWall
             * because for every brick program checks for horizontal and vertical impacts
             */
            Sounds.getSoundInstance().playSound("src/main/java/coursework/resources/Bounce.wav");
            brickCount--;
            /**
             * Increase score by 10 everytime the ball hit the wall
             */
            getInstance().setScore(getInstance().getScore()+10);
        }
        else if(impactBorder()) {
            ball.reverseX();
        }
        else if(ball.getPosition().getY() < area.getY()){
            ball.reverseY();
        }
        else if(ball.getPosition().getY() > area.getY() + area.getHeight()){
            ballCount--;
            ballLost = true;
        }
    }

    /**
     * When the ball strikes the brick
     * @return if the strength is 1, the brick will break; otherwise,
     * the brick will crack and the strength will decrease.
     */
    private boolean impactWall(){
        for(Brick b : bricks){
            switch(b.findImpact(ball)) {
                //Vertical Impact
                case Brick.UP_IMPACT:
                    ball.reverseY();
                    return b.setImpact(ball.getDown(), Crack.UP);
                case Brick.DOWN_IMPACT:
                    ball.reverseY();
                    return b.setImpact(ball.getUp(),Crack.DOWN);

                //Horizontal Impact
                case Brick.LEFT_IMPACT:
                    ball.reverseX();
                    return b.setImpact(ball.getRight(),Crack.RIGHT);
                case Brick.RIGHT_IMPACT:
                    ball.reverseX();
                    return b.setImpact(ball.getLeft(),Crack.LEFT);
            }
        }
        return false;
    }
    /**
     * impactBorder is to check to see if the ball has made contact with the top and side borders.
     * @return
    If there is an impact, return true; otherwise, return false.
     */
    private boolean impactBorder(){
        Point2D p = ball.getPosition();
        return ((p.getX() < area.getX()) ||(p.getX() > (area.getX() + area.getWidth())));
    }
    /**
     * getBrickCount(), getBallCount(), isBallLost() is getter for variables in Wall Class
     */
    public int getBrickCount(){
        return brickCount;
    }

    public int getBallCount(){
        return ballCount;
    }

    public boolean isBallLost(){
        return ballLost;
    }

    /**
     *ballReset() will reset the ball speed and return the ball and player bar to their original positions.
     */
    public void ballReset(){
        player.moveTo(startPoint);
        ball.moveTo(startPoint);
        ball.setXSpeed(speedX);
        ball.setYSpeed(speedY);
        ballLost = false;
    }
    /**
     * wallReset() will reset the wall, brick count and ball count
     */
    public void wallReset(){
        for(Brick b : bricks)
            b.repair();
        brickCount = bricks.length;
        ballCount = 3;
    }

    /**
     * @return  the ball Count at the end of game
     */
    public boolean ballEnd(){
        return ballCount == 0;
    }
    /**
     * @return isDone() set Brick Count to 0
     */
    public boolean isDone(){
        return brickCount == 0;
    }

    /**
     * setBallXSpeed() set the ball speed on x-axis
     */
    public void setBallXSpeed(int s){
        ball.setXSpeed(s);
    }
    /**
     * setBallYSpeed() set the ball speed on u-axis
     */
    public void setBallYSpeed(int s){
        ball.setYSpeed(s);
    }
    /**
     * resetBallCount() set the ball count to 3
     */
    public void resetBallCount(){
        ballCount = 3;
    }
}
