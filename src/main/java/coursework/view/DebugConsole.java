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
package coursework.view;

import coursework.controller.DebugPanel;
import coursework.controller.GameBoard;
import coursework.model.Ball;
import coursework.model.Levels;
import coursework.model.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by Nguyen Ha Van on 11/12/2021
 * @author Van
 * @since 11/12/2021
 *
 */
public class DebugConsole extends JDialog implements WindowListener{

    private static final String TITLE = "Debug Console";


    private JFrame owner;
    private DebugPanel debugPanel;
    private GameBoard gameBoard;
    private Wall wall;
    private Levels levels;
    /**
     * Constructor of Debug Console
     * @param owner JFrame Owner that will display the debug console screen
     * @param wall from Wall class
     * @param levels from Level class
     * @param gameBoard from Game Board class
     */
    public DebugConsole(JFrame owner,Wall wall,Levels levels,GameBoard gameBoard){

        this.wall = wall;
        this.levels = levels;
        this.owner = owner;
        this.gameBoard = gameBoard;
        initialize();

        debugPanel = new DebugPanel(wall, levels);
        this.add(debugPanel,BorderLayout.CENTER);


        this.pack();
    }
    /**
     *Initialize the debug console and display the screen
     */
    private void initialize(){
        this.setModal(true);
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.addWindowListener(this);
        this.setFocusable(true);
    }

    /**
     * Set the position of the debug console screen.
     */
    private void setLocation(){
        int x = ((owner.getWidth() - this.getWidth()) / 2) + owner.getX();
        int y = ((owner.getHeight() - this.getHeight()) / 2) + owner.getY();
        this.setLocation(x,y);
    }

    /**
     * When the debug console screen is closed, the game board is painted
     * and displayed on the screen.
     * @param windowEvent is the user's window screen
     */
    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        gameBoard.repaint();
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }
    /**
     * Displays the debug console screen
     * @param windowEvent is the user's window screen
     */
    @Override
    public void windowActivated(WindowEvent windowEvent) {
        setLocation();
        Ball b = wall.getBall();
        debugPanel.setValues(b.getSpeedX(),b.getSpeedY());
    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
