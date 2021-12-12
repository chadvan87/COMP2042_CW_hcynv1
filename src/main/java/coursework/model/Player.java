/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package coursework.model;

import java.awt.*;


/**
 * Created by Nguyen Ha Van on 11/12/2021
 * @author Van
 * @since 11/12/2021
 *
 */
public class Player {


    public static final Color BORDER_COLOR = Color.GREEN.darker().darker();
    public static Color INNER_COLOR = Color.GREEN;

    private static int DEF_MOVE_AMOUNT;

    private Rectangle playerFace;
    private Point ballPoint;


    private int moveAmount;
    private int min;
    private int max;

    /**
     * Constructor of Player class
     * @param ballPoint  is the ball's X and Y coordinates,
     *                   so that the bar(Player) is directly beneath the ball.
     * @param width is width of the bar(Player)
     * @param height is the height of the bar(Player)
     * @param container is the bar(Player)
     */
    public Player(Point ballPoint,int width,int height,Rectangle container) {
        this.ballPoint = ballPoint;
        moveAmount = 0;
        playerFace = makeRectangle(width, height);
        min = container.x + (width / 2);
        max = min + container.width - width;
        setDefMoveAmount(5);
    }
    /**
     * makeRectangle() will make the bar(Player)
     * @param width is width of the bar(Player)
     * @param height is height of the bar(Player)
     * @return
     */
    private Rectangle makeRectangle(int width,int height){
        Point p = new Point((int)(ballPoint.getX() - (width / 2)),(int)ballPoint.getY());
        return  new Rectangle(p,new Dimension(width,height));
    }
    /**
     * impact() will make the impact of the ball and the bar(Player)
     * @param b ball
     * @return true if impact(), else false
     */
    public boolean impact(Ball b){
        return playerFace.contains(b.getPosition()) && playerFace.contains(b.getDown()) ;
    }
    /**
     * move() will make sure the bar(Player) is not covering the entire screen by moving it to the left and right.
     */
    public void move(){
        double x = ballPoint.getX() + moveAmount;
        if(x < min || x > max)
            return;
        ballPoint.setLocation(x,ballPoint.getY());
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }
    /**
     * moveLeft() and moveRight() functions help the player move left and right
     */
    public void moveLeft(){
        moveAmount = -DEF_MOVE_AMOUNT;
    }

    public void moveRight(){
        moveAmount = DEF_MOVE_AMOUNT;
    }
    /**
     * stop() will stop the player from moving
     */
    public void stop(){
        moveAmount = 0;
    }
    /**
     * getter method for playerFace
     */
    public Shape getPlayerFace(){
        return  playerFace;
    }
    /**
     * moveTo() will set the position of the bar(Player)
     * @param p coordinate X and Y
     */
    public void moveTo(Point p){
        ballPoint.setLocation(p);
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }
    /**
     * Getter and setter for variables in the class
     */
    public static void setDefMoveAmount(int DEF_MOVE_AMOUNT){
        Player.DEF_MOVE_AMOUNT = DEF_MOVE_AMOUNT;
    }
    public static void setInnerColor(Color color){
        Player.INNER_COLOR = color;
    }
    public int getMoveAmount() {return moveAmount;}
}
