
package business;

import java.util.Random;

/**
 *
 * @author Mark H
 */
public abstract class GuessingGame implements Game{
    private String endmsg;
    private int random;
    public static final String INSTRUCTIONS = "Try to guess my random number "
                                            + "between 1 and 100.  I will "
                                            + "give you hints after each guess. "
                                            + "Enter zero to stop.";
    
    public GuessingGame(){
       random = getRandom();
    }
    
    private int getRandom() {
        int rnum;
        Random r = new Random();
        rnum = r.nextInt(100)+1;//originally 0-99
        return rnum;
    }

    protected int getRandNo() {
        return random;
    }
    
    @Override
    public String getInstructions(){
        return INSTRUCTIONS;
    }

    @Override
    public String getEndMsg(){
        return endmsg;
    }
    
    @Override
    public abstract String getErrorMsg();
    
    @Override
    public abstract boolean isGameOver();
    
    @Override
    public abstract void setMove(String move);
    
    @Override
    public abstract String getGameName();
    
    @Override
    public abstract String getMoveResults();
}
