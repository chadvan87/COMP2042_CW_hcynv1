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
import java.awt.geom.Point2D;
import java.util.Random;

import static coursework.controller.ScoreController.getInstance;

/**
 * Created by Nguyen Ha Van on 11/12/2021
 * @author Van
 * @since 11/12/2021
 *
 */
public class SteelBrick extends Brick {

    private static final String NAME = "Steel Brick";
    private static final Color DEF_INNER = new Color(203, 203, 201);
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int STEEL_STRENGTH = 1;
    private static final double STEEL_PROBABILITY = 0.4;

    private Random rnd;
    private Shape brickFace;
    /**
     * Constructor of Steel Brick
     * @param point determine the brick's X and Y coordinates.
     * @param size is the size of the brick
     */
    public SteelBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,STEEL_STRENGTH);
        rnd = new Random();
        brickFace = super.brickFace;
    }


    @Override
    /**
     * makeBrickFace() create the steel brick
     * @param pos is the brick's X and Y coordinates.
     * @param size is the brick's size
     */
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    @Override
    /**
     * getBrick() will return the Steel Brick's face
     */
    public Shape getBrick() {
        return brickFace;
    }
    /**
     * setImpact() is called when the brick has an impact
     * @param point the brick's X and Y coordinates
     * @param dir direction in which the brick is contacted and shattered
     * @return broken brick
     */
    public  boolean setImpact(Point2D point , int dir){
        if(super.isBroken())
            return false;
        impact();
        return  super.isBroken();
    }
    /**
     * If the random number created when a brick is hit is smaller than the probability of a brick,
     * the strength is reduced by one.
     */
    public void impact(){
        if(rnd.nextDouble() < STEEL_PROBABILITY){
            super.impact();
            getInstance().setScore(getInstance().getScore()+15); //Plus 25 points when user break Steel Brick
        }
    }

}
