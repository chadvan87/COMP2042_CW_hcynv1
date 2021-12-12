package coursework.model;

import java.awt.*;

/**
 * Created by Nguyen Ha Van on 11/12/2021
 * @author Van
 * @since 11/12/2021
 *
 */
public class Levels {
    private static final int LEVELS_COUNT = 9;

    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;
    private static final int Fast = 4;
    private static final int Slow = 5;

    private Brick[][] levels;
    private int level;

    public static Wall getWall() {
        return wall;
    }

    private static Wall wall;
    /**
     * Constructor for Levels class which will show bricks on each level.
     * @param drawArea is the size of the bricks level
     * @param brickCount is the number of bricks
     * @param lineCount is the number of line
     * @param brickDimensionRatio is the size of bricks
     * @param wall from Wall class
     */
    public Levels(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Wall wall){
        levels = makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);
        level = 0;
        this.wall = wall;

    }
    /**
     * The bricks in all the rows are the same.
     * @param drawArea is the size of the bricks level
     * @param brickCnt is the number of bricks
     * @param lineCnt is the number of lines
     * @param brickSizeRatio is the size of the brick
     * @param type is the type of the brick
     * @return the three layer of bricks
     */
    private Brick[] makeSingleTypeLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int type){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp  = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            double x = (i % brickOnLine) * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);
            tmp[i] = makeBrick(p,brickSize,type);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = new ClayBrick(p,brickSize);
        }
        return tmp;

    }
    /**
     * The bricks are alternated one at a time.
     * @param drawArea is the size of the bricks level
     * @param brickCnt is the number of bricks
     * @param lineCnt is the number of lines
     * @param brickSizeRatio is the size of the brick
     * @param typeA is the first type of brick
     * @param typeB is the second type of brick
     * @return the three layer of alternate placing bricks
     */
    private Brick[] makeChessboardLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp  = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            int posX = i % brickOnLine;
            double x = posX * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);

            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
            tmp[i] = b ?  makeBrick(p,brickSize,typeA) : makeBrick(p,brickSize,typeB);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = makeBrick(p,brickSize,typeA);
        }
        return tmp;
    }
    /**
     * Each level contains different types of bricks and design.
     * @param drawArea is the size of the bricks level
     * @param brickCount is the number of brick
     * @param lineCount is the number of line
     * @param brickDimensionRatio is the size of the brick
     * Add new Levels with 2 different type of brick Fast Brick and Slow Brick
     * @return each level with different pattern and bricks
     */
    private Brick[][] makeLevels(Rectangle drawArea,int brickCount,int lineCount,double brickDimensionRatio){
        Brick[][] tmp = new Brick[LEVELS_COUNT][];
        tmp[0] = makeSingleTypeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY);
        tmp[1] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,Fast,CEMENT); //New Level
        tmp[2] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,Slow,CEMENT);  //New Level
        tmp[3] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,STEEL);
        tmp[4] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,STEEL,CEMENT);
        tmp[5] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio, Fast,STEEL);  //New Level
        tmp[6] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio, CLAY,CEMENT);
        tmp[7] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio, Slow,STEEL);  //New Level
        tmp[8] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio, Slow,Fast);  //New Level
        return tmp;
    }
    /**
     * makeBrick() will make the brick and its color
     * @param point is the coordinate X and Y of the bricks
     * @param size is the size of the bricks
     * @param type is the type of the bricks
     * @return created bricks
     */
    private Brick makeBrick(Point point, Dimension size, int type){
        Brick out;
        switch(type){
            case CLAY:
                out = new ClayBrick(point,size);
                break;
            case STEEL:
                out = new SteelBrick(point,size);
                break;
            case CEMENT:
                out = new CementBrick(point, size);
                break;
            case Fast:
                out =  new FastBrick(point, size);
                break;
            case Slow:
                out = new SlowBrick(point, size);
                break;
            default:
                throw new IllegalArgumentException(String.format("Unknown Type:%d\n",type));
        }
        return  out;
    }
    /**
     * nextLevel() will Advance to the next level and
     * set the brick based on the level if there are more.
     */
    public void nextLevel(){
        wall.setBricks(levels[level++]);
        wall.setBrickCount(wall.getBricks().length);
    }

    /**
     * hasLevel() will check if there is more levels
     * @return the player's current level
     */
    public boolean hasLevel(){
        return level < levels.length;
    }
}
