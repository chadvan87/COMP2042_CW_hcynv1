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

import coursework.controller.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.Image;


/**
 * Created by Nguyen Ha Van on 11/12/2021
 * @author Van
 * @since 11/12/2021
 *
 */
public class HomeMenu extends JComponent implements MouseListener, MouseMotionListener {

    private static final String GREETINGS = "Welcome to:";
    private static final String GAME_TITLE = "Brick Destroy";
    private static final String CREDITS = "Version 0.1";
    private static final String START_TEXT = "Start";
    private static final String MENU_TEXT = "Exit";
    private static final String INFO_TEXT = "Info";
    private static final String Leader_TEXT = "Scores";

    private static final Color BG_COLOR = Color.GREEN.darker();
    private static final Color TEXT_COLOR = new Color(255, 255, 255);//White
    private static final Color CLICKED_BUTTON_COLOR = BG_COLOR.brighter();
    private static final Color CLICKED_TEXT = Color.WHITE;
    private static final int BORDER_SIZE = 5;
    private static final float[] DASHES = {12,6};

    private Rectangle menuFace;
    private Rectangle startButton;
    private Rectangle menuButton;
    private Rectangle infoButton;
    private Rectangle leaderButton;


    private BasicStroke borderStoke;
    private BasicStroke borderStoke_noDashes;

    private Font greetingsFont;
    private Font gameTitleFont;
    private Font creditsFont;
    private Font buttonFont;

    private GameFrame owner;

    private boolean startClicked;
    private boolean menuClicked;
    private boolean infoClicked;
    private Image background;

    /**
     * Constructor of HomeMenu
     * @param owner Game Frame owner will configure the home menu screen.
     * @param area will set the screen size
     */
    public HomeMenu(GameFrame owner,Dimension area){

        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.owner = owner;



        menuFace = new Rectangle(new Point(0,0),area);
        this.setPreferredSize(area);

        Dimension btnDim = new Dimension(area.width / 3, area.height / 12);
        startButton = new Rectangle(btnDim);
        menuButton = new Rectangle(btnDim);
        infoButton = new Rectangle(btnDim);
        leaderButton=new Rectangle(btnDim);

        borderStoke = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,0,DASHES,0);
        borderStoke_noDashes = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);

        greetingsFont = new Font("Noto Mono",Font.PLAIN,25);
        gameTitleFont = new Font("Noto Mono",Font.BOLD,45);
        creditsFont = new Font("Monospaced",Font.PLAIN,20);
        buttonFont = new Font("Monospaced",Font.PLAIN,startButton.height-2);



    }

    /**
     * paint() paints the screen, displaying text, buttons, pictures, and background images.
     * @param g is a graphics parameter that displays the screen's contents.
     */
    public void paint(Graphics g){
        drawMenu((Graphics2D)g);
    }

    /**
     *drawMenu() will create a menu screen with images, text, and buttons.
     * @param g2d is a graphics2d parameter
     */
    public void drawMenu(Graphics2D g2d){

        drawContainer(g2d);

        /*
        all the following method calls need a relative
        painting directly into the HomeMenu rectangle,
        so the translation is made here so the other methods do not do that.
         */
        Color prevColor = g2d.getColor();
        Font prevFont = g2d.getFont();

        double x = menuFace.getX();
        double y = menuFace.getY();

        g2d.translate(x,y);

        //methods calls
        drawText(g2d);
        drawButton(g2d);
        //end of methods calls

        g2d.translate(-x,-y);
        g2d.setFont(prevFont);
        g2d.setColor(prevColor);

    }
    /**
     * Draw the background image
     * @param  g2d  is a graphics2d parameter
     */
    private void drawContainer(Graphics2D g2d){
        background = new ImageIcon("src/main/java/coursework/resources/background.jpg").getImage();
        g2d.drawImage(background,0,0,null);

    }
    /**
     * Draw the text(greetings and title)
     * @param g2d is a graphics2d parameter
     */
    private void drawText(Graphics2D g2d){

        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D greetingsRect = greetingsFont.getStringBounds(GREETINGS,frc);
        Rectangle2D gameTitleRect = gameTitleFont.getStringBounds(GAME_TITLE,frc);
        Rectangle2D creditsRect = creditsFont.getStringBounds(CREDITS,frc);

        int sX,sY;

        sX = (int)(menuFace.getWidth() - greetingsRect.getWidth()) / 2;
        sY = (int)(menuFace.getHeight() / 4);

        g2d.setFont(greetingsFont);
        g2d.drawString(GREETINGS,sX,sY);

        sX = (int)(menuFace.getWidth() - gameTitleRect.getWidth()) / 2;
        sY += (int) gameTitleRect.getHeight() * 1.1;//add 10% of String height between the two strings

        g2d.setFont(gameTitleFont);
        g2d.drawString(GAME_TITLE,sX,sY);

        sX = (int)(menuFace.getWidth() - creditsRect.getWidth()) / 2;
        sY += (int) creditsRect.getHeight() * 1.1;

        g2d.setFont(creditsFont);
        g2d.drawString(CREDITS,sX,sY);


    }
    /**
     * drawButton will draw the button in the game like start,exit,scores,info
     * @param g2d is graphics2d parameter
     */
    private void drawButton(Graphics2D g2d){

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D txtRect = buttonFont.getStringBounds(START_TEXT,frc);
        Rectangle2D mTxtRect = buttonFont.getStringBounds(MENU_TEXT,frc);
        Rectangle2D aTxtRect = buttonFont.getStringBounds(INFO_TEXT,frc);
        Rectangle2D lTxtRect = buttonFont.getStringBounds(Leader_TEXT,frc);

        g2d.setFont(buttonFont);

        int x = (menuFace.width - startButton.width) / 2;
        int y =(int) ((menuFace.height - startButton.height) * 0.6);

        startButton.setLocation(x,y);

        x = (int)(startButton.getWidth() - txtRect.getWidth()) / 2;
        y = (int)(startButton.getHeight() - txtRect.getHeight()) / 2;

        x += startButton.x;
        y += startButton.y + (startButton.height * 0.9);




        if(startClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(startButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(START_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(startButton);
            g2d.drawString(START_TEXT,x,y);
        }

        x = startButton.x;
        y = startButton.y;

        y *= 1.5;

        // Draw info button
        int x1 = (int) ((menuFace.width - infoButton.width) / 2);
        int y1 =(int) ((menuFace.height - infoButton.height) * 0.8);

        infoButton.setLocation(x1,y1);

        x1 = (int)(infoButton.getWidth() - aTxtRect.getWidth()) / 2;
        y1 = (int)(infoButton.getHeight() - aTxtRect.getHeight()) / 2;

        x1 += infoButton.x;
        y1 += infoButton.y + (startButton.height * 0.9);

        g2d.draw(infoButton);
        g2d.drawString(INFO_TEXT,x1,y1);

        // Draw Leader Board Button
        int x2 = (int) ((menuFace.width - leaderButton.width) / 2);
        int y2 =(int) ((menuFace.height - leaderButton.height) * 0.7);

        leaderButton.setLocation(x2,y2);

        x2 = (int)(leaderButton.getWidth() - lTxtRect.getWidth()) / 2;
        y2 = (int)(leaderButton.getHeight() - lTxtRect.getHeight()) / 2;

        x2 += leaderButton.x;
        y2 += leaderButton.y + (startButton.height * 0.9);

        g2d.draw(leaderButton);
        g2d.drawString(Leader_TEXT,x2,y2);



        menuButton.setLocation(x,y);


        x = (int)(menuButton.getWidth() - mTxtRect.getWidth()) / 2;
        y = (int)(menuButton.getHeight() - mTxtRect.getHeight()) / 2;

        x += menuButton.x;
        y += menuButton.y + (startButton.height * 0.9);

        if(menuClicked){
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(menuButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(MENU_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(menuButton);
            g2d.drawString(MENU_TEXT,x,y);
        }

    }
    /** mouseClicked() method
     * When the start button is pressed, the game begins.
     * When the exit button is pressed, the game ends.
     * When the info button is pressed, the info page is shown
     * When scores button is pressed, show the leaderboard
     * @param mouseEvent is the user's mouse when clicked
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(startButton.contains(p)){
           owner.enableGameBoard();

        }
        else if(menuButton.contains(p)){
            System.out.println("Goodbye " + System.getProperty("user.name"));
            System.exit(0);
        }
        else if(infoButton.contains(p)){
            JFrame info = new JFrame();
            JOptionPane.showMessageDialog(info,"Use Space to start or pause the game\n" +
                            "Use A and D to move left and right\n" +
                            "Press ESC to enter or exit Pause Menu\n" +
                            "Press ALT+SHIFT+F1 to open console of the game ","How to play", JOptionPane.PLAIN_MESSAGE
                            );

        }
        else if(leaderButton.contains(p)){
            LeaderBoard.getLeaderBoard().DrawLeaderBoard(); //Display Leaderboard on Main Menu
        }
    }


    /** mousePressed() method
     * When buttons are clicked, change the color of the button text and border.
     * @param mouseEvent is the user's mouse when clicked
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(startButton.contains(p)){
            startClicked = true;
            repaint(startButton.x,startButton.y,startButton.width+1,startButton.height+1);

        }
        else if(menuButton.contains(p)){
            menuClicked = true;
            repaint(menuButton.x,menuButton.y,menuButton.width+1,menuButton.height+1);
        }

    }
    /**
     * When buttons are clicked, change the color of the button text and border to the original.
     * @param mouseEvent is the user's mouse when clicked
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(startClicked ){
            startClicked = false;
            repaint(startButton.x,startButton.y,startButton.width+1,startButton.height+1);
        }
        else if(menuClicked){
            menuClicked = false;
            repaint(menuButton.x,menuButton.y,menuButton.width+1,menuButton.height+1);
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }


    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }
    /**
     * mouseMoved will change the mouse to hand cursor
     * @param mouseEvent is the user's mouse when clicked
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(startButton.contains(p) || menuButton.contains(p) || infoButton.contains(p) || leaderButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());

    }
}
