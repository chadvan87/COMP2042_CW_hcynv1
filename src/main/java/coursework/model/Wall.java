
package coursework.model;

import coursework.controller.ScoreController;
import coursework.view.Sounds;
import java.awt.*;
import java.awt.geom.Point2D;


import static coursework.controller.ScoreController.getInstance;


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

    private void makeBall(Point2D ballPos){
        ball = new RubberBall(ballPos);
    }


    public void move(){
        player.move();
        ball.move();
    }

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

    private boolean impactBorder(){
        Point2D p = ball.getPosition();
        return ((p.getX() < area.getX()) ||(p.getX() > (area.getX() + area.getWidth())));
    }

    public int getBrickCount(){
        return brickCount;
    }

    public int getBallCount(){
        return ballCount;
    }

    public boolean isBallLost(){
        return ballLost;
    }

    public void ballReset(){
        player.moveTo(startPoint);
        ball.moveTo(startPoint);
        ball.setXSpeed(speedX);
        ball.setYSpeed(speedY);
        ballLost = false;
    }

    public void wallReset(){
        for(Brick b : bricks)
            b.repair();
        brickCount = bricks.length;
        ballCount = 3;
    }

    public boolean ballEnd(){
        return ballCount == 0;
    }

    public boolean isDone(){
        return brickCount == 0;
    }


    public void setBallXSpeed(int s){
        ball.setXSpeed(s);
    }

    public void setBallYSpeed(int s){
        ball.setYSpeed(s);
    }

    public void resetBallCount(){
        ballCount = 3;
    }


}
