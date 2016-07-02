
package business;

/**
 *
 * @author Mark H
 */
public class GuessHiLo extends GuessingGame{
    public static final String GAMENAME = "Guessing by High/Low";
    public String result = "", errmsg = "";
    public boolean over;
    public int gcount = 0;
    
    public GuessHiLo() {
        super();
        over = false;
    }
    
    @Override
    public void setMove(String move){
        int guess = 0;
        errmsg = "";
        try {
            guess = Integer.parseInt(move);
            if (guess < 0 || guess > 100) {
                errmsg = "Guess is out of bounds.\n";
                over = false;
            } else if (guess == 0){
                result = "Sorry. You did not guess my number " + 
                        super.getRandNo() + " in " + gcount + " guesses.\n";
                over = true;
            } else {
                if (guess < super.getRandNo()) {
                    gcount++;
                    result = "Your guess of " + guess + " is too low.\n";
                } else if (guess > super.getRandNo()) {
                    gcount++;
                    result = "Your guess of " + guess + " is too high.\n";
                } else {
                    gcount++;
                    result = "You guessed my number " + super.getRandNo() +
                            " (in " + gcount + " guesses!)\n";
                    over = true;
                }
            }
        } catch (Exception e) {
            errmsg = "Illegal guess.\n";
            over = false;
        }
        
    }
    
    @Override
    public String getErrorMsg() {
        return errmsg;
    }
    
    @Override
    public boolean isGameOver() {
        return over;
    }
    
    @Override
    public String getMoveResults(){
        return result;
    }
    
    @Override
    public String getGameName(){
        return GAMENAME;
    }
}
